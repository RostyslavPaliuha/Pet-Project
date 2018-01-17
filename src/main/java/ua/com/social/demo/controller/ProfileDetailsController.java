package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.security.TokenAuthenticationService;
import ua.com.social.demo.service.api.ProfileDetailsService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProfileDetailsController {
    @Autowired
    private ProfileDetailsService profileDetailsService;

    @GetMapping("api/profile/{id}")
    public ProfileDetails get(@PathVariable("id") Integer profileId) {
        return profileDetailsService.get(profileId).get();
    }

    @PutMapping("api/profile/{id}/update")
    public ResponseEntity update(@PathVariable("id") Integer profileId, @RequestBody ProfileDetails profileDetails, HttpServletRequest request) {
        if (profileId == TokenAuthenticationService.getAccountIdFromToken(request.getHeader("Authentication"))) {
            profileDetails.setProfileId(profileId);
            profileDetailsService.update(profileDetails);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
