package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Account;

public interface AccountRepository<T extends Account> {

    Integer persistAndRetrieveId(String email, String password);

    void delete(Integer accountId) throws Exception;

    T get(Integer id);

    T getByEmail(String email) throws EmptyResultDataAccessException;

    void updateEmail(String email, Integer profileId);

    void updatePassword(String password, Integer profileId);

    Integer checkIfExist(Integer accountId);
}
