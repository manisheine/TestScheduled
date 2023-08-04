package com.vinculum.dynamic.pattern;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * Logback appender which adds a log to current trace span event
 */
public class LogbackSpanEventAppender extends AppenderBase<LoggingEvent> {
	@Override
	protected void append(LoggingEvent event) {
		
		/*
		Span span = Span.current();

		AttributesBuilder attributesBuilder = Attributes.builder();
		// copy all MDC properties, you can leave it out or take only useful information to avoid storing unnecessary data
		event.getMDCPropertyMap().forEach(attributesBuilder::put);

		Marker marker = event.getMarker();
		attributesBuilder.put("marker", marker != null ? marker.getName() : null);

		Attributes attributes = attributesBuilder.build();
		
		//String uniqueid = attributes.get(AttributeKey.stringKey("uniqueid"));
		
		span.addEvent(event.getFormattedMessage(), attributes, event.getTimeStamp(), TimeUnit.MILLISECONDS);
		*/

	}
}