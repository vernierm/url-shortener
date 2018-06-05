package com.marin.urlshortener.model;

import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Url {

    @NonNull
    private String url;

    @Min(301)
    @Max(302)
    private int redirectType = 302;

    public Url() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }
}
