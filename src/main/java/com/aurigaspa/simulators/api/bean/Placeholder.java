package com.aurigaspa.simulators.api.bean;

import com.aurigaspa.simulators.api.exceptions.SimulatorAPIException;
import com.aurigaspa.simulators.api.utils.JsonHttpClient;
import com.aurigaspa.simulators.api.utils.Parameters;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Placeholder {
	
	private String bank;
	private String module;
	private String requestId;
	private String messageId;
	private String bankId;
	private String atmId;
	
	
	public Placeholder(String bank, String module, String requestId, String messageId, String bankId, String atmId) {
		this.bank = bank;
		this.module = module;
		this.requestId = requestId;
		this.messageId = messageId;
		this.bankId = bankId;
		this.atmId = atmId;
	}
	

	public String getBank() {
		return bank;
	}

	public String getModule() {
		return module;
	}


	public String getRequestId() {
		return requestId;
	}


	public String getMessageId() {
		return messageId;
	}


	public String getBankId() {
		return bankId;
	}


	public String getAtmId() {
		return atmId;
	}
	
	public static int store(Placeholder placeholder) throws SimulatorAPIException {
		int result = -1;
		
		Gson gson = new Gson();
		JsonObject jsonPlaceholder = new JsonObject();
		jsonPlaceholder = gson.fromJson(gson.toJsonTree(placeholder, Placeholder.class), JsonObject.class);
		
		JsonHttpClient jsonHttpClient = new JsonHttpClient();
		result = jsonHttpClient.doPost(jsonHttpClient.buildURI(Parameters.HOST + Parameters.DB, Parameters.PLACEHOLDER, null), jsonPlaceholder);
		
		return result;
	}
	
}
