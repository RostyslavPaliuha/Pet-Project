package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;


public interface AccountRepository<T> extends EntityRepository<T> {

    T getByEmail(String email) throws EmptyResultDataAccessException;

    void updateEmail(String email, Integer profileId) throws Exception;

    void updatePassword(String password, Integer profileId) throws Exception;


}
