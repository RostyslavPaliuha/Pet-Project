package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.AccountRepository;
import ua.com.social.demo.repository.rowMapper.AccountRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLDataException;


@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer persistAndRetrieveId(String email, String password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO account(email,password) VALUES (?,?);", new String[]{"account_id"});
                    ps.setString(1, email);
                    ps.setString(2, password);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(Integer accountId) throws Exception {
        jdbcOperations.update("DELETE FROM account WHERE account_id =?", new Object[]{accountId});

    }

    @Override
    public Account get(Integer id) {
        return jdbcOperations.queryForObject("SELECT * FROM account WHERE account_id= ?", new Object[]{id}, new AccountRowMapper());
    }

    @Override
    public Account getByEmail(Account account) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM account WHERE email= ?", new Object[]{account.getEmail()}, new AccountRowMapper());

    }

    @Override
    public void updateEmail(String email, Integer profileId) {
        jdbcOperations.update("UPDATE account SET email=? WHERE account_id=(SELECT account_id FROM profile WHERE profile_id=?)", new Object[]{email, profileId});
    }

    @Override
    public void updatePassword(String password, Integer profileId) {
        jdbcOperations.update("UPDATE account SET password=? WHERE account_id=(SELECT account_id FROM profile WHERE profile_id=?)", new Object[]{password, profileId});
    }

    @Override
    public Integer checkIfExist(Integer accountId) {
        return jdbcOperations.queryForObject("SELECT COUNT(*) FROM account WHERE account_id=?", new Object[]{accountId}, Integer.class);
    }
}
