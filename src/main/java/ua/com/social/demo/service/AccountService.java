package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Account;

public interface AccountService {
    public boolean persist(String email, String password);

    public boolean delete(Integer id);

    public Account get(Integer id);

    public Account getByEmail(Account account);
}
