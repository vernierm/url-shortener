package com.marin.urlshortener.service;

import com.marin.urlshortener.dao.AccountRepository;
import com.marin.urlshortener.model.Account;
import com.marin.urlshortener.web.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final int PASSWORD_LENGTH = 8;

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse registerNew(Account account) {
        if(accountRepository.findById(account.getAccountId()).isPresent()) {
            return new AccountResponse(false, "An account with such ID already exists", account.getPassword());
        } else {
            account.setPassword(RandomStringUtils.generateRandomString(PASSWORD_LENGTH));
            account.setRoles(new String[]{"USER"});
            accountRepository.save(account);
            return new AccountResponse(true, "Your account is opened", account.getPassword());
        }
    }

    @Override
    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }
}
