package com.bhailaverse.mausam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhailaverse.mausam.model.WeatherData;
import com.bhailaverse.mausam.service.WeatherService;

import lombok.extern.slf4j.Slf4j;
import rx.schedulers.Schedulers;

@Slf4j
@RestController
public class MausamRestController {
	
	private static final String WEATHER_URL = "/weather/{latLng:.+}";
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping(WEATHER_URL)
	@Cacheable(value="weatherData")
	@CrossOrigin(origins = "http://localhost:3002")
	public WeatherData getWeather(@PathVariable("latLng") String latLng) throws Exception {
		log.debug("Accessing " + WEATHER_URL + " with " + latLng);
		return weatherService.getWeather(latLng)
				.subscribeOn(Schedulers.computation())
				.toBlocking().single();
				//.first();
	}
}
