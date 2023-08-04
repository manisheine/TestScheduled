package com.vinculum.dynamic.events;

import javax.ws.rs.core.Response;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Span;


public abstract class AbstractTraceProvider {

	protected Response enrich(int status, Object entity) {
		Span span = ElasticApm.currentSpan();
		
		final String traceId = span.getTraceId();
	
		
	
	
		System.out.println(traceId);
		
		 
		// System.out.println(sspp);
	
		//String traceId = span.getSpanContext().getTraceId();
		
		//AttributesBuilder attributesBuilder = Attributes.builder();
		
		//attributesBuilder.put("XYZ", "MANISH");
		//Attributes attributes = attributesBuilder.build();
		//span.addEvent("tHIS IS A TEST MESSAGE", attributes, new Date().getTime(), TimeUnit.MILLISECONDS);
		
		return Response.status(status).entity(entity).header("traceId", traceId).build();
	}
}
