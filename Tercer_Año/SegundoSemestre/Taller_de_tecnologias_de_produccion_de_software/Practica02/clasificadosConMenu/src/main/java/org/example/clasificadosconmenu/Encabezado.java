package org.example.clasificadosconmenu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.clasificadosconmenu.model.SitioClasificado;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Encabezado", value = "/Encabezado")
public class Encabezado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SitioClasificado sitio = (SitioClasificado) getServletContext().getAttribute("sitio");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<header>");
        out.println("<h1>Bienvenido a " + sitio.getNombre() + "</h1>");
        out.println("<h3>Nuestro mail es " + sitio.getEmail() + "</h3>");
        out.println("<h3>Nuestro telefono es " + sitio.getTelefono() + "</h3>");
        out.println("</header>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}