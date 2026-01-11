package org.example.blogdemensajes.servlets;

import com.mysql.cj.Messages;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.blogdemensajes.models.Message;
import org.example.blogdemensajes.persistence.dao.FactoryDAO;

import java.io.IOException;
import java.util.List;
import org.example.blogdemensajes.helpers.Helpers;

@WebServlet(name = "VisualizarMensajes", value = "/VisualizarMensajes")
public class VisualizarMensajes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(!Helpers.check_session(session)) {
            response.sendRedirect("login.html");
        }
        List<Message> messages = FactoryDAO.getMessageDAO().getAll();
        ServletOutputStream out = response.getOutputStream();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Messages</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("</head><body class='bg-light'>");
        out.println("<div class='container d-flex flex-column align-items-center py-5'>");
        out.println("<h1 class='mb-4 text-center'>Mensajes</h1>");
        if(!messages.isEmpty()) {
            for (Message message : messages) {
                String username = (message.getSender() != null) ? message.getSender().getUsername() : "(usuario desconocido)";
                String content = (message.getContent() != null) ? message.getContent() : "";
                out.println("<div class='card mb-2' style='width: 28rem;'>");
                out.println("<div class='card-body text-center'>");
                out.println("<div class='card-title mb-2'><strong>" + username + "</strong></div>");
                out.println("<p class='card-text mb-0'>" + content + "</p>");
                out.println("</div></div>");
            }
        }else {
            out.println("<p class='text-muted text-center'>No hay mensajes para mostrar.</p>");
        }
        out.println("<a href='add_message.html' class='btn btn-primary mt-3'>Agregar Mensaje</a>");
        out.println("</div>");
        out.println("<div class='text-center mt-4'>");
        out.println("<a href='Logout' class='text-decoration-none text-secondary'>Cerrar Sesión</a>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}