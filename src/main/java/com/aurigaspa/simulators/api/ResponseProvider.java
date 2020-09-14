package com.aurigaspa.simulators.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurigaspa.simulators.api.bean.Response;
import com.aurigaspa.simulators.api.exceptions.SimulatorAPIException;

public class ResponseProvider {

    private Logger logger = LoggerFactory.getLogger(ResponseProvider.class);

    private String module;

    private String request;

    private String userId;

    public ResponseProvider() {
        super();
    }
    
    public ResponseProvider(String module, String request, String userId) {
        super();
        this.module = module;
        this.request = request;
        this.userId = userId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Response provideResponse() {
        Finder finder = new Finder(Response.class);
        Response response = new Response();
        try {
            logger.info("Finding the message");
            response = (Response) finder.findMessage(module,request,userId);
            logger.info("Message found");
        } catch (SimulatorAPIException e) {
            logger.error("An error occured ", e);
        }
        return response;
    }
}
