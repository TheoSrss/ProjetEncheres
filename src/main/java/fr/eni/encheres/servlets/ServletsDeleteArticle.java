package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.WithDrawalManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteArticle", value = "/deleteArticle")

public class ServletsDeleteArticle extends HttpServlet {
    private ArticleManager articleManager;
    private CategoryManager categoryManager;
    private WithDrawalManager withDrawalManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        categoryManager = CategoryManager.getInstance();
        withDrawalManager = WithDrawalManager.getInstance();

        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        User userSession = (User) request.getSession().getAttribute("user");

        try {
            String idArticle = request.getParameter("idArticle");

            if (idArticle != null && !idArticle.equals("")) {
                Article a = articleManager.getArticleById(Integer.parseInt(idArticle));

                if (a.getUser().getId() == userSession.getId()) {

                    articleManager.deleteArticle(a);
                }

            }
            request.setAttribute("success", "Article supprimer");
            getServletContext().getRequestDispatcher("/home").forward(request, response);

        } catch (DALException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("error", "Impossible de supprimer cet article");

        getServletContext().getRequestDispatcher("/home").forward(request, response);


    }

}
