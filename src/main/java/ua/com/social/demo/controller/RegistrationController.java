package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.service.impl.RegistrationServiceImpl;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody FullProfileDto fullProfileDto) {
        registrationService.register(fullProfileDto);
        return ResponseEntity.ok().build();
    }
}
