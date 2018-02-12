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
import ua.com.social.demo.security.TokenAuthenticationService;
import ua.com.social.demo.security.UserProxy;
import ua.com.social.demo.service.api.AccountService;
import ua.com.social.demo.service.api.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class LoginController {

    static Logger logger = Logger.getLogger(LoginController.class);
    @Value("${security.headerName}")
    private String HEADER_NAME;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserProxy> getAuthenticationToken(@RequestBody Account credential, HttpServletResponse response) {
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (credential.getEmail() != null && credential.getEmail() != "") {
            Optional<Account> accountOptional = accountService.getByEmail(credential.getEmail());
            Account account = accountOptional.isPresent() ? accountOptional.get() : accountOptional.orElse(new Account());
            if (passwordEncoder.matches(credential.getPassword(), account.getPassword())) {
                Integer profileId = profileService.get(account.getAccountId()).get().getProfileId();
                String fullToken = TokenAuthenticationService.createToken(account, profileId);
                logger.info("Token created.");
                response.addHeader(HEADER_NAME, fullToken);
                logger.info("User authentication success. User: " + account.getEmail() + " logged in.");
                responseEntity = ResponseEntity.status(HttpStatus.OK).build();
            } else {
                logger.info("Bad user credentials");
                return responseEntity;
            }
        }
        return responseEntity;
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = httpServletRequest.getHeader("Authentication");
        String email = TokenAuthenticationService.getAccountEmailFromToken(token);
        logger.info("User " + email + " logout");
        return new ResponseEntity(HttpStatus.OK);
    }


}