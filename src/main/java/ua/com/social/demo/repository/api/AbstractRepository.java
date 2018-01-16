package ua.com.social.demo.repository.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public abstract class AbstractRepository<T> implements EntityRepository<T> {
    @Autowired
    private JdbcOperations jdbcOperations;

    public Integer create(T entity) throws Exception {
        return null;
    }

    public T read(Integer entityId) throws Exception {
        return null;
    }

    public List<T> readAll() throws Exception {
        return null;
    }

    public void update(T entity) throws Exception {

    }

    public void delete(Integer entityId) throws Exception {

    }

    public Integer checkIfExist(String tableName, String columnIdName, Integer recordId) throws Exception {
        return jdbcOperations.queryForObject("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnIdName + "=?", new Object[]{recordId}, Integer.class);
    }

}

