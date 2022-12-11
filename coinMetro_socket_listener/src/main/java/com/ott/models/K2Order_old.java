package com.ott.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "K2Order")
public class K2Order_old {

    String orderId;
    String clientId;
    String market;
    String type;
    String side;
    Double size;
    Double price;
    boolean reduceOnly;
    boolean ioc;
    boolean postOnly;
    String status;
    Double filledSize;
    Double remainingSize;
    Double avgFillPrice;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
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
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	public Double getFilledSize() {
		return filledSize;
	}
	public void setFilledSize(Double filledSize) {
		this.filledSize = filledSize;
	}
	public Double getRemainingSize() {
		return remainingSize;
	}
	public void setRemainingSize(Double remainingSize) {
		this.remainingSize = remainingSize;
	}
	public Double getAvgFillPrice() {
		return avgFillPrice;
	}
	public void setAvgFillPrice(Double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}
    
    

}
