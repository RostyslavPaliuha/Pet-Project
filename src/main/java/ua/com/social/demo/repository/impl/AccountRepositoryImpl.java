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
import java.sql.Types;
import java.util.List;


@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Account account) {
        Object[] params = new Object[]{account.getEmail(), account.getPassword()};
        int[] types = new int[]{Types.VARCHAR, Types.CHAR};
        jdbcOperations.update("INSERT INTO account(email,password) VALUES (?,?);", params, types);
    }

    @Override
    public Integer persistAndRetrieveId(Account account) {
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
    public List<Account> getAll(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer accountId) {
        jdbcOperations.update("DELETE FROM account WHERE account_id =?;", accountId);
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
}
