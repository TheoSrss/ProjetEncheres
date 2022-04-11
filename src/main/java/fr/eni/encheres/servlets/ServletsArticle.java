package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/article")
public class ServletsArticle extends HttpServlet {
    private ArticleManager articleManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idArticle = request.getParameter("idArticle");

        if (idArticle != null && !idArticle.equals("")) {
            try {
                Article a = articleManager.getArticleById(Integer.parseInt(idArticle));


                if (a != null) {
                    request.setAttribute("article", a);
                    request.getRequestDispatcher("WEB-INF/article.jsp").forward(request, response);

                } else {
                    getServletContext().getRequestDispatcher("/home").forward(request, response);
                }
            } catch (DALException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

    }

}
