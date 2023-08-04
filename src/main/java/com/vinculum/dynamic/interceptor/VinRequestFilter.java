package com.vinculum.dynamic.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

@Priority(Priorities.HEADER_DECORATOR)
public class VinRequestFilter implements ContainerResponseFilter, ContainerRequestFilter {

	

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)throws IOException {
		final MultivaluedMap<String, String>  map= requestContext.getHeaders();
		for(final Map.Entry<String,List<String>> headers :map.entrySet()) {
			
			final String key = headers.getKey();
			final List<String> values = headers.getValue();
			System.out.println(String.format("Key: %s Value: %s", key, values));
		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		
	}
}