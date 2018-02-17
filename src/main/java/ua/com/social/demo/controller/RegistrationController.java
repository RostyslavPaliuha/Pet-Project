package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
            return new ResponseEntity("Greetings! Dear " + fullProfileDto.getFirstName() + " " + fullProfileDto.getLastName() + ", you successful register on resource!", HttpStatus.OK);
        } catch (InvalidParameterException ipe) {
            LOG.error("Invalid credentials." + ipe.getMessage());
            return new ResponseEntity("Invalid credentials. Try Again.", HttpStatus.CONFLICT);
        }
    }
}
