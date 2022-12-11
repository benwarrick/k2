package com.ott.candles.beans;

import java.util.ArrayList;

import org.apache.camel.Body;
import org.apache.camel.Header;

import com.ott.candles.models.CoinMetroCandle;
import com.ott.candles.models.CoinMetroCandles;


public class MessageFilters {
	public CoinMetroCandles removeDuplicateCandles(@Body CoinMetroCandles candles) {
		
		for (int i=0; i<candles.getCandleHistory().size()-1; i++) {
			if (candles.getCandleHistory().get(i).getTimestamp() == candles.getCandleHistory().get(i+1).getTimestamp()) {
				candles.getCandleHistory().remove(i);
				System.out.println("Removing " + ((int)i));
				i--;
			}
		}
		
		return candles;
	}
	
	public CoinMetroCandles removePartialCandles(@Header("lastFinishTime") long lastFinishTime, @Body CoinMetroCandles payload) {
		//System.out.println(payload.getResult().size());
		for(int i = 0; i<payload.getCandleHistory().size(); i++) {
			System.out.println(payload.getCandleHistory().get(i).getTimestamp());
			if (!hasCandleTime(payload.getCandleHistory().get(i).getTimestamp()+60000, payload.getCandleHistory())) {
				System.out.println("Removing, young " + payload.getCandleHistory().get(i).getTimestamp());
				payload.getCandleHistory().remove(i);
			}
			else if (payload.getCandleHistory().get(i).getTimestamp() < lastFinishTime) {
				System.out.println("Removeing, old " + payload.getCandleHistory().get(i).getTimestamp());
				payload.getCandleHistory().remove(i);
			}
		}		
		//System.out.println(payload.getResult().size());
		return payload; 
	}
	
	private boolean hasCandleTime(long time, ArrayList<CoinMetroCandle> candles) {
		for (CoinMetroCandle c : candles) {
			if (c.getTimestamp() == time) return true;
		}
		return false;
	}
}
