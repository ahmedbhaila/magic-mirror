package com.bhailaverse.mausam.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;


@Builder
@Data
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = WeatherData.WeatherDataBuilder.class)
public class WeatherData implements Serializable {
	
	private Double currentTemp;
	private String currentTempDesc;
	private String alert;
	private String hourlySummary;
	private String dailySummary;
	
	private Double highTemp;
	private Double lowTemp;
	
	private long sunriseTime;
	private long sunsetTime;
	
	@Singular("forecast")
	private List<SkinnyWeatherData> forecast;
	
}
