package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Account;

import java.util.Optional;

public interface AccountService {
    public boolean persist(String email, String password);

    public boolean delete(Integer id);

    public Optional<Account> get(Integer id);

    public Optional<Account> getByEmail(Account account);
}
