package com.vinculum.dynamic.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TestRedisObject implements Serializable{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5688980264711440079L;
	
	private String token;
	private Long ttl;
	public String getToken() {
		return token;
	}
	
	public Long getTtl() {
		//Get current time
		final Long current = new Date().getTime();
		//TTL in Seconds
		Long ttl = this.ttl - current;
		//Check
		if(ttl <= 0) {
			//Set
			ttl=0L;
		}
		//Return TTL
		return TimeUnit.MILLISECONDS.toSeconds(ttl);
	}
	
	public void setToken(final String token, final int minutes) {
		//Create the calendar
		final Calendar calendar = new GregorianCalendar();
		//Add Sent minutes
		calendar.add(Calendar.MINUTE, minutes);
		//Get the time
		this.ttl= calendar.getTime().getTime();
		//Set the token
		this.token = token;
	}
}
