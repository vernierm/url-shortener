package com.marin.urlshortener.dao;

import com.marin.urlshortener.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, Long> {

    Statistic findByShortUrlAndAccountId(String shortUrl, String accountId);

    List<Statistic> findAllByAccountId(String accountId);
}
