package chat;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;

public class ChatJDBC {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:chat.db")) {
            Scanner sc = new Scanner(System.in);

            String insertSQL = "INSERT INTO mensajes(usuario, texto) VALUES(?, ?)";

            System.out.print("Tu nombre: ");
            String usuario = sc.nextLine();

            while (true) {
                System.out.print(usuario + ": ");
                String texto = sc.nextLine();

                if (texto.equalsIgnoreCase("salir")) {
                    System.out.println("¡Hasta luego!");
                    break;
                }

                // Insertar mensaje
                try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                    ps.setString(1, usuario);
                    ps.setString(2, texto);
                    ps.executeUpdate();
                }

                // Mostrar últimos 5 mensajes
                String selectSQL = "SELECT usuario, texto, fecha FROM mensajes ORDER BY id DESC LIMIT 5";
                try (Statement st = conn.createStatement();
                     ResultSet rs = st.executeQuery(selectSQL)) {

                    System.out.println("\n--- Últimos mensajes ---");
                    while (rs.next()) {
                        System.out.println("[" + rs.getString("fecha") + "] "
                                + rs.getString("usuario") + ": "
                                + rs.getString("texto"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
