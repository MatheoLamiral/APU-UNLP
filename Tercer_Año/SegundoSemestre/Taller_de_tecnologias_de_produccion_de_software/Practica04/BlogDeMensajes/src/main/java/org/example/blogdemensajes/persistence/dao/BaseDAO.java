package org.example.blogdemensajes.persistence.dao;

import java.util.List;

public interface BaseDAO<T> {

    List<T> getAll();
    T findById(Long id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}
