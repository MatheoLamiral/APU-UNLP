package org.example.ttps2025_entregable1_lamiral.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.HashMap;

@WebFilter(filterName = "VentaPeliculas", urlPatterns = "/ServletImprimeEntrada")
public class VentaPeliculas implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
        filterConfig.getServletContext().setAttribute("ventaPeliculas", new HashMap<String, Integer>());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HashMap<String, Integer> peliculas=(HashMap<String, Integer>) request.getServletContext().getAttribute("ventaPeliculas");
        String pelicula = request.getParameter("pelicula");
        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

        peliculas.putIfAbsent(pelicula, 0);
        peliculas.put(pelicula, peliculas.get(pelicula) + cantidad);
        request.getServletContext().setAttribute("ventaPeliculas", peliculas);

        chain.doFilter(request,response);
    }

}
