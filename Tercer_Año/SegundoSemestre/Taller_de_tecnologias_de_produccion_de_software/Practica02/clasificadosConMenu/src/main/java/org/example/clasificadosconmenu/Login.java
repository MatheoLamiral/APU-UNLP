package org.example.clasificadosconmenu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.clasificadosconmenu.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class Login extends HttpServlet {
    private List<User> users;

    public void init() throws ServletException {
        users = List.of(
                new User("admin", "admin123", "admin"),
                new User("publisher1", "pub123", "pub"),
                new User("publisher2", "pub456", "pub")
        );
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usr = request.getParameter("username");
        String pswd = request.getParameter("password");
        if(usr != null && pswd != null) {
            Optional<User> find = this.users.stream()
                                            .filter(user -> user.checkCredentials(usr, pswd))
                                            .findFirst();
            RequestDispatcher dispatcher = request.getRequestDispatcher("menu-servlet");
            if(find.isPresent()){
                request.setAttribute("user", find.get().getPerfil());
                if(dispatcher != null){
                    try {
                        dispatcher.forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                    response.sendRedirect("views/error.html");
            };
        }
    }
}
