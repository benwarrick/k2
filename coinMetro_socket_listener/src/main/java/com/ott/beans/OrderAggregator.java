package com.ott.beans;

import java.util.LinkedHashMap;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt64;
import org.bson.BsonNumber;
import org.bson.BsonString;
import org.bson.conversions.Bson;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.ott.models.K2Order;

public class OrderAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange orderUpdateExchange, Exchange orderExchange) {
		if (orderExchange.getIn().getBody() == null) {
			orderUpdateExchange.getIn().setHeader("isNew", "true");
			return orderUpdateExchange;
		}
		else {
			orderExchange.getIn().setHeader("isNew", "false");
			
			// "oldExchange" is the current document, from the web socket
			K2Order orderUpdate = (K2Order) orderUpdateExchange.getMessage().getBody();
			
			// "newExchange is the document from the aggregation query, from MongoDB
			Bson bson = (Bson) orderExchange.getMessage().getBody();
			BsonDocument bsonDoc = bson.toBsonDocument();

			if (orderUpdate.getClientId() != null) { bsonDoc.put("clientId", new BsonString((String) orderUpdate.getClientId())); }
			if (orderUpdate.getOrderId() != null) { bsonDoc.put("orderId", new BsonString((String) orderUpdate.getOrderId())); }
			if (orderUpdate.getCurrencyPair() != null) { bsonDoc.put("currencyPair", new BsonString((String) orderUpdate.getCurrencyPair())); }
			if (orderUpdate.getType() != null) { bsonDoc.put("type", new BsonString((String) orderUpdate.getType())); }
			if (orderUpdate.getSide() != null) { bsonDoc.put("side", new BsonString((String) orderUpdate.getSide())); }
			bsonDoc.put("size", new BsonDouble((double) orderUpdate.getSize())); 
			bsonDoc.put("price", new BsonDouble((double) orderUpdate.getPrice()));
			if (orderUpdate.getStatus() != null) { bsonDoc.put("status", new BsonString((String) orderUpdate.getStatus())); }
			bsonDoc.put("filledSize", new BsonDouble((double) orderUpdate.getFilledSize()));
			bsonDoc.put("remainingSize", new BsonDouble((double) orderUpdate.getRemainingSize()));
			bsonDoc.put("avgFillPrice", new BsonDouble((double) orderUpdate.getAvgFillPrice()));
			bsonDoc.put("creationTime", new BsonInt64((long) orderUpdate.getCreationTime()));
			if (orderUpdate.getCreationTimeDate() != null) { bsonDoc.put("creationTimeDate", new BsonString((String) orderUpdate.getCreationTimeDate())); }
			bsonDoc.put("fee", new BsonDouble((double) orderUpdate.getFee()));
			if (orderUpdate.getFeeCurrency() != null) { bsonDoc.put("feeCurrency", new BsonString((String) orderUpdate.getFeeCurrency())); }

			orderExchange.getMessage().setBody(bsonDoc);
			
			return orderExchange;
			
		}
	}

}
