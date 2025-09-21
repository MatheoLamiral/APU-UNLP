package org.example.ttps2025_entregable1_lamiral.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "ServletVisualizarEstadisticas", value = "/ServletVisualizarEstadisticas")
public class VisualizarEstadisticas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Estadísticas de Venta de Entradas</title>
                    <link rel="stylesheet" href="styles/estadisticas.css">
                </head>
                <body>
                    <h1>Estadísticas de Venta de Entradas</h1>
                    <div class="card">
                   <div class="toolbar">
                       <span class="hint">Entradas vendidas por película</span>
                       <a class="btn" href="index.html" title="Volver">← Volver</a>
                   </div>
                   <div class="table-wrap">
                       <table>
                           <thead>
                               <tr>
                                   <th>Película</th>
                                   <th>Entradas Vendidas</th>
                               </tr>
                           </thead>
                           <tbody>
                """);
        HashMap<String, Integer> peliculas = (HashMap<String, Integer>) this.getServletContext().getAttribute("ventaPeliculas");
        if (peliculas != null && !peliculas.isEmpty()){
            for (String pelicula : peliculas.keySet()) {
                out.println("<tr>");
                out.println("<td>" + pelicula + "</td>");
                out.println("<td>" + peliculas.get(pelicula) + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='2'>No hay datos disponibles</td></tr>");
        }
        out.println("""
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """);

        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}