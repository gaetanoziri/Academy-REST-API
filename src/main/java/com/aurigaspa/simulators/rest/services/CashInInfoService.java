package com.aurigaspa.simulators.rest.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aurigaspa.simulators.rest.services.bean.Response;
import com.aurigaspa.simulators.rest.services.bean.Request;

@Path("/api/v1")
public class CashInInfoService {
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("verifica-disponibilita-versamento")
	public Response CheckDeposit(Request resquest) {
		return new Response();
	}

}
