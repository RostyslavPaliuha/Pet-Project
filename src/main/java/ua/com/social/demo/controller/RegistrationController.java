package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.service.impl.RegistrationServiceImpl;

import java.security.InvalidParameterException;

@RestController
public class RegistrationController {
    private static final Logger LOG = Logger.getLogger(RegistrationController.class);
    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("auth/registration")
    public ResponseEntity registration(@RequestBody FullProfileDto fullProfileDto) {
        try {
            registrationService.register(fullProfileDto);
            return new ResponseEntity("Greetings! Dear " + fullProfileDto.getFirstName() + " " + fullProfileDto.getLastName() + ", you successful register on resource, check email that was used when registering to activate your account!", HttpStatus.OK);
        } catch (InvalidParameterException ipe) {
            LOG.error("Invalid credentials." + ipe.getMessage());
            return new ResponseEntity("Invalid credentials. Try Again.", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("auth/activate/{email}")
    public ResponseEntity activateAccount(@PathVariable("email") String email, @RequestParam("hash") Integer hash) {
        try {
            if (registrationService.activateAccount(email, hash)) {
                return new ResponseEntity("Congratulation you activate your account! Now you can login.", HttpStatus.OK);
            } else {
                return new ResponseEntity("Something wrong during activation attempt. Try to request new activation Link on your email.", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity("Something wrong during activation attempt. Try to request new activation Link on your email.", HttpStatus.BAD_REQUEST);
        }
    }
}
