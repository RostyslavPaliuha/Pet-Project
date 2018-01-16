package ua.com.social.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

public abstract class AbstractRepository<T> implements EntityRepository<T> {
    @Autowired
    private JdbcOperations jdbcOperations;

    public abstract Integer create(T entity) throws Exception;

    public abstract T read(Integer entityId) throws Exception;

    public abstract void update(T entity) throws Exception;

    public abstract void delete(Integer entityId) throws Exception;

    public Integer checkIfExist(String tableName, String columnIdName, Integer recordId) throws Exception {
        return jdbcOperations.queryForObject("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnIdName + "=?", new Object[]{recordId}, Integer.class);
    }

}

