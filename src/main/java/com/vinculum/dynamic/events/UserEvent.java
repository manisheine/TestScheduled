package com.vinculum.dynamic.events;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.vinculum.dynamic.constants.Constants;
import com.vinculum.dynamic.service.TestService;



@Path("event")
public class UserEvent extends AbstractTraceProvider{


	private TestService service;

	//Logger for this class
	public static final Logger LOGGER = LoggerFactory.getLogger(UserEvent.class);
	@GET
	@Path("execute")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response send(@QueryParam("requestId") String param) {
		//Put
		MDC.put(Constants.UNIQUE, "EXECUTE");
		//LOG
		LOGGER.info("Request Received Will Send Response After 1 Minute.");
		//Call the method
		service.execute();
		//Log the information
		LOGGER.info(String.format("Response Is Good", param));
		//Remove
		MDC.remove(Constants.UNIQUE);
		//Return
		return enrich(Response.Status.OK.ordinal(),Constants.RESPONSE);
	}

	@GET
	@Path("set")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response set(@QueryParam("key") String key, @QueryParam("value") String value) {
		//Put
		MDC.put(Constants.UNIQUE, "EXECUTE");
		//LOG
		LOGGER.info("Request Received Will Send Response After 1 Minute.");
		//Call the method
		service.set(key, value);
		//Log the information
		LOGGER.info(String.format("Response Is Good", key));
		//Remove
		MDC.remove(Constants.UNIQUE);
		//Return
		return enrich(Response.Status.OK.ordinal(),Constants.RESPONSE);
	}
	
	@GET
	@Path("expire")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response expire(@QueryParam("key") String key) {
		//Put
		MDC.put(Constants.UNIQUE, "EXECUTE");
		//LOG
		LOGGER.info("Request Received Will Send Response After 1 Minute.");
		//Call the method
		service.expires(key);
		//Log the information
		LOGGER.info(String.format("Response Is Good", key));
		//Remove
		MDC.remove(Constants.UNIQUE);
		//Return
		return enrich(Response.Status.OK.ordinal(),Constants.RESPONSE);
	}
	public void setService(TestService service) {
		this.service = service;
	}

}
