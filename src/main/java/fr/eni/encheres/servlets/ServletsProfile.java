package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ServletsProfile extends HttpServlet {
    private UserManager userManager;

    public void init() throws ServletException {
        userManager = UserManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idUser = request.getParameter("idUser");
        User user = null;

        if (idUser != null) {
            try {
                user = userManager.getUserById(Integer.parseInt(idUser));
                if (user == null) {
                    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                } else {
                    request.setAttribute("profileUser", user);
                    System.out.println(user.getUsername());

                    request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
                    return ;
                }
            } catch (BLLException | DALException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}