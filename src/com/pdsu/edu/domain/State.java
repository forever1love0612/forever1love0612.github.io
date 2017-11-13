package com.pdsu.edu.domain;

import java.io.Serializable;
public class State implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8213214427489759500L;
	private String time;
	private String led1;
	private String led2;
	private String led3;
	private String led4;
	private String led5;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLed1() {
		return led1;
	}
	public void setLed1(String led1) {
		this.led1 = led1;
	}
	public String getLed2() {
		return led2;
	}
	public void setLed2(String led2) {
		this.led2 = led2;
	}
	public String getLed3() {
		return led3;
	}
	public void setLed3(String led3) {
		this.led3 = led3;
	}
	public String getLed4() {
		return led4;
	}
	public void setLed4(String led4) {
		this.led4 = led4;
	}
	public String getLed5() {
		return led5;
	}
	public void setLed5(String led5) {
		this.led5 = led5;
	}


}
