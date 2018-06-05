package com.marin.urlshortener.dao;

import com.marin.urlshortener.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findAccountByAccountId(String accountId);
}


