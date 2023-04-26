package com.cardona.apiservlet.webapp.controllers;

import com.cardona.apiservlet.webapp.services.LoginService;
import com.cardona.apiservlet.webapp.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123456";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()){

            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Hola" + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("    <h1>Hola " + usernameOptional.get() + " Bienvenido seras redireccionado" + "</h1>");
                out.println("<label>\n" +
                        "        <a class=\"btn btn-primary\" href=\"/webapp-session2\" role=\"button\">Regresar</a>\n" +
                        "    </label>");
                out.println("    </body>");
                out.println("</html>");


                resp.setHeader("Refresh", "3; URL=http://localhost:8080/webapp-session2/");

            }


        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");


        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado");
        }


    }
}
