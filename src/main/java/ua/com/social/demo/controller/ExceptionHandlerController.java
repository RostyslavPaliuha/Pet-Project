package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ua.com.social.demo.exceptions.RestException;

@RestController
public class ExceptionHandlerController {
    private static final Logger LOG = Logger.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(RestException.class)
    public String handleException(RestException e) {
        LOG.error("Error: " + e.getMessage(), e);
        return "Error: " + e.getMessage();
    }
}
