package org.example.clasificadosconmenu;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import org.example.clasificadosconmenu.model.SitioClasificado;

@WebListener
public class ContextListener implements ServletContextListener {

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        String nombre = sce.getServletContext().getInitParameter("nombre");
        String email = sce.getServletContext().getInitParameter("email");
        String telefono = sce.getServletContext().getInitParameter("telefono");

        SitioClasificado sitio = new SitioClasificado(nombre, email, telefono);
        sce.getServletContext().setAttribute("sitio", sitio);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}