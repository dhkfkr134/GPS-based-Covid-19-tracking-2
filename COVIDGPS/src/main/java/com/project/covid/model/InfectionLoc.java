package com.project.covid.model;

import java.util.Date;

public class InfectionLoc {
	private String mcode;
	private String address;
	private String inTime;
	private String outTime;
	
	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getLoc() {
		return address;
	}

	public void setLoc(String loc) {
		this.address = loc;
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

	public InfectionLoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfectionLoc(String mcode, String loc, String inTime, String outTime) {
		super();
		this.mcode = mcode;
		this.address = loc;
		this.inTime = inTime;
		this.outTime = outTime;
	}
	
	
	
}
