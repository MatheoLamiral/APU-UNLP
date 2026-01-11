package org.example.blogdemensajes.persistence.dao.impl;

import org.example.blogdemensajes.models.Message;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.BaseDAO;
import org.example.blogdemensajes.persistence.dao.FactoryDAO;
import org.example.blogdemensajes.persistence.emf.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements BaseDAO<Message> {

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<Message>();
        try {
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM Messages"
            );
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                User sender = FactoryDAO.getUserDAO().findById(resultSet.getLong("user_id"));
                Message message = new Message(resultSet.getString("content"), sender);
                message.setId(resultSet.getLong("id"));
                messages.add(message);
            }
        }catch (Exception e){
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
        return messages;
    }

    @Override
    public Message findById(Long id) {
        Message message = null;
        try {
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM Messages WHERE id = ?"
            );
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                User sender = FactoryDAO.getUserDAO().findById(resultSet.getLong("user_id"));
                message = new Message(resultSet.getString("content"), sender);
                message.setId(resultSet.getLong("id"));
            }
        }catch (Exception e){
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
        return message;
    }

    @Override
    public void create(Message message) {
        try {
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO Messages (content, user_id) VALUES (?, ?)"
            );
            pst.setString(1, message.getContent());
            pst.setLong(2, message.getUser().getId());
            pst.executeUpdate();
        }catch (Exception e){
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
