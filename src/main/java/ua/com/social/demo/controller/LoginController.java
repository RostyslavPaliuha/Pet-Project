package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.security.TokenAuthenticationService;
import ua.com.social.demo.security.UserProxy;
import ua.com.social.demo.service.api.AccountService;
import ua.com.social.demo.service.api.ProfileDetailsService;
import ua.com.social.demo.service.api.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class LoginController {

    static Logger LOG = Logger.getLogger(LoginController.class);
    @Value("${security.headerName}")
    private String HEADER_NAME;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileDetailsService profileDetailsService;

    @RequestMapping(value = "auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserProxy> getAuthenticationToken(@RequestBody Account credential, HttpServletResponse response) {
        if (credential.getEmail() != null && credential.getEmail() != "") {
            Optional<Account> accountOptional = accountService.getByEmail(credential.getEmail());
            Account account = accountOptional.isPresent() ? accountOptional.get() : accountOptional.orElse(new Account());
            if (passwordEncoder.matches(credential.getPassword(), account.getPassword()) & account.getStatus() != 0) {
                Integer profileId = profileService.get(account.getAccountId()).get().getProfileId();
                String fullToken = TokenAuthenticationService.createToken(account, profileId);
                response.addHeader(HEADER_NAME, fullToken);
                Optional<ProfileDetails> details = profileDetailsService.get(profileId);
                return new  ResponseEntity(details.get(),HttpStatus.OK);
            } else if (account.getStatus() == 0) {
                LOG.info("Account does not have activate email.");
                return new ResponseEntity("Please check your email, and click on the activate link in it. Or request it again.", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity("Unsuccessful attempt to login.", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = httpServletRequest.getHeader("Authentication");
        String email = TokenAuthenticationService.getAccountEmailFromToken(token);
        LOG.info("User " + email + " logout");
        return new ResponseEntity(HttpStatus.OK);
    }


}