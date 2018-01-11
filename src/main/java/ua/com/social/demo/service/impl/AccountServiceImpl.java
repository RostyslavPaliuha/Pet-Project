package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.controller.ExceptionHandlerController;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.impl.AccountRepository;
import ua.com.social.demo.repository.impl.ProfileRepository;
import ua.com.social.demo.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountRepository")
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public boolean persist(String email, String password) {
        try {
            accountRepository.persist(new Account(email, password));
            return true;
        } catch (Exception e) {
            LOG.error("Error while creating profile" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            accountRepository.delete(new Account(id));
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting profile" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Account get(Integer id) {
        try {
            return accountRepository.get(id);
        } catch (Exception e) {
            LOG.error("Error while getting profile" + e.getMessage(), e);
        }
        return new Account();
    }

    public Account getByEmail(Account account) {
        try {
            return accountRepository.getByEmail(account);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        } catch (Exception e) {
            LOG.error("Error while getting by email profile" + e.getMessage(), e);
        }
        return new Account();
    }

}
