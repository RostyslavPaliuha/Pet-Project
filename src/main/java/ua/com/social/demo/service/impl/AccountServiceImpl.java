package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.api.AccountService;

import java.util.Optional;

@Service("accountService")
public class AccountServiceImpl implements AccountService<Account, Integer> {
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountRepository")
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Optional persist(Account account) {
        try {
            return Optional.ofNullable(accountRepository.create(account));
        } catch (Exception e) {
            LOG.error("Error while creating profile" + e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer accountId) {
        try {
            if (1 == accountRepository.checkIfExist("account", "account_id", accountId)) {
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
    public Optional get(Integer id) {
        try {
            return Optional.ofNullable(accountRepository.read(id));
        } catch (Exception e) {
            LOG.error("Error while getting profile" + e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Optional getByEmail(String email) {
        try {
            return Optional.ofNullable(accountRepository.getByEmail(email));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        } catch (Exception e) {
            LOG.error("Error while getting by email profile" + e.getMessage(), e);
            return Optional.empty();
        }
    }

}
