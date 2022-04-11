package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "home", value = "/")
public class ServeltsHome extends HttpServlet {

    private ArticleManager articleManager;
    private CategoryManager categoryManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        categoryManager = CategoryManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            ArrayList<Category> categories = categoryManager.getAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("articles", articleManager.getAllArticles());

            request.setAttribute("catSelected", -1);
            request.setAttribute("nameArticle", null);

        } catch (DALException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        int idCat = -1;
        String nameArticle = null;
        try {
            if (!Objects.equals(request.getParameter("category"), null)) {
                idCat = Integer.parseInt(request.getParameter("category"));
            }
            String name=request.getParameter("name");

            if(name!=null){
                if (!name.equals("")) {
                    nameArticle = request.getParameter("name");
                }
            }


            ArrayList<Article> allArticles = articleManager.getArticlesWithFilter(idCat, nameArticle);
            request.setAttribute("articles", allArticles);

            ArrayList<Category> categories = categoryManager.getAllCategory();
            request.setAttribute("categories", categories);

            request.setAttribute("catSelected", idCat);
            request.setAttribute("nameArticle", nameArticle);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (DALException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
