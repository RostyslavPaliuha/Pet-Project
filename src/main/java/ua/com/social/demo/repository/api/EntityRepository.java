package ua.com.social.demo.repository.api;

import java.util.List;

public interface EntityRepository<E> {
    Integer create(E entity) throws Exception;

    E read(Integer entityId) throws Exception;

    List<E> readAll() throws Exception;

    void update(E entity) throws Exception;

    void delete(Integer entityId) throws Exception;

    java.lang.Integer checkIfExist(String tableName, String columnIdName, java.lang.Integer id) throws Exception;
}
