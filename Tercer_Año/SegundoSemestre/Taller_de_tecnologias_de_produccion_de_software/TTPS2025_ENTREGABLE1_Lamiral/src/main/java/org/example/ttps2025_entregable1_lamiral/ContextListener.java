package org.example.ttps2025_entregable1_lamiral;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;

@WebListener
public class ContextListener implements ServletContextListener {

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        int cantidadLatas = 0;
        ServletContext context = sce.getServletContext();
        try{
            cantidadLatas = Integer.parseInt(sce.getServletContext().getInitParameter("cantidadLatasRegalo"));
        }catch(Exception e){
            System.out.println("No se ingresó un número, se inicializa la cantidad de latas de regalo en 0 por defecto");
        }
        context.setAttribute("cantidadLatasRegalo", cantidadLatas);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}