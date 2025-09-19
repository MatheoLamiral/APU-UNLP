package org.example.filtros;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

@WebServlet(name = "LoginMultilenguaje", value = "/LoginMultilenguaje")
public class LoginMultilenguaje extends HttpServlet {
    private String usr;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle) request.getAttribute("lenguageFilter");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>TTPS</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>"+ bundle.getString("title") +"</h1>");
        out.println("<form action='LoginMultilenguaje' method='post'>");
        out.println("<label for=labelUser>"+bundle.getString("labelUser")+"</label>");
        out.println("<input type=text id=labelUser name=labelUser/>");
        out.println("<label for= labelPassword>" + bundle.getString("labelPassword") +"</label>");
        out.println("<input type='password' id='labelPassword' name='labelPassword'/>");
        out.println("<input type='submit' id='labelSubmit' name='labelSubmit' value="+bundle.getString("labelSubmit")+" />");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle) request.getAttribute("lenguageFilter");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<h1>"+ bundle.getString("message") +"</h1>");
        out.println("<body></html>");
    }
}