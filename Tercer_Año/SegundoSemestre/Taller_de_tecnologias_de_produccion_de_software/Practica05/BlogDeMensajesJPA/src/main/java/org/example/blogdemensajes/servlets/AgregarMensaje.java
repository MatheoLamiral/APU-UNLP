package org.example.blogdemensajes.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.blogdemensajes.helpers.Helpers;
import org.example.blogdemensajes.models.Message;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.BaseDAO;
import org.example.blogdemensajes.persistence.dao.FactoryDAO;
import org.example.blogdemensajes.persistence.dao.impl.MessageDAOImpl;
import org.example.blogdemensajes.persistence.dao.impl.UserDAOImpl;

import java.io.IOException;
import org.example.blogdemensajes.helpers.Helpers;

@WebServlet(name = "AgregarMensaje", value = "/AgregarMensaje")
public class AgregarMensaje extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(!Helpers.check_session(session)) {
            response.sendRedirect("login.html");
        }
        response.sendRedirect("add_message.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(!Helpers.check_session(session)) {
            response.sendRedirect("login.html");
        }
        String sender = (String) session.getAttribute("username");
        String message = request.getParameter("message");

        UserDAOImpl userDAO = FactoryDAO.getUserDAO();
        User user = userDAO.findByUsername(sender);
        if (user == null) {
            user = new User(sender);
            userDAO.create(user);
            user = userDAO.findByUsername(sender);
        }

        MessageDAOImpl messageDAO = FactoryDAO.getMessageDAO();
        messageDAO.create(new Message(message, user));
        response.sendRedirect(request.getContextPath() + "/VisualizarMensajes");
    }
}