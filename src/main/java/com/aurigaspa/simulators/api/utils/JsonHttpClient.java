package com.aurigaspa.simulators.api.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurigaspa.simulators.api.exceptions.SimulatorAPIException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonHttpClient {
	
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(JsonHttpClient.class);
	
	public URI buildURI(String host, String query, Map<String, String> parameters) throws SimulatorAPIException {
		URIBuilder builder = null;
		URI uri = null;
		try {
			builder = new URIBuilder(host + query);

			if(parameters != null)
			{
				for (String parameter : parameters.keySet()) {
					builder.setParameter(parameter, parameters.get(parameter));
				}
			}

			uri = builder.build();
			logger.info("Builded uri: {}", uri.toString());

		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			
			throw new SimulatorAPIException(String.format("Error building URI: {} {} ", host, query), e.getCause());
		}
		return uri;
	}

	public JsonObject doGet(URI uri) throws SimulatorAPIException {
		byte[] response = null;
		JsonObject json = null;

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader(HttpHeaders.AUTHORIZATION, Parameters.AUTH_TOKEN);
			logger.info(httpGet.toString());
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				logger.info(response1.getStatusLine().toString());
				if (response1.getStatusLine().getStatusCode() >= 200
						&& response1.getStatusLine().getStatusCode() < 300) {
					HttpEntity entity1 = response1.getEntity();
					response = EntityUtils.toByteArray(entity1);
				}
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SimulatorAPIException("Error during HTTP request", e.getCause());
		}
		
		if(response!= null) {
			logger.debug("Received JSON: {}", new String(response));
			json = gson.fromJson(new String(response), JsonObject.class);
		}

		return json;
	}

	public int doPost(URI uri, JsonObject object) throws SimulatorAPIException {
		int result = -1;
		
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader(HttpHeaders.AUTHORIZATION, Parameters.AUTH_TOKEN);
			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
			httpPost.setEntity(new StringEntity(gson.toJson(object)));
			logger.info(httpPost.toString());
			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			try {
				logger.info(response1.getStatusLine().toString());
				result = response1.getStatusLine().getStatusCode();
			} finally {
				response1.close();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SimulatorAPIException("Error during HTTP request", e.getCause());
		}

		return result;
	}

}
