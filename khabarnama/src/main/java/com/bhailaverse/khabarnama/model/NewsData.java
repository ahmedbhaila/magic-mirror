package com.bhailaverse.khabarnama.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsData implements Serializable {
	public NewsData() {
		
	}
	private String title;
	
	@JsonProperty("abstract")
	private String shortDesc;
}
