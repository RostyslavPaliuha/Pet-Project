package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.rowMapper.AccountRowMapper;

import java.sql.PreparedStatement;


@Repository("accountRepository")
public class AccountRepositoryImpl extends AbstractRepository<Account> implements AccountRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override

    public Integer create(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO account(email,password) VALUES (?,?);", new String[]{"account_id"});
                    ps.setString(1, account.getEmail());
                    ps.setString(2, account.getPassword());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Account read(Integer id) throws Exception {
        return jdbcOperations.queryForObject("SELECT * FROM account WHERE account_id= ?", new Object[]{id}, new AccountRowMapper());
    }

    @Override
    public void delete(Integer accountId) throws Exception {
        jdbcOperations.update("DELETE FROM account WHERE account_id =?", new Object[]{accountId});

    }

    @Override
    public Account getByEmail(String email) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM account WHERE email= ?", new Object[]{email}, new AccountRowMapper());

    }

    @Override
    public void updateEmail(String email, Integer profileId) throws Exception {
        jdbcOperations.update("UPDATE account SET email=? WHERE account_id=(SELECT account_id FROM profile WHERE profile_id=?)", new Object[]{email, profileId});
    }

    @Override
    public void updatePassword(String password, Integer profileId) throws Exception {
        jdbcOperations.update("UPDATE account SET password=? WHERE account_id=(SELECT account_id FROM profile WHERE profile_id=?)", new Object[]{password, profileId});
    }

}
