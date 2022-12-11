package com.ott.candles.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fTXCandle")
public class K2Candle {
    long startTime;
    long finishTime;
    String startTimeDate;
    String finishTimeDate;
    double openPrice;
    double closePrice;
    double highPrice;
    double lowPrice;
    double volume;
    String candleType;
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
	public String getStartTimeDate() {
		return startTimeDate;
	}
	public void setStartTimeDate(String startTimeDate) {
		this.startTimeDate = startTimeDate;
	}
	public String getFinishTimeDate() {
		return finishTimeDate;
	}
	public void setFinishTimeDate(String finishTimeDate) {
		this.finishTimeDate = finishTimeDate;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getCandleType() {
		return candleType;
	}
	public void setCandleType(String candleType) {
		this.candleType = candleType;
	}
    
    

}
