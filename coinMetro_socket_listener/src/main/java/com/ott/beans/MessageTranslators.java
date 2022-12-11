package com.ott.beans;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;

import com.ott.models.K2Fill;
import com.ott.models.K2Order;


public class MessageTranslators {
	public K2Order CoinMetroToK2Order(@Body LinkedHashMap orderMap) {
		K2Order order = new K2Order();	
		
		double buyingQty = this.getDouble(orderMap.get("buyingQty"));		
		double sellingQty = this.getDouble(orderMap.get("sellingQty"));		
		double boughtQty = this.getDouble(orderMap.get("boughtQty"));
		double soldQty = this.getDouble(orderMap.get("soldQty"));
		double fees = this.getDouble(orderMap.get("fees"));
		

		order.setOrderId((String) orderMap.get("orderID"));
		
		if (((LinkedHashMap)orderMap.get("userData")).get("comment") instanceof Integer) {
			order.setClientId(Integer.toString( (int) ((LinkedHashMap)orderMap.get("userData")).get("comment")));
		} else {
			order.setClientId(Long.toString( (long) ((LinkedHashMap)orderMap.get("userData")).get("comment")));
		}
		
		order.setSide((String) orderMap.get("fillStyle")); 
		
		if (order.getSide() == "buy") {
			order.setCurrencyPair((String) orderMap.get("buyingCurrency") + "/" + (String) orderMap.get("sellingCurrency"));
			order.setPrice((boughtQty > 0) ? soldQty / boughtQty : 0);
			order.setSize(buyingQty);
			
			if (boughtQty >= sellingQty) {
				order.setStatus("closed");
			}
			else { order.setStatus("open"); }
			
			order.setFilledSize(boughtQty);
			order.setFeeCurrency((String) orderMap.get("buyingCurrency"));
		}
		else {
			order.setCurrencyPair((String) orderMap.get("sellingCurrency") + "/" + (String) orderMap.get("buyingCurrency"));
			order.setPrice((soldQty > 0) ? boughtQty / soldQty : 0);
			order.setSize(sellingQty);
			
			if (soldQty >= sellingQty) {
				order.setStatus("closed");
			}
			else { order.setStatus("open"); }
			
			order.setFilledSize(soldQty);
			order.setFeeCurrency((String) orderMap.get("sellingCurrency"));
		}
		
		order.setCreationTime((long) orderMap.get("creationTime"));
		
		LocalDateTime ldt = Instant.ofEpochMilli((long) orderMap.get("creationTime")).atZone(ZoneId.of("UTC")).toLocalDateTime();		
		DateTimeFormatter k2Format = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm:ss");
		order.setCreationTimeDate(ldt.format(k2Format));
		
		order.setFee(fees);		
		order.setType((String) orderMap.get("orderType"));
				
		return order;
	}
	
	public K2Fill CoinMetroToK2Fill(@Body LinkedHashMap fillMap, @Headers Map headers) {
		K2Fill fill = new K2Fill(); 
		
		fill.setFillId( getLong(fillMap.get("seqNumber")));
		if (headers.get("side") == "buy") {
			fill.setCurrencyPair((String) headers.get("buyingCurrency") + "/" + (String) headers.get("sellingCurrency"));
		}
		else {
			fill.setCurrencyPair((String) headers.get("sellingCurrency") + "/" + (String) headers.get("buyingCurrency") );
		}

		fill.setSide((String) fillMap.get("side"));
		fill.setSize(getDouble(fillMap.get("qty")));
		fill.setOrderId((String) headers.get("orderId"));
		fill.setFillTime( getLong(fillMap.get("timestamp")));
		
		LocalDateTime ldt = Instant.ofEpochMilli((long) fillMap.get("timestamp")).atZone(ZoneId.of("UTC")).toLocalDateTime();		
		DateTimeFormatter k2Format = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm:ss");
		fill.setFillTimeDate(ldt.format(k2Format));
		
		fill.setPrice( getDouble(fillMap.get("price")));
		fill.setFee( getDouble(headers.get("fee")));
		
		if (headers.get("side") == "buy") {
			fill.setFeeCurrency((String) headers.get("buyingCurrency"));
		}
		else {
			fill.setFeeCurrency((String) headers.get("sellingCurrency"));
		}
		
		return fill; 
	}
	
	private double getDouble(Object o) {
		double n;
		
		if (o instanceof Integer) {
			n = (Integer) o; 
		}
		else if (o instanceof Double) {
			n = (Double) o;
		}
		else {
			n = (Double) o;
		}
		return n;
	}
	private long getLong(Object o) {
		long n;
		
		if (o instanceof Integer) {
			n = (int) o; 
		}
		else {
			n = (long) o;
		}
		return n;
	}
}
