package com.marin.urlshortener.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"accountId", "shortUrl"})})
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountId;
    private String shortUrl;
    private int counter = 0;

    public Statistic() {
    }

    public Statistic(String accountId, String shortUrl) {
        this.accountId = accountId;
        this.shortUrl = shortUrl;
        this.counter = 1;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }
}
