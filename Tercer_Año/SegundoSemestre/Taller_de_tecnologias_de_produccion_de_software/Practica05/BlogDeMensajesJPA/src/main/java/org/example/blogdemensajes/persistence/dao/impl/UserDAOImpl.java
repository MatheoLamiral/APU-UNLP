package org.example.blogdemensajes.persistence.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.BaseDAO;

import java.util.List;

public class UserDAOImpl extends BaseDAO<User> {

    public User findByUsername(String username) {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<User> getAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
        query.setParameter("id", id);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
