package com.marin.urlshortener.web.response;

public class StatisticsResponse {

    private String url;
    private int accessesCount;

    public StatisticsResponse() {
    }

    public StatisticsResponse(String url, int accessesCount) {
        this.url = url;
        this.accessesCount = accessesCount;
    }

    public String getUrl() {
        return url;
    }

    public int getAccessesCount() {
        return accessesCount;
    }
}
