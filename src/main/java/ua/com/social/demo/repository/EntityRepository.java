package ua.com.social.demo.repository;

import ua.com.social.demo.entity.DomainObject;

public interface EntityRepository<V extends DomainObject> {
    void persist(V object);

    void delete(V object);

    V get(Integer id);
}
