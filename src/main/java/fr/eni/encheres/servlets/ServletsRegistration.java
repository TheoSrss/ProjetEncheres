package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.dal.DALException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "registration", value = "/registration")

public class ServletsRegistration extends HttpServlet {
    private UserManager userManager;

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");

            if (Objects.equals(password, passwordCheck)) {
                User user = new User(
                        request.getParameter("username"),
                        request.getParameter("surname"),
                        request.getParameter("firstName"),
                        request.getParameter("email"),
                        request.getParameter("phone"),
                        request.getParameter("street"),
                        request.getParameter("postalCode"),
                        request.getParameter("city"),
                        request.getParameter("password"),
                        100, false
                );

                System.out.println(user);
                userManager.registration(user);

            }
        } catch (BLLException e) {
            e.printStackTrace();
        } catch (DALException e) {
            e.printStackTrace();
        }
    }


}


