package fr.eni.encheres.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet(name = "home", value = "/")
public class ServeltsHome extends HttpServlet {
    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("indexServlet");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
