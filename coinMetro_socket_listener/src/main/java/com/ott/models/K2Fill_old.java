package com.ott.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "K2Fill")
public class K2Fill_old {
	double fee;
	double feeRate;
	String feeCurrency;
	long id; 
	String liquidity; 
	String market; 
	long orderId; 
	long tradeId;
	double price; 
	String side; 
	double size; 
	String time; 
	String type;
	
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(String liquidity) {
		this.liquidity = liquidity;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 

	
}
