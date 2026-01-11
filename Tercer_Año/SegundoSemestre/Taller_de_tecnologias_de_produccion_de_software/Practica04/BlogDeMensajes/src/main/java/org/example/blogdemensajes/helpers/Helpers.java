package org.example.blogdemensajes.helpers;

import jakarta.servlet.http.HttpSession;

public class Helpers {

    public static boolean check_session(HttpSession session) {
        return session.getAttribute("username") != null;
    }

}
