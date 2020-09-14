package com.aurigaspa.simulators.api.bean;

import java.util.ArrayList;

public class Response {

	private String id;
	private String type;
	private ArrayList<String> content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getContent() {
		return content;
	}

	public void setContent(ArrayList<String> content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
