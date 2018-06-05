package com.marin.urlshortener.service;

import com.marin.urlshortener.dao.AccountRepository;
import com.marin.urlshortener.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByAccountId(accountId);

        if (account == null)
            throw new UsernameNotFoundException(accountId);

        User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(accountId);
        builder.password(passwordEncoder().encode(account.getPassword()));
        builder.roles(account.getRoles());

        return builder.build();
    }
}
