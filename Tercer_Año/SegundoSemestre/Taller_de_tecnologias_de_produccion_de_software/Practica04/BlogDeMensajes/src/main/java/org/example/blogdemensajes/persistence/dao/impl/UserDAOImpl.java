package org.example.blogdemensajes.persistence.dao.impl;

import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.BaseDAO;
import org.example.blogdemensajes.persistence.emf.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements BaseDAO<User> {

    public UserDAOImpl() {

    }

    public User findByUsername(String username) {
        User user = null;
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            pst.setString(1, username);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("username"));
                user.setId(resultSet.getLong("id"));
            }
            resultSet.close();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
        return user;
    }
    @Override
    public User findById(Long id) {
        User user = null;
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("username"));
                user.setId(resultSet.getLong("id"));
            }
            resultSet.close();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            resultSet.close();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
        return users;
    }

    @Override
    public void create(User user) {
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Users (username) VALUES (?)");
            pst.setString(1, user.getUsername());
            pst.executeUpdate();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE Users SET username = ? WHERE id = ?");
            pst.setString(1, user.getUsername());
            pst.setLong(2, user.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try{
            Connection connection = MyDataSource.getDataSource().getConnection();
            PreparedStatement pst = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            pst.setLong(1, user.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        }catch (Exception e) {
            System.err.println("Error connecting to the database:" + e.getMessage());
        }
    }
}
