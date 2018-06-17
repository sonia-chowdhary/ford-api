package com.springmvc.model;

import java.util.Date;

public class DataModel {
	public Date date;
	public String open;
	public String high;
	public String low;
	public String adjClose;
	public String volume;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	

}
