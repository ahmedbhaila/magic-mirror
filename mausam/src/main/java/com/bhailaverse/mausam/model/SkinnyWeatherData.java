package com.bhailaverse.mausam.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@Data
@EqualsAndHashCode
@ToString
public class SkinnyWeatherData implements Serializable {
	private Object highTemp;
	private Object lowTemp;
}
