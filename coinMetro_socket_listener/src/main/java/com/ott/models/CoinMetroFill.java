package com.ott.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CoinMetroFill")
public class CoinMetroFill {
	String _id;
	long seqNumber;
	long timestamp;
	float qty;
	float price;
	String side;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public long getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(long seqNumber) {
		this.seqNumber = seqNumber;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	
	
}
