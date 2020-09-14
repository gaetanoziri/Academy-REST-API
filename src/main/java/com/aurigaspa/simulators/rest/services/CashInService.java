package com.aurigaspa.simulators.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aurigaspa.simulators.api.Finder;
import com.aurigaspa.simulators.rest.services.bean.CashInData;
import com.aurigaspa.simulators.rest.services.bean.CashInErrors;
import com.aurigaspa.simulators.rest.services.bean.CashInRequest;
import com.aurigaspa.simulators.rest.services.bean.CashInResponse;

@Path("/")
public class CashInService {

	private final String MODULE_NAME = "cashin-info-services";
	
	@Path("verifica-disponibilita-versamento")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CashInResponse checkDisponibilita(CashInRequest request) {
		
		CashInResponse result = new CashInResponse(); 
		
		try {
			
			String service_name = "verifica-disponibilita-versamento";
			Finder finder = new Finder(CashInResponse.class);
			result = (CashInResponse)finder.findMessage(MODULE_NAME, service_name, request.getNumeroRapporto());
			
		} catch (Exception e) {
			result.setCode(500);
			System.err.println(e);
		}
		
		return result;
	}
	

}
