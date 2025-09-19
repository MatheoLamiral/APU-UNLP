package org.example.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "filterLogAccesos", value = "logAccesos")
        }
)
public class FiltroLogDeAccesos implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String dirIp = req.getRemoteAddr();
        String usrAgnt = req.getHeader("User-Agent");
        String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String method = req.getMethod();
        String resource = req.getRequestURI();
        String version = req.getProtocol();

        System.out.println(dirIp + " - " + usrAgnt + " [" + dateAndTime + "] " + '"' + method + " " + resource + " " + version + '"');

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
