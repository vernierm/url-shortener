package com.marin.urlshortener.web.controller;

import com.marin.urlshortener.model.Account;
import com.marin.urlshortener.service.AccountService;
import com.marin.urlshortener.web.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account")
    public AccountResponse addAccount(@RequestBody Account account) {
        return accountService.registerNew(account);
    }

    @GetMapping(value = "/all")
    public List<Account> getAllAccounts(@RequestHeader(value="Authorization", defaultValue="foo") String auth){
        return accountService.allAccounts();
    }
}
