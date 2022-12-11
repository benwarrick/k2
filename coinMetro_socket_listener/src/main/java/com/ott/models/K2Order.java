package com.ott.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ott.beans.CustomDoubleSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "K2Order")
public class K2Order {

    String OrderId;
    String clientId;
    String currencyPair;
    String type;
    String side;
    double size;
    double price;
    boolean reduceOnly;
    boolean ioc;
    boolean postOnly;
    String status;
    double filledSize;
    double remainingSize;
    double avgFillPrice;
    long creationTime;
    String creationTimeDate;
    double fee;
    String feeCurrency;
    
    

	public String getFeeCurrency() {
		return feeCurrency;
	}
	public void setFeeCurrency(String feeCurrency) {
		this.feeCurrency = feeCurrency;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	public String getCreationTimeDate() {
		return creationTimeDate;
	}
	public void setCreationTimeDate(String creationTimeDate) {
		this.creationTimeDate = creationTimeDate;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String id) {
		this.OrderId = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isReduceOnly() {
		return reduceOnly;
	}
	public void setReduceOnly(boolean reduceOnly) {
		this.reduceOnly = reduceOnly;
	}
	public boolean isIoc() {
		return ioc;
	}
	public void setIoc(boolean ioc) {
		this.ioc = ioc;
	}
	public boolean isPostOnly() {
		return postOnly;
	}
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getFilledSize() {
		return filledSize;
	}
	public void setFilledSize(double filledSize) {
		this.filledSize = filledSize;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getRemainingSize() {
		return remainingSize;
	}
	public void setRemainingSize(double remainingSize) {
		this.remainingSize = remainingSize;
	}
	@JsonSerialize(using = CustomDoubleSerializer.class)
	public double getAvgFillPrice() {
		return avgFillPrice;
	}
	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}
    
    

}
