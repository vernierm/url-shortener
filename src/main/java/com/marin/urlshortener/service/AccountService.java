package com.marin.urlshortener.service;

import com.marin.urlshortener.model.Account;
import com.marin.urlshortener.web.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse registerNew(Account account);

    List<Account> allAccounts();

}
