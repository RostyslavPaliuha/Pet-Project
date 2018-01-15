package ua.com.social.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

public abstract class AbstractRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    public Integer checkIfExist(String tableName, String columnIdName, Integer recordId) {
        return jdbcOperations.queryForObject("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnIdName + "=?", new Object[]{recordId}, Integer.class);
    }
}

