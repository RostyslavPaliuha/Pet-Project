package ua.com.social.demo.repository;

import org.springframework.jdbc.core.JdbcOperations;

public interface Injectable {
    void setJdbcOperations(JdbcOperations jdbcOperations);
    JdbcOperations getJdbcOperations();
}
