package fr.eni.encheres.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet(name = "encheres", value = "/encheres")

public class ServletcHome {


    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }
}
