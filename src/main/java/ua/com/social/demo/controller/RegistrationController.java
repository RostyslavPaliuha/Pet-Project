package ua.com.social.demo.controller;

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
    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("auth/registration")
    public ResponseEntity registration(@RequestBody FullProfileDto fullProfileDto) {
       try{
           registrationService.register(fullProfileDto);
       }catch(InvalidParameterException ipe){
           return new ResponseEntity(ipe.getMessage(), HttpStatus.CONFLICT);
       }
        return new ResponseEntity("Greetings!",HttpStatus.OK);
    }
}
