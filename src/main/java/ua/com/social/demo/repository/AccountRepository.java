package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Account;

import java.util.List;

public interface AccountRepository<T extends Account> {
    void persist(T account);

    Integer persistAndRetrieveId(T account);

    List<T> getAll(Integer id);

    void delete(Integer accountId);

    T get(Integer id);

    T getByEmail(T account) throws EmptyResultDataAccessException;

    void updateEmail(String email, Integer profileId);

    void updatePassword(String password, Integer profileId);
}
