package ua.com.social.demo.service;

import java.util.Optional;

public interface AccountService<T, N> {
    public Optional<N> persist(T t);

    public boolean delete(Integer id);

    public Optional<T> get(Integer id);

    public Optional<T> getByEmail(String email);
}
