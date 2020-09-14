package com.google.gson;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class XMLGregorianCalendarDeserializer implements JsonDeserializer<XMLGregorianCalendar> {

	public XMLGregorianCalendar deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		// Easily parse the adapter class first
		XMLGregoriancalendarAdapterClass ac = new Gson().fromJson(json, XMLGregoriancalendarAdapterClass.class);
		try {
			// Then return a new newXMLGregorianCalendar
			// using values in adapter class
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(ac.getYear(), ac.getMonth(), ac.getDay(), ac.getHour(),        
            ac.getMinute(), ac.getSecond(),ac.getFractionalSecond(), ac.getTimezone());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}