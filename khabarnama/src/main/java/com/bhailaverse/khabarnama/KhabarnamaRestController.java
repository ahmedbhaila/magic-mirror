package com.bhailaverse.khabarnama;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhailaverse.khabarnama.model.NewsData;
import com.bhailaverse.khabarnama.service.NewsService;

import lombok.extern.slf4j.Slf4j;
import rx.schedulers.Schedulers;

@Slf4j
@RestController
public class KhabarnamaRestController {
	
	private static final String NEWS_URL = "/news";
	
	@Autowired
	NewsService newsService;
	
	@RequestMapping(NEWS_URL)
	@Cacheable("news")
	@CrossOrigin(origins = "http://localhost:3002")
	public List<NewsData> getNews() throws Exception {
		log.debug("Accessing " + NEWS_URL);
		return newsService.getNews()
				.subscribeOn(Schedulers.computation())
				.toBlocking().single();
				//.single();
	}
}
