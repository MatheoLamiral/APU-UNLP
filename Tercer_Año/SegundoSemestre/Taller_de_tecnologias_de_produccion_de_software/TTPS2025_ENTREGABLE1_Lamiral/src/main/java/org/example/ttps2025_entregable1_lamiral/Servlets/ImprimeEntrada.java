package org.example.ttps2025_entregable1_lamiral.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@WebServlet(name = "ServletImprimeEntrada", value = "/ServletImprimeEntrada")
public class ImprimeEntrada extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellido");
        String email = request.getParameter("email");
        String dni = request.getParameter("dni");
        String pelicula = request.getParameter("pelicula");
        String cantidadEntradas = request.getParameter("cantidad");

        int cantidadDeLatasRegalo = (int) this.getServletContext().getAttribute("cantidadLatasRegalo");

        Random random = new Random();
        boolean ganoLata =  (cantidadDeLatasRegalo> 0 && random.nextBoolean());
        if (ganoLata) {
            this.getServletContext().setAttribute("cantidadLatasRegalo", cantidadDeLatasRegalo - 1);
        }
        this.generarCupon(response, nombres, apellidos, dni, pelicula, ganoLata);

    }

    private void generarCupon(HttpServletResponse response, String nombres, String apellidos, String dni, String pelicula, boolean ganoLata) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        String textoQR = "Entrada para la película " + pelicula + ".\n"
                + nombres + " " + apellidos + ", DNI: " + dni + ". \n";
        if (ganoLata) {
            textoQR += "¡¡Felicitaciones!! Te ganaste una LATA DE POCHOCLOS. " +
                    "Podés retirarla con esta entrada.";
        } else {
            textoQR += "¡Seguí viniendo al CINE!";
        }
        try {
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix matrix = qrWriter.encode(textoQR, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);

            BufferedImage bufferedImage = ImageIO.read(this.getServletContext().getResourceAsStream("/WEB-INF/cupon_con_logo.jpeg"));

            BufferedImage combined = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = combined.createGraphics();

            g.drawImage(bufferedImage, 0, 0, null);

            g.drawImage(qrImage, 550, 166, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(apellidos + ", " + nombres + " - DNI: " + dni, 100, 250);
            g.drawString("Película: " + pelicula, 100, 300);

            g.dispose();

            response.setContentType("image/png");
            try (OutputStream out = response.getOutputStream()) {
                ImageIO.write(combined, "PNG", out);
            }

        } catch (WriterException e) {
            throw new IOException("Error generando QR", e);
        }
    }
}