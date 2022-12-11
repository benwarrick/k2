package com.ott.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CoinMetroOrder")
public class CoinMetroOrder {
	String _id;
	String orderPlatform;
	String buyingCurrency;
	String sellingCurrency;
	String fillStyle;
	double buyingQty;
	double sellingQty;
	String orderType;
	long timeInForce;
	String userID;
	String orderID;
	double boughtQty;
	double soldQty;
	long creationTime;
	long seqNumber;
	long firstFillTime;
	long lastFillTime;
	long completionTime;
	double takerQty;
	double fees;
	boolean isAncillary;
	boolean margin;
	boolean trade;
	List<CoinMetroFill> fills;
	
	public List<CoinMetroFill> getFills() {
		return fills;
	}
	public void setFills(List<CoinMetroFill> fills) {
		this.fills = fills;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getOrderPlatform() {
		return orderPlatform;
	}
	public void setOrderPlatform(String orderPlatform) {
		this.orderPlatform = orderPlatform;
	}
	public String getBuyingCurrency() {
		return buyingCurrency;
	}
	public void setBuyingCurrency(String buyingCurrency) {
		this.buyingCurrency = buyingCurrency;
	}
	public String getSellingCurrency() {
		return sellingCurrency;
	}
	public void setSellingCurrency(String sellingCurrency) {
		this.sellingCurrency = sellingCurrency;
	}
	public String getFillStyle() {
		return fillStyle;
	}
	public void setFillStyle(String fillStyle) {
		this.fillStyle = fillStyle;
	}
	public double getBuyingQty() {
		return buyingQty;
	}
	public void setBuyingQty(double buyingQty) {
		this.buyingQty = buyingQty;
	}
	public double getSellingQty() {
		return sellingQty;
	}
	public void setSellingQty(double sellingQty) {
		this.sellingQty = sellingQty;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public long getTimeInForce() {
		return timeInForce;
	}
	public void setTimeInForce(long timeInForce) {
		this.timeInForce = timeInForce;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public double getBoughtQty() {
		return boughtQty;
	}
	public void setBoughtQty(double boughtQty) {
		this.boughtQty = boughtQty;
	}
	public double getSoldQty() {
		return soldQty;
	}
	public void setSoldQty(double soldQty) {
		this.soldQty = soldQty;
	}
	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	public long getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(long seqNumber) {
		this.seqNumber = seqNumber;
	}
	public long getFirstFillTime() {
		return firstFillTime;
	}
	public void setFirstFillTime(long firstFillTime) {
		this.firstFillTime = firstFillTime;
	}
	public long getLastFillTime() {
		return lastFillTime;
	}
	public void setLastFillTime(long lastFillTime) {
		this.lastFillTime = lastFillTime;
	}
	public long getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(long completionTime) {
		this.completionTime = completionTime;
	}
	public double getTakerQty() {
		return takerQty;
	}
	public void setTakerQty(double takerQty) {
		this.takerQty = takerQty;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public boolean isAncillary() {
		return isAncillary;
	}
	public void setAncillary(boolean isAncillary) {
		this.isAncillary = isAncillary;
	}
	public boolean isMargin() {
		return margin;
	}
	public void setMargin(boolean margin) {
		this.margin = margin;
	}
	public boolean isTrade() {
		return trade;
	}
	public void setTrade(boolean trade) {
		this.trade = trade;
	}
	
	
}
