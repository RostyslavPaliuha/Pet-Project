package ua.com.social.demo.repository;

import ua.com.social.demo.entity.DomainObject;
import ua.com.social.demo.entity.impl.Account;

import java.util.List;

public interface ExtendedEntityRepository<V extends DomainObject> {
    Integer persistAndRetrieveId(V object);
    List<V> getAll(Integer id);
}
