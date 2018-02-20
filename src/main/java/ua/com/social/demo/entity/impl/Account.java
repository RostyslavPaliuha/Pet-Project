package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class Account implements DomainObject {
    private int accountId;
    private String email;
    private String password;
    private int activate;


    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    private Integer activateHash;

    public Integer getActivateHash() {
        return activateHash;
    }

    public void setActivateHash(Integer activateHash) {
        this.activateHash = activateHash;
    }

    public int getStatus() {
        return activate;
    }

    public void setStatus(int activate) {
        this.activate = activate;
    }

    public Account(String email, String password, Integer hash) {
        this.email = email;
        this.password = password;
        this.activateHash = hash;
    }

    public Account(int accountId) {
        this.accountId = accountId;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
