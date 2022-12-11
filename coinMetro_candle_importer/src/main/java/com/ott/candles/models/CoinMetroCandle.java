package com.ott.candles.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "candle")
public class CoinMetroCandle {
	long timeframe;
	double h;
	double c;
	long timestamp;
	double v;
	double l;
	double o;
	String pair;
	public long getTimeframe() {
		return timeframe;
	}
	public void setTimeframe(long timeframe) {
		this.timeframe = timeframe;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getV() {
		return v;
	}
	public void setV(double v) {
		this.v = v;
	}
	public double getL() {
		return l;
	}
	public void setL(double l) {
		this.l = l;
	}
	public double getO() {
		return o;
	}
	public void setO(double o) {
		this.o = o;
	}
	public String getPair() {
		return pair;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	
	

}
