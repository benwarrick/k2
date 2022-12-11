package com.ott.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "K2Fill")
public class K2Fill {
	double fee;
	double feeRate; 
	String feeCurrency;
	long fillId; 
	String liquidity; 
	String currencyPair; 
	String orderId; 
	long tradeId;
	double price; 
	String side; 
	double size; 
	long fillTime;
	String fillTimeDate;
	
	
	public String getFeeCurrency() {
		return feeCurrency;
	}
	public void setFeeCurrency(String feeCurrency) {
		this.feeCurrency = feeCurrency;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}
	public long getFillId() {
		return fillId;
	}
	public void setFillId(long id) {
		this.fillId = id;
	}
	public String getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(String liquidity) {
		this.liquidity = liquidity;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String market) {
		this.currencyPair = market;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public long getFillTime() {
		return fillTime;
	}
	public void setFillTime(long fillTime) {
		this.fillTime = fillTime;
	}
	public String getFillTimeDate() {
		return fillTimeDate;
	}
	public void setFillTimeDate(String fillTimeDate) {
		this.fillTimeDate = fillTimeDate;
	}
}
