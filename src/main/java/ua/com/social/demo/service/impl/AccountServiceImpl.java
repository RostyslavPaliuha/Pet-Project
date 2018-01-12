package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.AccountRepository;
import ua.com.social.demo.repository.ProfileRepository;
import ua.com.social.demo.service.AccountService;

import java.util.Optional;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountRepository")
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Optional<Integer> persist(String email, String password) {

        Optional<Integer> integerOptional = Optional.empty();
        try {
            integerOptional = Optional.ofNullable(accountRepository.persistAndRetrieveId(email, password));
            return integerOptional;
        } catch (Exception e) {
            LOG.error("Error while creating profile" + e.getMessage(), e);
            return integerOptional;
        }
    }

    @Override
    public boolean delete(Integer accountId) {
        try {
            if (1 == accountRepository.checkIfExist(accountId)) {
                accountRepository.delete(accountId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Error while deleting profile" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<Account> get(Integer id) {
        Optional<Account> optionalAccount = Optional.empty();
        try {
            optionalAccount = Optional.ofNullable(accountRepository.get(id));
            return optionalAccount;
        } catch (Exception e) {
            LOG.error("Error while getting profile" + e.getMessage(), e);
            return optionalAccount;
        }
    }

    public Optional<Account> getByEmail(Account account) {
        Optional<Account> optionalAccount = Optional.empty();
        try {
            optionalAccount = Optional.ofNullable(accountRepository.getByEmail(account));
            return optionalAccount;
        } catch (EmptyResultDataAccessException exception) {
            return optionalAccount;
        } catch (Exception e) {
            LOG.error("Error while getting by email profile" + e.getMessage(), e);
            return optionalAccount;
        }
    }

}
