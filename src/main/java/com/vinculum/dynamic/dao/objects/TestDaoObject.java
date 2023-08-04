package com.vinculum.dynamic.dao.objects;

import java.io.Serializable;

public class TestDaoObject implements Serializable{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3604874679756873860L;
	
	private Long serial;
	private Integer random;
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public Integer getRandom() {
		return random;
	}
	public void setRandom(Integer random) {
		this.random = random;
	}

}
