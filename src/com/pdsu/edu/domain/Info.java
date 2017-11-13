package com.pdsu.edu.domain;

import java.io.Serializable;
public class Info implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8213214427489759500L;

	private String sendTime;
	private String infoSent;
	private String getTime;
	private String infoGot;
	private String timeCost;
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getInfoSent() {
		return infoSent;
	}
	public void setInfoSent(String infoSent) {
		this.infoSent = infoSent;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getInfoGot() {
		return infoGot;
	}
	public void setInfoGot(String infoGot) {
		this.infoGot = infoGot;
	}
	public String getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(String timeCost) {
		this.timeCost = timeCost;
	}
	
	

	
}
