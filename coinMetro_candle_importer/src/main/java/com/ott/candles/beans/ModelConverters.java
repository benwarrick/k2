package com.ott.candles.beans;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.camel.Body;

import com.ott.candles.models.CoinMetroCandle;
import com.ott.candles.models.K2Candle;

public class ModelConverters {
	public K2Candle CoinMetroToK2Candle(@Body LinkedHashMap cmc) {
		K2Candle k2Candle = new K2Candle(); 
		
		k2Candle.setStartTime((long) cmc.get("timestamp"));
		k2Candle.setFinishTime((long) cmc.get("timestamp") + 60000);
		
		Date startDate = new Date((long) cmc.get("timestamp"));
		Date endDate = new Date(k2Candle.getFinishTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm");
		k2Candle.setStartTimeDate(dateFormat.format(startDate));
		k2Candle.setFinishTimeDate(dateFormat.format(endDate));
		
		
		if (cmc.get("o") instanceof Integer) {
			k2Candle.setOpenPrice( (double) ((Integer) cmc.get("o")).doubleValue());
		} else {
			k2Candle.setOpenPrice((double) cmc.get("o"));
		}
		
		if (cmc.get("c") instanceof Integer) {
			k2Candle.setClosePrice( (double) ((Integer) cmc.get("c")).doubleValue());
		} else {
			k2Candle.setClosePrice( (double) cmc.get("c"));
		}

		k2Candle.setHighPrice(toDouble(cmc.get("h")));
		k2Candle.setLowPrice(toDouble( cmc.get("l")));
		k2Candle.setVolume(toDouble( cmc.get("v")));
		
		k2Candle.setCandleType("ONE_MINUTE");
		
		return k2Candle;
	}
	
	private double toDouble(Object object) {
		if (object instanceof Integer) {
			return ( (double) ((Integer) object).doubleValue());
		} else {
			return (double) object;
		}
	}
}
