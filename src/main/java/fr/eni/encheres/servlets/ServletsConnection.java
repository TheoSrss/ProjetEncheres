package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "connection", value = "/login")
public class ServletsConnection extends HttpServlet {
    private UserManager userManager;
    private ArticleManager articleManager;


    public void init() throws ServletException {
        userManager = UserManager.getInstance();
        articleManager = ArticleManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getSession().setAttribute("user", null);

        request.getRequestDispatcher("WEB-INF/connection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        try {
            if (request.getParameter("login").equals("") ||
                    request.getParameter("password").equals("")
            ) {
                request.setAttribute("error", "Veuillez remplir tous les champs");
                doGet(request, response);
            } else {
                String login = request.getParameter("login");
                String password = request.getParameter("password");

                User user = UserManager.connexionIsGood(login, password);

                if (user == null) {
                    request.setAttribute("error", "Identifiant et/ou mot de passe incorrect");
                    request.getRequestDispatcher("WEB-INF/connection.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();

                    session.setMaxInactiveInterval(300);
                    request.getSession().setAttribute("user", user);
                    request.setAttribute("catSelected", -1);
                    request.setAttribute("nameArticle", null);
                    request.setAttribute("articles", articleManager.getArticlesTOSALE());

                    getServletContext().getRequestDispatcher("/home").forward(request, response);
                }
            }
        } catch (BLLException | DALException | SQLException e) {
            e.printStackTrace();
        }
    }
}
