package com.vinculum.dynamic.pattern;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomMessageConverter extends MessageConverter {

	@Override
	public String convert(ILoggingEvent event) {
	
		return enhance(super.convert(event));
	}

	// implement your "hook ... to at least alter the message text"
	private String enhance(String incoming) {
		return  incoming;
	}
}