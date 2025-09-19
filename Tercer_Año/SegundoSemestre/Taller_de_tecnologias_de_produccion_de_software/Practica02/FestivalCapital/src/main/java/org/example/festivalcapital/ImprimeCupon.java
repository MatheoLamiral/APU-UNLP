package org.example.festivalcapital;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "ImprimeCupon", value = "/ImprimeCupon")
public class ImprimeCupon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("mensaje");

        OutputStream out = response.getOutputStream();
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        // Fondo violeta
        g.setColor(new Color(186, 36, 120));
        g.fillRect(0, 0, 800, 600);

        // Panel izquierdo (verde)
        g.setColor(new Color(0, 200, 150));
        g.fillRoundRect(50, 50, 300, 400, 30, 30);

        // Remera
        BufferedImage remera = ImageIO.read(this.getServletContext().getResourceAsStream("/WEB-INF/remera.png"));
        Image remeraEscalada = remera.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
        g.drawImage(remeraEscalada, 80, 100, null, null);

        // Texto de la remera
        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(text, 170, 290);

        // Panel derecho (texto evento)
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 36));
        g.drawString("28 DE OCTUBRE", 400, 150);

        g.setFont(new Font("SansSerif", Font.PLAIN, 28));
        g.drawString("HIPODROMO", 400, 200);
        g.drawString("DE LA PLATA", 400, 240);

        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.setColor(new Color(255, 255, 0));
        g.drawString("CÓDIGO DE RETIRO", 400, 320);

        g.setFont(new Font("Monospaced", Font.BOLD, 28));
        g.drawString("#" + ThreadLocalRandom.current().nextInt(1000000,99999999), 400, 360);

        ImageIO.write(image, "png", out);
    }
}