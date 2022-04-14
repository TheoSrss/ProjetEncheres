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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "home", value = "/")
public class ServletsHome extends HttpServlet {

    private ArticleManager articleManager;
    private CategoryManager categoryManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        categoryManager = CategoryManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            articleManager.updateAllArticleState();

            ArrayList<Category> categories = categoryManager.getAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("articles", articleManager.getArticlesTOSALE());

            request.setAttribute("catSelected", -1);
            request.setAttribute("nameArticle", null);
            request.setAttribute("typeBid", null);
            request.setAttribute("params", null);

        } catch (DALException | SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User userSession = (User) request.getSession().getAttribute("user");
        int idCat = -1;
        String nameArticle = null;
        try {
            articleManager.updateAllArticleState();

            if (!Objects.equals(request.getParameter("category"), null)) {
                if (!Objects.equals(request.getParameter("category"), "null")) {
                    idCat = Integer.parseInt(request.getParameter("category"));
                }
            }
            String name = request.getParameter("name");
            if (name != null) {
                if (!name.equals("")) {
                    nameArticle = request.getParameter("name");
                }
            }
            String typeBid = null;
            Map<String, String> params = new HashMap<String, String>();

            String firstParam = null;
            String secondParam = null;
            String thirdParam = null;
            if (userSession != null) {
                if (Objects.equals(request.getParameter("typeBid"), "typeBidPurchase")) {
                    typeBid = "typeBidPurchase";
                    params.put("bidOpen", request.getParameter("bidOpen"));
                    params.put("myBids", request.getParameter("myBids"));
                    params.put("myWinBids", request.getParameter("myWinBids"));

                    firstParam = request.getParameter("bidOpen");
                    secondParam = request.getParameter("myBids");
                    thirdParam = request.getParameter("myWinBids");

                } else if (Objects.equals(request.getParameter("typeBid"), "typeBidMySell")) {
                    typeBid = "typeBidMySell";
                    params.put("mySellOpen", request.getParameter("mySellOpen"));
                    params.put("mySellNotStart", request.getParameter("mySellNotStart"));
                    params.put("mySellFinish", request.getParameter("mySellFinish"));

                    firstParam = request.getParameter("mySellOpen");
                    secondParam = request.getParameter("mySellNotStart");
                    thirdParam = request.getParameter("mySellFinish");
                }
            }

            ArrayList<Article> allArticles = articleManager.getArticlesWithFilter(idCat, nameArticle, typeBid, params, userSession);
            request.setAttribute("articles", allArticles);
            ArrayList<Category> categories = categoryManager.getAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("catSelected", idCat);
            request.setAttribute("nameArticle", nameArticle);
            request.setAttribute("typeBid", typeBid);

            request.setAttribute("firstParam", firstParam);
            request.setAttribute("secondParam", secondParam);
            request.setAttribute("thirdParam", thirdParam);


            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (DALException | SQLException e) {
            e.printStackTrace();
        }

    }

}
