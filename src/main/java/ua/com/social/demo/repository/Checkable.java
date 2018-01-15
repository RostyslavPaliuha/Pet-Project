package ua.com.social.demo.repository;

import org.springframework.jdbc.core.JdbcOperations;

public interface Checkable {

 public Integer checkIfExist(String tableName, String columnIdName, Integer recordId);

}
