package com.bhailaverse.mausam.service;

import com.bhailaverse.mausam.model.WeatherData;

import rx.Observable;

public interface WeatherService {
	public Observable<WeatherData> getWeather(String latLng);
}
