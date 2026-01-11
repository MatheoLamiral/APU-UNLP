package org.example.blogdemensajes.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.FactoryDAO;
import org.example.blogdemensajes.persistence.dao.impl.UserDAOImpl;

import java.io.IOException;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAOImpl userDAO = FactoryDAO.getUserDAO();
        User user = userDAO.findByUsername(username);
        String res;
        if (user != null) {
            res = "/register.html";
        }
        else {
            userDAO.create(new User(username));
            res = "/login.html";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(res);
        dispatcher.forward(request, response);
    }
}