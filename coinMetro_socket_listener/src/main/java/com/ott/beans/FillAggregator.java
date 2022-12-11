package com.ott.beans;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;

import com.ott.models.K2Fill;
import com.ott.models.K2Order;

public class FillAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange fillUpdateExchange, Exchange fillExchange) {
		if (fillExchange.getIn().getBody() == null) {
			fillUpdateExchange.getIn().setHeader("isNew", "true");
			return fillUpdateExchange;
		}
		else {
			fillExchange.getIn().setHeader("isNew", "false");
			
			K2Fill fillUpdate = (K2Fill) fillUpdateExchange.getMessage().getBody();
			
			Bson bson = (Bson) fillExchange.getMessage().getBody();
			BsonDocument bsonDoc = bson.toBsonDocument();
			
			bsonDoc.put("timestamp", new BsonInt64((long) fillUpdate.getSize()));
			//bsonDoc.put("qty", new BsonDouble(());
			
			return null; 
		}
	}

}
