package com.project.covid.model;

import java.sql.Date;

public class LocationVO {
	private String id;
	private String mcode;
	private String loc;
	private String inTime;
	private String outTime;
	private String address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocationVO(String id, String mcode, String loc, String inTime, String outTime, String address) {
		super();
		this.id = id;
		this.mcode = mcode;
		this.loc = loc;
		this.inTime = inTime;
		this.outTime = outTime;
		this.address = address;
	}
	public LocationVO() {
		super();
	}
	
}
