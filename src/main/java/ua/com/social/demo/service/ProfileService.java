package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Profile;

public interface ProfileService {
    public Integer persist(Profile profile);

    public Profile get(Integer id);

    public boolean update(Profile profile);

}
