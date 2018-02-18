package ua.com.social.demo.repository.api;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Account;


public interface AccountRepository extends EntityRepository<Account> {

    Account getByEmail(String email) throws EmptyResultDataAccessException;

    void updateEmail(String email, Integer profileId) throws Exception;

    void updatePassword(String password, Integer profileId) throws Exception;

    boolean updateActivationStatus(String email);
}
