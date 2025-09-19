package org.example.clasificadosconmenu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MenuServlet", value = "/menu-servlet")
public class Menu extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String perfil = (String) request.getAttribute("user");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Encabezado");
        if (dispatcher != null) {
           dispatcher.include(request, response);
        }
        if (perfil.equals("admin")) {
            response.sendRedirect("/clasificadosconmenu/views/administrator.html");
        } else if (perfil.equals("pub")) {
            response.sendRedirect("/clasificadosconmenu/views/publisher.html");
        } else {
            response.sendRedirect("/clasificadosconmenu//views/error.html");
        }
    }
}
