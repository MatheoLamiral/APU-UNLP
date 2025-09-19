package org.example.sitiodinamico;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "Premio",
        value= "/premio-servlet",
        initParams = {
                @WebInitParam(name = "mensaje", value = "¡Felicitaciones @! eres el visitante número # de nuestro sitio y has sido\n" +
                        "seleccionado para el gran premio TTPS - Cursada APROBADA"),
                @WebInitParam(name = "formato", value = "json")
        })
public class Premio extends HttpServlet {
    private String mensaje;
    private int contador;
    private String ultimo;

    public void init() {
        mensaje = getServletConfig().getInitParameter("mensaje");
        contador = 0;
    }

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("formato").equals("json")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            String jsonResponse = "{ nombre: " + ultimo + "}";
            out.println(jsonResponse);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        contador++;
        this.ultimo = request.getParameter("nombre");
        String mensajeActual = mensaje.replace("@", String.valueOf(request.getParameter("Nombre")));
        mensajeActual = mensajeActual.replace("#", String.valueOf(contador));
        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + mensajeActual + "</h1>");
        out.println("</body></html>");
    }
}
