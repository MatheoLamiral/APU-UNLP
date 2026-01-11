package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablasJDBC {

    public static void main(String[] args) {
        // si no existe chat.db -> la crea
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:chat.db")) {
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS mensajes (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT NOT NULL, texto TEXT NOT NULL,  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

