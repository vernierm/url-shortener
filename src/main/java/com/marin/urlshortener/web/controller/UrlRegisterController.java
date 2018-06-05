package com.marin.urlshortener.web.controller;

import com.marin.urlshortener.model.Url;
import com.marin.urlshortener.service.UrlService;
import com.marin.urlshortener.web.response.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpHeaders.LOCATION;

@RestController
public class UrlRegisterController {

    @Autowired
    private UrlService urlService;

    @PostMapping(value = "/register")
    public UrlResponse registerUrl(@RequestBody @Valid Url url) {
        return urlService.storeUrl(url);
    }

    @GetMapping(value="/statistic/{accountId}")
    public Map<String, Integer> getStatisticsForUser(@PathVariable String accountId) {
        return urlService.getStatisticForUser(accountId);
    }

    @GetMapping(value="/{accountId}/{shortUrl}")
    public void redirectUserToRealUrl(@PathVariable String accountId, @PathVariable String shortUrl, HttpServletResponse response) {
        String url = urlService.findRealUrl(shortUrl, accountId);
        if (url == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            int redirectType = urlService.getRedirectType(shortUrl);
            response.setHeader(LOCATION, url);
            response.setStatus(redirectType);
        }
    }
}
