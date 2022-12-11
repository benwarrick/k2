package com.ott;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.ott.candles.beans.ModelConverters;



/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
    	// I'm not sure if this still works. Give it another test. 
    	from("timer:myTimer?repeatCount=1")
	    	.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.candleTable}}&operation=count")
	    	.log("Body: ${body}")
	    	.choice()
	    		.when().simple("${body} == '0'")
	    			.log("zero")
	    			.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.GET))
	    			.toD("https://api.coinmetro.com/exchange/candles/BTCUSD/60000")    
	        		.log("end choice")
	    			.split().jsonpath("$.candleHistory")
	    			.log("Coin Metro: ${body}")				
	    			.bean(ModelConverters.class, "CoinMetroToK2Candle")
	    			.multicast()
	    				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.candleTable}}&operation=insert")
	    				//.to("direct://publish-candle")
	    				.log("K2: ${body}")
	    				.end()
	    			.endChoice()
	        	.otherwise()
	        		.log("non-zer0")
	        ;
    }


}
