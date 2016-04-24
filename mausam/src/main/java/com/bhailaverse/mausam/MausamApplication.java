package com.bhailaverse.mausam;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import com.bhailaverse.mausam.service.DarkSkyWeatherService;
import com.bhailaverse.mausam.service.WeatherService;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class MausamApplication {

	private static final long HOUR_IN_SECONDS = 3600;
	
	@Value("${datasource.redis.url}")
	String redisUrl;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WeatherService weatherService() {
		return new DarkSkyWeatherService();
	}
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() throws Exception {
		URI redisUri = new URI(redisUrl);
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory();
		jedisFactory.setHostName(redisUri.getHost());
		jedisFactory.setPort(redisUri.getPort());
		if(redisUri.getUserInfo() != null) {
			jedisFactory.setPassword(redisUri.getUserInfo().split(":", 2)[1]);
		}
		return jedisFactory;
    }
	
	@Bean
    RedisTemplate<Object, Object> redisTemplate() throws Exception {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager() throws Exception {
        RedisCacheManager cacheManger = new RedisCacheManager(redisTemplate());
        cacheManger.setDefaultExpiration(HOUR_IN_SECONDS);
        return cacheManger;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MausamApplication.class, args);
	}
}
