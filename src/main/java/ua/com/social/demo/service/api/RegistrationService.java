package ua.com.social.demo.service.api;

import ua.com.social.demo.dto.FullProfileDto;

public interface RegistrationService {
    public void register(FullProfileDto fullProfileDto);
    public boolean activateAccount(String email, Integer hash);
}
