package ua.com.social.demo.repository.api;

import java.util.List;

public interface EntityRepository<T> {
    Integer create(T entity) throws Exception;

    T read(Integer entityId) throws Exception;

    List<T> readAll() throws Exception;

    void update(T entity) throws Exception;

    void delete(Integer entityId) throws Exception;

    java.lang.Integer checkIfExist(String tableName, String columnIdName, java.lang.Integer id) throws Exception;
}
