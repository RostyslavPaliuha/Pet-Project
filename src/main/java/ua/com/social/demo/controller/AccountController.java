package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.exceptions.RestException;
import ua.com.social.demo.helper.Ajax;
import ua.com.social.demo.repository.impl.AccountRepository;
import ua.com.social.demo.service.AccountService;

import java.util.Map;

@Controller
public class AccountController extends ExceptionHandlerController {
    private static final Logger LOG = Logger.getLogger(AccountController.class);

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Map<String, Object> persist(@RequestBody Account account) throws RestException {
        try {
            if (account == null || !account.getEmail().equals(null)& !account.getPassword().equals(null)) {
                accountService.persist(account.getEmail(), account.getPassword());
                return Ajax.emptyResponse();
            }
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Map<String, Object> get(@RequestBody Account account) throws RestException {
        try {
            Account recievedAccount = accountService.get(account.getAccountId());
            return Ajax.successResponse(recievedAccount);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}

