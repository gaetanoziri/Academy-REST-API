package com.aurigaspa.simulators.rest.services.bean;

import javax.xml.bind.annotation.XmlElement;

//{
//"codiceFrazionarioATM": "Field 'codiceFrazionarioATM' size must be between 5
//and 5, 'x' given",
//"codiceFiscale": "Field 'codiceFiscale' must match \"^[A-Z]{6}[0-
//9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]$\", 'x' given",
//"tipoOrdinante": "Field 'tipoOrdinante' must be a valid value, 'x' given",
//"transactionId": "Field 'transactionId' must not be blank, 'null' given",
//"numeroRapporto": "Field 'numeroRapporto' size must be between 12 and 12, 'x'
//given"
//}

public class CashInErrors {

	@XmlElement(required = false, nillable = true)
	private String codiceFrazionarioATM;

	@XmlElement(required = false, nillable = true)
	private String codiceFiscale;

	@XmlElement(required = false, nillable = true)
	private String tipoOrdinante;

	@XmlElement(required = false, nillable = true)
	private String transactionId;

	@XmlElement(required = false, nillable = true)
	private String numeroRapporto;
	
	public String getCodiceFrazionarioATM() {
		return codiceFrazionarioATM;
	}
	public void setCodiceFrazionarioATM(String codiceFrazionarioATM) {
		this.codiceFrazionarioATM = codiceFrazionarioATM;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getTipoOrdinante() {
		return tipoOrdinante;
	}
	public void setTipoOrdinante(String tipoOrdinante) {
		this.tipoOrdinante = tipoOrdinante;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getNumeroRapporto() {
		return numeroRapporto;
	}
	public void setNumeroRapporto(String numeroRapporto) {
		this.numeroRapporto = numeroRapporto;
	}

}
