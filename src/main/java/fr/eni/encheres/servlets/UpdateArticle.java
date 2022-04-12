package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/updateArticle")
public class UpdateArticle extends HttpServlet {
    private ArticleManager articleManager;
    private CategoryManager categoryManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        categoryManager = CategoryManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            System.out.println("oui");
            String idArticle = request.getParameter("idArticle");
            try {
                Article a = articleManager.getArticleById(Integer.parseInt(idArticle));

                if (a.getUser().getId() != ((User) request.getSession().getAttribute("user")).getId()) {
                    getServletContext().getRequestDispatcher("/home").forward(request, response);
                }else{
                    ArrayList<Category> categories = categoryManager.getAllCategory();
                    request.setAttribute("categories", categories);
                    request.setAttribute("catSelected", a.getCategory().getId());

                    request.setAttribute("a", a);
                    request.getRequestDispatcher("WEB-INF/updateArticle.jsp").forward(request, response);
                }
            } catch (DALException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}