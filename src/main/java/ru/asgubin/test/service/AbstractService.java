package ru.asgubin.test.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T, ID, R extends JpaRepository<T, ID>> implements Service<T, ID> {

    public final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
