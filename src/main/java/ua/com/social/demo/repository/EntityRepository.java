package ua.com.social.demo.repository;

public interface EntityRepository<T> {
    Integer create(T entity) throws Exception;

    T read(Integer entityId) throws Exception;

    void update(T entity) throws Exception;

    void delete(Integer entityId) throws Exception;

    java.lang.Integer checkIfExist(String tableName, String columnIdName, java.lang.Integer id) throws Exception;
}
