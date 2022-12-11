package com.ott;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.ott.candles.beans.MessageFilters;
import com.ott.candles.beans.ModelConverters;
import com.ott.candles.models.CoinMetroCandle;
import com.ott.candles.models.CoinMetroCandles;
import com.ott.candles.models.K2Candle;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class CandleRouter extends RouteBuilder {
	
    @Override
    public void configure() {
    	
    	JacksonDataFormat candleHistory = new JacksonDataFormat(CoinMetroCandles.class);
    	JacksonDataFormat candle = new JacksonDataFormat(CoinMetroCandle.class);
    	JacksonDataFormat k2Candle = new JacksonDataFormat(K2Candle.class);
    	
    	
        from("timer:candleTimer?period={{timer.period}}")
	        	.log("Poller")	
		        .setHeader(MongoDbConstants.SORT_BY).constant(Sorts.descending("finishTime"))
				.setHeader(MongoDbConstants.FIELDS_PROJECTION).constant(Projections.include("finishTime"))		
				.setBody().constant("{}")
				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.candleTable}}&operation=findOneByQuery")

				.setHeader("lastFinishTime").jsonpath("$.finishTime")
				.setHeader("lastFinishTime").exchange(exchange -> {
					if (exchange.getMessage().getHeader("lastFinishTime") != "new") {
						return ((long)exchange.getMessage().getHeader("lastFinishTime"));
					}
					else {
						Date date = new Date(); 
						long t = date.getTime();
						return t;
					}
				} )
				.setHeader("endTime").exchange(exchange -> {
					if (exchange.getMessage().getHeader("lastFinishTime") != "new") {
						Date date = new Date(); 
						// Grabbing the maximum number of available candles (1000) 
						long t = ((long)exchange.getMessage().getHeader("lastFinishTime")+(60000*1000));
						System.out.println(t);
						return t;
					}
					else {
						return 0; 
					}					
				})
				.log("Test Header ${headers.lastFinishTime}")
				.log("Test Header ${headers.endTime}")
				
				.log("https://api.coinmetro.com/exchange/candles/BTCUSD/60000/${headers.lastFinishTime}/${headers.endTime}")
				.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.GET))
				.toD("https://api.coinmetro.com/exchange/candles/BTCUSD/60000/${headers.lastFinishTime}/${headers.endTime}")
				//.log("Original: ${body}")
				.unmarshal(candleHistory)
				.bean(MessageFilters.class, "removeDuplicateCandles")
				.bean(MessageFilters.class, "removePartialCandles")
				.marshal(candleHistory)
				.log("History: ${body}")				
				
        
				.split().jsonpath("$.candleHistory")
				.bean(ModelConverters.class, "CoinMetroToK2Candle")
				.multicast()
					.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.candleTable}}&operation=insert")
					.to("direct://publish-candle")				
					.end()
					;
        
					
        from("direct://publish-candle")
			.marshal(k2Candle)
			.log("To JMS: ${body}")
			.to("jms:topic:CoinMetro.Candles.Address?jmsMessageType=Text");					
					
					
					
				
				//
				//.log("Body: ${body}")
				
				/*
				//.log("Header ${headers.lastFinishTime}")
				.split().jsonpath("$.candleHistory")
				.log("Coin Metro: ${body}")				
				.bean(ModelConverters.class, "CoinMetroToK2Candle")
				.multicast()
					.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.candleTable}}&operation=insert")
					.to("direct://publish-candle")
					.log("K2: ${body}")
				*/
			
        

			
        /*
        .routeId("hello")
            .transform().method("myBean", "saySomething")
            .filter(simple("${body} contains 'foo'"))
                .to("log:foo")
            .end()
            .to("stream:out");*/
    }

}
