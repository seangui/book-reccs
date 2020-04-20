package com.bookreccs.dto.tastedive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Info {
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("wUrl")
	private String wUrl;
	
	@JsonProperty("yUrl")
	private String yUrl;
	
	@JsonProperty("yID")
	private String yID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getwUrl() {
		return wUrl;
	}

	public void setwUrl(String wUrl) {
		this.wUrl = wUrl;
	}

	public String getyUrl() {
		return yUrl;
	}

	public void setyUrl(String yUrl) {
		this.yUrl = yUrl;
	}

	public String getyID() {
		return yID;
	}

	public void setyID(String yID) {
		this.yID = yID;
	}

	@Override
	public String toString() {
		return "Info [name=" + name + ", type=" + type + ", wUrl=" + wUrl + ", yUrl=" + yUrl + ", yID=" + yID + "]";
	}
}
