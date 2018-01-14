package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.ProfileDetails;

import java.util.Optional;

public interface ProfileDetailsService {
    public Optional<Integer> persist(ProfileDetails profileDetails);

    public Optional<ProfileDetails> get(Integer profileId);

    public boolean update(ProfileDetails profileDetails);
}
