package com.aurigaspa.simulators.rest.services.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//{
//"code": 200,
//"message": "OK",
//"data": {
//"importoCommissione": 5.50,
//"massimoVersabile": 739.00
//}
//}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CashInResponse {

	private int code;
	private String message;
	
	@XmlElement(required = false, nillable = true)
	private CashInData data;
	
	@XmlElement(required = false, nillable = true)
	private CashInErrors errors;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CashInData getData() {
		return data;
	}
	public void setData(CashInData data) {
		this.data = data;
	}
	public CashInErrors getErrors() {
		return errors;
	}
	public void setErrors(CashInErrors errors) {
		this.errors = errors;
	}

}
