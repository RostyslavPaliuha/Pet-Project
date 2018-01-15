package ua.com.social.demo.repository;

import org.springframework.jdbc.core.JdbcOperations;

public interface Checkable {

    default public Integer checkIfExist(JdbcOperations jdbcOperations,String tableName, String columnIdName, Integer recordId) {
        return jdbcOperations.queryForObject("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnIdName + "=?", new Object[]{recordId}, Integer.class);
    }

}
