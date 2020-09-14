package com.aurigaspa.simulators.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("pippo")
public class TestService {
	
	@GET
	@Path("hello/{name}/{surname}")
	public String GetText(@PathParam("name") String name, @PathParam("surname") String surname) {

		System.out.println();
		return "hello " + name;
	}
	

}
