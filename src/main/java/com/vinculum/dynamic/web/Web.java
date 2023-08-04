package com.vinculum.dynamic.web;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.vinculum.dynamic.constants.Constants;
import com.vinculum.dynamic.events.UserEvent;
/**
 * 
 * @author Manish Anand
 * 
 * This class is responsible for starting up the JDK embedded web server.
 * All properties are configurable and can be injected from spring.
 *
 */
public class Web {

	//Logger for this class
	private static final Logger LOGGER = LoggerFactory.getLogger(Web.class);
	//The Port On Which The Web Server Will Run [This is to be injected from Spring]
	private int port;
	//The Host On Which The Web Server Will Run [This is to be injected from Spring]
	private String host;
	//Define Event
	private UserEvent event;
	//The Initialize Method
	public void init() throws ClassNotFoundException, NoSuchAlgorithmException, KeyManagementException {
		//Put
		MDC.put(Constants.UNIQUE, Constants.WEB);
		//Log the information
		LOGGER.info("Configuring Web Service...");
		//Construct the base URI
		final URI baseUri = UriBuilder.fromUri(host).port(port).build();
		//Create the resource
		final ResourceConfig config = new ResourceConfig();
		

		
		//Set the component path
		config.register(event);
		//Register the Jackson Feature
		config.register(JacksonFeature.class);
		//Log the information
		LOGGER.info("Starting Web Server");
		//Register the resource to the server
		JdkHttpServerFactory.createHttpServer(baseUri, config);
		//Log the information
		LOGGER.info(String.format("Web Server Started On Port: %d", port));
		//Remove
		MDC.remove(Constants.UNIQUE);
	}
	public void setPort(final int port) {
		this.port = port;
	}
	public void setHost(final String host) {
		this.host = host;
	}
	public void setEvent(UserEvent event) {
		this.event = event;
	}
}
