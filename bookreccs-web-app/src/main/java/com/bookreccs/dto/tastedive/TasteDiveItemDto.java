package com.bookreccs.dto.tastedive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TasteDiveItemDto {
	@JsonProperty("Similar")
	private Similar similar;

	public Similar getSimilar() {
		return similar;
	}

	public void setSimilar(Similar similar) {
		this.similar = similar;
	}

	@Override
	public String toString() {
		return "TasteDiveItemDto [similar=" + similar + "]";
	}
}
