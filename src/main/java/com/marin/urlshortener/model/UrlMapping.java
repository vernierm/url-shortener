package com.marin.urlshortener.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"shortUrl"})})
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortUrl;
    private String url;
    private int redirectType;
    private int accessCounter;

    public UrlMapping() {
    }

    public UrlMapping(String shortUrl, String url, int redirectType, int accessCounter) {
        this.shortUrl = shortUrl;
        this.url = url;
        this.redirectType = redirectType;
        this.accessCounter = accessCounter;
    }

    public Long getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public int getAccessCounter() {
        return accessCounter;
    }
}
