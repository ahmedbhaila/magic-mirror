package com.bhailaverse.khabarnama.service;

import java.util.List;

import com.bhailaverse.khabarnama.model.NewsData;

import rx.Observable;

public interface NewsService {
	public Observable<List<NewsData>> getNews();
}
