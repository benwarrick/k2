package com.ott;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.ott.beans.FillAggregator;
import com.ott.beans.MessageTranslators;
import com.ott.beans.OrderAggregator;
import com.ott.models.K2Order;


@Component
public class WebSocketRouter extends RouteBuilder {

    @Override
    public void configure() {
    	
    	JsonDataFormat formatter = new JsonDataFormat();
    	JacksonDataFormat K2OrderFormat = new JacksonDataFormat(K2Order.class);
    	AggregationStrategy aggregationStrategyOrder = new OrderAggregator(); 
    	AggregationStrategy aggregationStrategyFills = new FillAggregator();
    	
    	from("timer:myTimer?repeatCount=1")
    	//Prod
    	//from("ahc-wss://api.coinmetro.com/ws?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZpY2VJZCI6InF0ITI0NjAxQDciLCJpZCI6IjYwNjc2MzA2NGI0MGQyMWNjN2I1MmFiOSIsImlwIjoiMjMuMjguODkuMTM0IiwiaWF0IjoxNjMxNDg5MDIzfQ.KRfq-fdfj7ZSgqYnzakDUWFpGd4LoEMN0TRq5IxHA5c&pairs=BTCUSD")
    	
    	// Sandbox
    	//from("ahc-wss://api.coinmetro.com/open/ws?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxYmZhMDljNmEzMzQ4NjgyMmFlMzI1OCIsImlwIjoiMjYwMDoxNzAyOjRiZjk6ZTcyMDpkODRjOmRlYjM6YjEwOTphNDFhIiwiaWF0IjoxNjM5OTQ4NDQ0fQ.YuPr4qpNsd7ugj0yqc6TpCDCDOuoAn8s5OF5CU6Rfwc&pairs=BTCUSD")
        //from("ahc-wss://api.coinmetro.com/open/ws?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxYjUwMGMyYTMxNDkzMGZiMjUyMjc3NiIsImlwIjoiMjMuMjguODkuMTM0IiwiaWF0IjoxNjM5MjUyMTYyfQ.H1OKsD5nNJlVw5ozXJJ4GECJcw6ZaHOJLE7-PbwCnKs&pairs=BTCUSD")
    		.log("Received: ${body}")
    		
    		//.setBody(simple("{\"orderStatus\":{\"userID\":\"61b500c2a314930fb2522776\",\"orderID\":\"61b500c2a314930fb2522776164004250705813b6ac488924cf8f\",\"orderType\":\"limit\",\"buyingCurrency\":\"USD\",\"sellingCurrency\":\"BTC\",\"fillStyle\":\"sell\",\"buyingQty\":15,\"sellingQty\":0.00025,\"userData\":{\"comment\":12345,\"takeProfit\":0},\"timeInForce\":1,\"boughtQty\":0,\"soldQty\":0,\"creationTime\":1640042507062,\"seqNumber\":764197220,\"fees\":0,\"fills\":[],\"isAncillary\":false,\"margin\":false,\"trade\":false}}"))
    		.setBody(simple("{\"orderStatus\":{\"userID\":\"61b500c2a314930fb2522776\",\"orderID\":\"61b500c2a314930fb25227761639266547822cdda65dc0f2d523e\",\"orderType\":\"limit\",\"buyingCurrency\":\"USD\",\"sellingCurrency\":\"BTC\",\"fillStyle\":\"sell\",\"buyingQty\":76,\"sellingQty\":0.002,\"timeInForce\":1,\"boughtQty\":76,\"soldQty\":0.00153807,\"creationTime\":1639266547830,\"seqNumber\":757450782,\"userData\":{\"comment\":12345,\"takeProfit\":0},\"completionTime\":1639266687485,\"firstFillTime\":1639266687485,\"lastFillTime\":1639266687485,\"fills\":[{\"seqNumber\":757450781,\"timestamp\":1639266687485,\"qty\":0.00153807,\"price\":49412.57550046487,\"side\":\"sell\"},{\"seqNumber\":757450782,\"timestamp\":1639266687490,\"qty\":0.00153808,\"price\":49412.575500465,\"side\":\"sell\"}],\"takerQty\":76,\"fees\":0,\"isAncillary\":false,\"margin\":false,\"trade\":false}}"))
    		.choice()    	
        		.when(simple("${body} startsWith '{\"bookUpdate\"'"))
        			.to("direct://bookUpdate")
        		.when(simple("${body} startsWith '{\"orderStatus\"'"))
        			.setBody().jsonpath("$.orderStatus")
        			.multicast()
        				.to("direct://orderStatus")
        				.to("direct://fillStatus")
        	.endChoice();
    	
        from("direct://bookUpdate")
        	.log("BookUpdate: ${body}");
        
        
        from("direct://orderStatus")
        	.setHeader("orderid").jsonpath("$.orderID")
        	.bean(MessageTranslators.class, "CoinMetroToK2Order")
        	.multicast()
        		.to("direct://upsertOrder")
        		.to("direct://publishOrder")
        ;
        from("direct://upsertOrder")
	        .setHeader(MongoDbConstants.CRITERIA, new Expression() {
			    @Override
			    public <T> T evaluate(Exchange exchange, Class<T> type) {
			        String drRequestId = exchange.getIn().getHeader("orderid", String.class);
			        Bson equalsClause = Filters.eq("orderId", drRequestId);
			        return exchange.getContext().getTypeConverter().convertTo(type, equalsClause);
			    };
			})
	        .enrich("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.orders}}&operation=findOneByQuery", aggregationStrategyOrder)
	        .choice()
			.when(header("isNew").contains("true"))
				.log("New")
				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.orders}}&operation=insert")
			.endChoice()
			.otherwise()					
				.log("Update")
				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.orders}}&operation=save")
			.endChoice()
		;        
        from("direct://publishOrder")
        	.marshal(formatter)
        	.log("Order: ${body}")
        	.to("jms:topic:CoinMetro.Order.Updates.Feed.Address?jmsMessageType=Text")
        ;     
        
        
        from("direct://fillStatus")
        	.log("FillStatus ${body}")
	        .setHeader("buyingCurrency").jsonpath("$.buyingCurrency")
	        .setHeader("sellingCurrency").jsonpath("$.sellingCurrency")
	        .setHeader("orderId").jsonpath("$.orderID")
	        .setHeader("fee").jsonpath("$.fees")
        	.split().jsonpath("$.fills")
        		.log("Fill ${body}")
        		.log("Headers ${headers}")
        		.bean(MessageTranslators.class, "CoinMetroToK2Fill")
        		//.multicast()
        			//.to("direct://upsertFill")
        			//.to("direct://publishFill")
        ;
        
        from("direct://upsertFill")
        	.setHeader("seqNumber").jsonpath("$.seqNumber")
	        .setHeader(MongoDbConstants.CRITERIA, new Expression() {
			    @Override
			    public <T> T evaluate(Exchange exchange, Class<T> type) {
			        String drRequestId = exchange.getIn().getHeader("seqNumber", String.class);
			        Bson equalsClause = Filters.eq("seqNumber", drRequestId);
			        return exchange.getContext().getTypeConverter().convertTo(type, equalsClause);
			    };
			})
	        .enrich("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.fills}}&operation=findOneByQuery", aggregationStrategyFills)
	        .choice()
			.when(header("isNew").contains("true"))
				.log("New")
				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.fills}}&operation=insert")
			.endChoice()
			.otherwise()					
				.log("Update")
				.to("mongodb:mongo?database={{spring.data.mongodb.database}}&collection={{spring.data.mongodb.collection.fills}}&operation=save")
			.endChoice();

    }

}
