package org.example.blogdemensajes.persistence.dao;

import org.example.blogdemensajes.models.Message;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.impl.UserDAOImpl;
import org.example.blogdemensajes.persistence.dao.impl.MessageDAOImpl;

public class FactoryDAO {
    private static UserDAOImpl user;
    private static MessageDAOImpl message;

    public static UserDAOImpl getUserDAO() {
        if (user == null) {
            user = new UserDAOImpl();
        }
        return user;
    }

    public static MessageDAOImpl getMessageDAO() {
        if (message == null) {
            message = new MessageDAOImpl();
        }
        return message;
    }

}
