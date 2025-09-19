package org.example.clasificados.model;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class User {
    private String username;
    private String password;
    private String perfil;

    public User(String username, String password, String perfil) {
        this.username = username;
        this.password = password;
        this.perfil = perfil;
    }

    public boolean checkCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPerfil() {
        return this.perfil;
    }
}
