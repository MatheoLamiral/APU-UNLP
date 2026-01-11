package org.example.blogdemensajes.persistence.emf;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EntityManagerListener implements ServletContextListener {
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing EntityManagerFactory...");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            emf = Persistence.createEntityManagerFactory("blogMensajesUP");
            System.out.println("EntityManagerFactory initialized");
        }
        catch (Exception e){
            System.err.println("Error loading JDBC Driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Closing EntityManagerFactory...");
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory closed");
        }else {
            System.out.println("EntityManagerFactory was already closed or not initialized");
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
