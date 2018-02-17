package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
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
    private static final Logger LOG = Logger.getLogger(ProfileDetailsController.class);
    @Autowired
    private ProfileDetailsService profileDetailsService;

    @GetMapping("api/profile/{id}")
    public ResponseEntity get(@PathVariable("id") Integer profileId) {
        try {
            ProfileDetails details = profileDetailsService.get(profileId).get();
            return new ResponseEntity(details, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to get profile details. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to get profile details.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("api/profile/{id}/update")
    public ResponseEntity update(@PathVariable("id") Integer profileId, @RequestBody ProfileDetails profileDetails, HttpServletRequest request) {
        try {
            if (profileId == TokenAuthenticationService.getAccountIdFromToken(request.getHeader("Authentication"))) {
                profileDetails.setProfileId(profileId);
                profileDetailsService.update(profileDetails);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to update profile. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to update profile.", HttpStatus.BAD_REQUEST);
        }

    }
}
