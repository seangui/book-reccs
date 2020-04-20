package com.bookreccs.dto.tastedive;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Similar {
	
	@JsonProperty("Info")
	private Info[] info;
	
	@JsonProperty("Results")
	private Info[] results;

	public Info[] getInfo() {
		return info;
	}

	public void setInfo(Info[] info) {
		this.info = info;
	}

	public Info[] getResults() {
		return results;
	}

	public void setResults(Info[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Similar [info=" + Arrays.toString(info) + ", results=" + Arrays.toString(results) + "]";
	}
	
}
