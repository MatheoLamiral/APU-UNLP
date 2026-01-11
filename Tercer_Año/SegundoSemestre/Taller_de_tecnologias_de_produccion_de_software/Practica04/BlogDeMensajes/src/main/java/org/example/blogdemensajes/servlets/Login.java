package org.example.blogdemensajes.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.blogdemensajes.models.User;
import org.example.blogdemensajes.persistence.dao.FactoryDAO;
import org.example.blogdemensajes.persistence.dao.impl.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
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
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("VisualizarMensajes");
        }
        else {
            res = "/register.html";
            RequestDispatcher dispatcher = request.getRequestDispatcher(res);
            dispatcher.forward(request, response);
        }
    }
}