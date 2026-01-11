package org.example.blogdemensajes.persistence.dao.impl;

import jakarta.persistence.TypedQuery;
import org.example.blogdemensajes.models.Message;
import org.example.blogdemensajes.persistence.dao.BaseDAO;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MessageDAOImpl extends BaseDAO<Message> {

    @Override
    public List<Message> getAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<Message> query = em.createNamedQuery("Message.findAll", Message.class);
        return query.getResultList();
    }

    @Override
    public Message findById(Long id) {
        EntityManager em = this.getEntityManager();
        TypedQuery<Message> query = em.createNamedQuery("Message.findById", Message.class);
        query.setParameter("id", id);
        List<Message> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    protected Class<Message> getEntityClass() {
        return Message.class;
    }
}
