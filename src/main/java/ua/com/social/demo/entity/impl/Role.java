package ua.com.social.demo.entity.impl;

import org.springframework.security.core.GrantedAuthority;
import ua.com.social.demo.entity.DomainObject;

public class Role implements DomainObject, GrantedAuthority {
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return "USER";
    }
}
