package fr.eni.encheres.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet(name = "disconnect", value = "/disconnect")
public class ServletsDisconnect extends HttpServlet {

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getSession().setAttribute("user", null);

        request.getSession().removeAttribute("user");
        getServletContext().getRequestDispatcher("/home").forward(request, response);
    }
}
