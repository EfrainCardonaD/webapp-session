package com.cardona.apiservlet.webapp.controllers;

import com.cardona.apiservlet.webapp.models.Producto;
import com.cardona.apiservlet.webapp.services.LoginService;
import com.cardona.apiservlet.webapp.services.LoginServiceSessionImpl;
import com.cardona.apiservlet.webapp.services.ProductoService;
import com.cardona.apiservlet.webapp.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n");
            out.println("    <title></title>");
            out.println("    </head>");
            out.println("    <body>");


            out.println("    <h1>Listado de productos!</h1>");
            if (usernameOptional.isPresent()){
                out.println("    <div style='color: blue;'>Hola " + usernameOptional.get() + " Bienvenido" + "</div>");
            }

            out.println("<table class=\"table table-striped\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th scope=\"col\">id</th>");
            out.println("<th scope=\"col\">nombre</th>");
            out.println("<th scope=\"col\">tipo</th>");
            out.println("<th scope=\"col\">precio</th>");
            out.println("<th scope=\"col\">agregar</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            productos.forEach(p -> {
                out.println("<tr>");

                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href=\""
                            + req.getContextPath()
                            + "/agregar-carro?id=" + p.getId()
                            + "\">agregar al carro</a></td>");
                }else {
                    out.println("<td>" + "Inicia sesion para ver los precios" + "</td>");
                    out.println("<td>" + "Inicia sesion para agregar al carrito" + "</td>");
                }

                out.println("</tr>");
            });
            out.println("</tbody>");
            out.println("</table>");

            out.println("<div ALIGN=right >");



            out.println("<div>");

            out.println("    </body>");
            out.println("</html>");
        }

    }
}