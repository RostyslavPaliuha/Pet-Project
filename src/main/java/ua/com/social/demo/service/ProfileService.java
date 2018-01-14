package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Profile;

import java.util.Optional;

public interface ProfileService {
    public Optional<Integer> persist(Profile profile);

    public Optional<Profile> get(Integer id);

    public boolean update(Profile profile);

}
