package org.example.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import java.io.IOException;

@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "lenguageFilter", value = "lenguageFilter")
        }
)
public class FiltroLenguajeCliente implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String lenguaje = req.getHeader("Accept-Language");

        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle("textos", new Locale(lenguaje) );
        }
        catch (Exception e){
            bundle = ResourceBundle.getBundle("textos", new Locale("es"));
        }

        req.setAttribute("lenguageFilter", bundle);
        filterChain.doFilter(req, servletResponse);
    }
}
