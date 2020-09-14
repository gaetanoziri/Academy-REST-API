package com.aurigaspa.simulators.api;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurigaspa.simulators.api.exceptions.SimulatorAPIException;
import com.aurigaspa.simulators.api.utils.Initializer;
import com.aurigaspa.simulators.api.utils.JsonHttpClient;
import com.aurigaspa.simulators.api.utils.Parameters;
import com.google.gson.BigIntegerDeserializer;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.LongDeserializer;
import com.google.gson.XMLGregorianCalendarDeserializer;

public class Finder {

	private Class type;

	private Gson gson;

	private JsonHttpClient httpclient;

	private static Logger logger = LoggerFactory.getLogger(Finder.class);

	static {
		Initializer.initialize();
	}

	public Finder(Class type, ExclusionStrategy strategy) {
		this.type = type;
		GsonBuilder gsonBuilder = new GsonBuilder();
		if (strategy != null) {
			gsonBuilder.setExclusionStrategies(strategy);
		}
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarDeserializer());
		/*
		 * è necessario registrare l'adapter sia per il tipo Wrapper che primitivo
		 */
		gsonBuilder.registerTypeAdapter(long.class, new LongDeserializer());
		gsonBuilder.registerTypeAdapter(Long.class, new LongDeserializer());
		gsonBuilder.registerTypeAdapter(BigInteger.class, new BigIntegerDeserializer());
		this.gson = gsonBuilder.create();
		this.httpclient = new JsonHttpClient();
	}

	public Finder(Class type) {
		this(type, null);
	}

	public Object FindMessageByMap(String module, String requestId, String bankId, String atmId,
			Map<String, String> filterKeys) throws SimulatorAPIException {
		JsonObject filter = createFilter(filterKeys);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("filter", gson.toJson(filter));
		parameters.put("sort", "{'_id':-1}");
		String host = getHost();
		JsonObject placeholder = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, Parameters.PLACEHOLDER, parameters));
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host, getMessageQuery(placeholder), new HashMap<String, String>()));

		return getMessageObject(message);
	}

	public Object findMessageByMap(Map<String, String> filterKeys, Map<String, String> defaultFilterKeys)
			throws SimulatorAPIException {

		try {

			return FindMessageByMap(null, null, null, null, filterKeys);
		} catch (SimulatorAPIException e) {

			if (defaultFilterKeys != null) {

				logger.warn("WARNING: Message not found for filter: ", filterKeys, ".");
				logger.warn("WARNING: Searching message using default filter: ", defaultFilterKeys, ".");

				return FindMessageByMap(null, null, null, null, defaultFilterKeys);
			}

			throw e;
		}
	}

	@Deprecated
	public Object findMessage(String module, String requestId, String bankId, String atmId)
			throws SimulatorAPIException {
		Map<String, String> filterKeys = new HashMap<String, String>();
		filterKeys.put("module", module);
		filterKeys.put("requestId", requestId);
		filterKeys.put("bankId", bankId);
		filterKeys.put("atmId", atmId);
		JsonObject filter = createFilter(filterKeys);

		// JsonObject filter = createFilter(module, requestId, bankId, atmId);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("filter", gson.toJson(filter));
		parameters.put("sort", "{'_id':-1}");
		String host = getHost();

		JsonObject placeholder = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, Parameters.PLACEHOLDER, parameters));
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host, getMessageQuery(placeholder), new HashMap<String, String>()));

		return getMessageObject(message);
	}

	@Deprecated
	public Object findMessageByPan(String module, String requestId, String bankId, String pan)
			throws SimulatorAPIException {
		Map<String, String> filterKeys = new HashMap<String, String>();
		filterKeys.put("module", module);
		filterKeys.put("requestId", requestId);
		filterKeys.put("bankId", bankId);
		filterKeys.put("pan", pan);
		JsonObject filter = createFilter(filterKeys);

		// JsonObject filter = createFilter(module, requestId, bankId, atmId);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("filter", gson.toJson(filter));
		parameters.put("sort", "{'_id':-1}");

		String host = getHost();
		JsonObject placeholder = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, Parameters.PLACEHOLDER, parameters));
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host, getMessageQuery(placeholder), new HashMap<String, String>()));

		return getMessageObject(message);
	}

	public Object findMessage(String module, String requestId, String userId) throws SimulatorAPIException {
		Map<String, String> filterKeys = new HashMap<String, String>();
		filterKeys.put("module", module);
		filterKeys.put("requestId", requestId);
		filterKeys.put("userId", userId);
		JsonObject filter = createFilter(filterKeys);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("filter", gson.toJson(filter));
		parameters.put("sort", "{'_id':-1}");

		String host = getHost();
		JsonObject placeholder = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, Parameters.PLACEHOLDER, parameters));
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host, getMessageQuery(placeholder), new HashMap<String, String>()));

		return getMessageObject(message);
	}

	public Object findMessage(String module, Map<String, String> filterKeys) throws SimulatorAPIException {
		filterKeys.put("module", module);
		JsonObject filter = createFilter(filterKeys);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("filter", gson.toJson(filter));
		parameters.put("sort", "{'_id':-1}");
		logger.info("running doGet....");
		String host = getHost();
		logger.info("Parameters.HOST: " + host + " Parameters.DB: " + Parameters.DB + "Parameters.PLACEHOLDER: "
				+ Parameters.PLACEHOLDER + "parameters: " + parameters);
		JsonObject placeholder = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, Parameters.PLACEHOLDER, parameters));
		logger.info("running doGet2....");
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host, getMessageQuery(placeholder), new HashMap<String, String>()));
		logger.info("doGet runned....");
		return getMessageObject(message);
	}

	public Object getMessage(String id) throws SimulatorAPIException {

		logger.info("running doGet....");
		String host = getHost();
		logger.info("Parameters.HOST: " + host + "id message: " + id);
		JsonObject message = httpclient
				.doGet(httpclient.buildURI(host + Parameters.DB, "/message/" + id, new HashMap<String, String>()));
		logger.info("doGet runned....");
		return getMessageObject(message);
	}

	private JsonObject createFilter(Map<String, String> filterKeys) {
		JsonObject filter = new JsonObject();
		for (String key : filterKeys.keySet()) {
			filter.addProperty(key, filterKeys.get(key));
		}

		return filter;
	}

	private String getMessageQuery(JsonObject placeholder) throws SimulatorAPIException {
		String query = null;
		try {
			query = placeholder.get("_embedded").getAsJsonArray().get(0).getAsJsonObject().get("_links")
					.getAsJsonObject().get("message").getAsJsonObject().get("href").getAsString();
		} catch (Exception e) {
			throw new SimulatorAPIException("Field not found in JsonObject: $._embedded[0]._links.message.href",
					e.getCause());
		}
		return query;
	}

	private Object getMessageObject(JsonObject message) throws SimulatorAPIException {
		Object messageObject = null;
		JsonElement response = null;

		response = message.get("response");

		if (response == null) {

			throw new SimulatorAPIException("Field not found in JsonObject: $.response");
		}

		try {

			messageObject = gson.fromJson(response, type);
		} catch (Exception e) {
			throw new SimulatorAPIException("The JSON is an invalid representation of " + type.getName(), e);
		}
		return messageObject;
	}

	private String getHost() {
		return Initializer.getParametersReader().get(Parameters.HOST, Parameters.HOST_DEFAULT);
	}

	public Gson getGsonInstance() {
		return gson;
	}
}
