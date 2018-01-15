package ua.com.social.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ua.com.social.demo.entity.impl.Account;

public interface AccountRepository<T extends Account> extends Checkable,Injectable {

    Integer persistAndRetrieveId(String email, String password);

    void delete(Integer accountId) throws Exception;

    T get(Integer id);

    T getByEmail(String email) throws EmptyResultDataAccessException;

    void updateEmail(String email, Integer profileId);

    void updatePassword(String password, Integer profileId);

}
