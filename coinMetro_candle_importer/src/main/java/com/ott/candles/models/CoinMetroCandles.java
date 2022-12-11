package com.ott.candles.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "candleHistory")
public class CoinMetroCandles {
	ArrayList<CoinMetroCandle> candleHistory = new ArrayList<CoinMetroCandle>();

	public ArrayList<CoinMetroCandle> getCandleHistory() {
		return candleHistory;
	}

	public void setCandleHistory(ArrayList<CoinMetroCandle> candleHistory) {
		this.candleHistory = candleHistory;
	} 
	
}
