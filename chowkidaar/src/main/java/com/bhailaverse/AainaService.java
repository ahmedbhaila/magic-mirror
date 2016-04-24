package com.bhailaverse;

import java.net.URI;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import rx.Observable;
import rx.exceptions.Exceptions;

public class AainaService {
	
	private static final String HTTPS = "https";
	private static final String WEATHER_SERVICE_ID = "mausam";
	private static final String NEWS_SERVICE_ID = "khabarnama";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	private URI generateURI(String serviceId) {
		ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
		return  URI.create(String.format(HTTPS + "://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()));
	}
	
	private Observable<ResponseEntity<String>> makeHttpCall(String serviceId, String path) {
		return Observable.create(sub -> {
			try {
				//ResponseEntity<String> response = restTemplate.getForEntity(generateURI(serviceId) + path, String.class);
				ResponseEntity<String> response = restTemplate.getForEntity("http://" + serviceId + path, String.class);
				
				System.out.println(response.getBody().toString());
				sub.onNext(response);
				sub.onCompleted();
			}
			catch(RestClientException e) {
				sub.onError(e);
			}
			catch(Exception e) {
				sub.onError(e);
			}
		});
	}
	
	public Observable<String> getMirrorData() {
		Observable<JSONObject> weatherObject = makeHttpCall(WEATHER_SERVICE_ID, "/weather/41.9694226,-87.7419139")
			.map(response -> {
				try{
					return new JSONObject(response.getBody());
				}
				catch(Throwable t) {
					throw Exceptions.propagate(t);
				}
			});
		
		
		Observable<JSONArray> newsObject = makeHttpCall(NEWS_SERVICE_ID, "/news")
				.map(response -> {
					try{
						return new JSONArray(response.getBody());
					}
					catch(Throwable t) {
						throw Exceptions.propagate(t);
					}
				});	
		
		return Observable.zip(weatherObject, newsObject, (w,n) -> {
			JSONObject mirrorObject = new JSONObject();
			try{	
				mirrorObject.put("weather", w);
				mirrorObject.put("news", n);
			}
			catch(Throwable t) {
				Exceptions.propagate(t);
			}
			return mirrorObject;
		}).map(json -> json.toString());
	}
}
