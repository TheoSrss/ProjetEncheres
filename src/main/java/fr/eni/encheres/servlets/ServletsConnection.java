package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "connection", value = "/login")
public class ServletsConnection extends HttpServlet {
    private UserManager userManager;


    public void init() throws ServletException {
        userManager = UserManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

//        HttpSession session = request.getSession();
//
//        System.out.println(session.getId());
//        request.setAttribute("session", session);

        request.getRequestDispatcher("WEB-INF/connection.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

        try {
            boolean exist = UserManager.connexionIsGood(login, password);

            if (!exist){
                request.getRequestDispatcher("WEB-INF/connection.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
            }

        } catch (BLLException | DALException | SQLException e) {
            e.printStackTrace();
        }


    }
}
