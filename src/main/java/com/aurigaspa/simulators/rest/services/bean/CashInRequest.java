package com.aurigaspa.simulators.rest.services.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//{
//"transactionId": "Tran-123",
//"tipoOrdinante": "DR",
//"numeroRapporto": "AB1234567890",
//"codiceATM": "ATM-0123",
//"codiceFrazionarioATM": "C1234",
//"codiceFiscale": "RSSMRA80A01H501U"
//}


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CashInRequest {
	
	private String transactionId;
	private String tipoOrdinante;
	private String numeroRapporto;
	private String codiceATM;
	private String codiceFrazionarioATM;
	private String codiceFiscale;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTipoOrdinante() {
		return tipoOrdinante;
	}
	public void setTipoOrdinante(String tipoOrdinante) {
		this.tipoOrdinante = tipoOrdinante;
	}
	public String getNumeroRapporto() {
		return numeroRapporto;
	}
	public void setNumeroRapporto(String numeroRapporto) {
		this.numeroRapporto = numeroRapporto;
	}
	public String getCodiceATM() {
		return codiceATM;
	}
	public void setCodiceATM(String codiceATM) {
		this.codiceATM = codiceATM;
	}
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

}
