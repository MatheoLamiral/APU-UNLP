package org.example.blogdemensajes.persistence.dao;

import org.example.blogdemensajes.persistence.emf.EntityManagerListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public abstract class BaseDAO<T> {

    public abstract List<T> getAll();
    public abstract T findById(Long id);

    protected EntityManager getEntityManager(){
        return EntityManagerListener.getEntityManagerFactory().createEntityManager();
    }

    protected abstract Class<T> getEntityClass();

    public void create(T entity){
        EntityManager emf = this.getEntityManager();
        EntityTransaction tx = emf.getTransaction();
        try{
            tx.begin();
            emf.persist(entity);
            tx.commit();
        }catch (Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            System.err.println("Error persisting entity: " + e.getMessage());
        }finally {
            emf.close();
        }
    }
    public void update(T entity){
        EntityManager emf = this.getEntityManager();
        EntityTransaction tx = emf.getTransaction();
        try {
            tx.begin();
            emf.merge(entity);
            tx.commit();
        }catch (Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            System.err.println("Error updating entity: " + e.getMessage());
        }finally {
            emf.close();
        }
    }
    public void delete(T entity){
        EntityManager emf = this.getEntityManager();
        EntityTransaction tx = emf.getTransaction();
        try {
            tx.begin();
            // si la entidad está gestionada, se elimina directamente.
            // Sino, se hace merge primero para gestionarla y luego se elimina.
            emf.remove(emf.contains(entity) ? entity : emf.merge(entity));
            tx.commit();
        }catch (Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            System.err.println("Error deleting entity: " + e.getMessage());
        }finally {
            emf.close();
        }
    }
}
