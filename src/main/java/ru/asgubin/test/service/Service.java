package ru.asgubin.test.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findById (ID id);
    void deleteById(ID id);
}
