package com.marin.urlshortener.service;

import com.marin.urlshortener.model.Url;
import com.marin.urlshortener.web.response.UrlResponse;

import java.util.Map;

public interface UrlService {

    UrlResponse storeUrl(Url url);

    String findRealUrl(String shortUrl, String accountId);

    int getRedirectType(String shortUrl);

    Map<String, Integer> getStatisticForUser(String accountId);
}
