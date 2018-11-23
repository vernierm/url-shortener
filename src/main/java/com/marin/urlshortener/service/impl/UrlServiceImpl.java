package com.marin.urlshortener.service.impl;

import com.marin.urlshortener.dao.StatisticsRepository;
import com.marin.urlshortener.dao.UrlRepository;
import com.marin.urlshortener.model.Statistic;
import com.marin.urlshortener.model.Url;
import com.marin.urlshortener.model.UrlMapping;
import com.marin.urlshortener.utils.RandomStringUtils;
import com.marin.urlshortener.service.UrlService;
import com.marin.urlshortener.web.response.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private static final int SHORTENED_URL_LENGTH = 6;

    private UrlRepository urlRepository;
    private StatisticsRepository statisticsRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, StatisticsRepository statisticsRepository) {
        this.urlRepository = urlRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public UrlResponse storeUrl(Url url) {
        while(true) { // UNTIL THE SAVE IS SUCCESSFUL
            String shortUrl = RandomStringUtils.generateRandomString(SHORTENED_URL_LENGTH);
            urlRepository.save(new UrlMapping(shortUrl, url.getUrl(), url.getRedirectType(), 0));

            Optional<UrlMapping> stored = urlRepository.findByShortUrl(shortUrl);
            if (stored.isPresent() && stored.get().getUrl().equals(url.getUrl())) {
                return new UrlResponse(shortUrl);
            }
        }
    }

    @Override
    public String findRealUrl(String shortUrl, String accountId) {
        Optional<UrlMapping> urlMapping = urlRepository.findByShortUrl(shortUrl);
        if (!urlMapping.isPresent())
            return null;

        Statistic statistic = statisticsRepository.findByShortUrlAndAccountId(shortUrl, accountId);
        if (statistic == null) {
            statistic = new Statistic(accountId, shortUrl);
        } else {
            statistic.increaseCounter();
        }
        statisticsRepository.save(statistic);

        return urlMapping.get().getUrl();
    }

    @Override
    public int getRedirectType(String shortUrl) {
        Optional<UrlMapping> stored = urlRepository.findByShortUrl(shortUrl);
        return stored.get().getRedirectType();
    }

    @Override
    public Map<String, Integer> getStatisticForUser(String accountId) {
        List<Statistic> statisticList = statisticsRepository.findAllByAccountId(accountId);
        Map<String, Integer> stats = new HashMap<>();

        for(Statistic s : statisticList) {
            Optional<UrlMapping> urlMapping = urlRepository.findByShortUrl(s.getShortUrl());
            stats.put(urlMapping.get().getUrl(), s.getCounter());
        }
        return stats;
    }
}
