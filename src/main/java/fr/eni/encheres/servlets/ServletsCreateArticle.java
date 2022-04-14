package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.WithDrawalManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/createArticle")
@MultipartConfig
public class ServletsCreateArticle extends HttpServlet {
    private ArticleManager articleManager;
    private CategoryManager categoryManager;
    private WithDrawalManager withDrawalManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        categoryManager = CategoryManager.getInstance();
        withDrawalManager = WithDrawalManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        } else {
            try {
                ArrayList<Category> categories = categoryManager.getAllCategory();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("WEB-INF/createArticle.jsp").forward(request, response);
            } catch (DALException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User userSession = (User) request.getSession().getAttribute("user");
            //checkIfInputIsEmpty
            if (request.getParameter("name").equals("") ||
                    request.getParameter("description").equals("") ||
                    request.getParameter("category").equals("") ||
                    request.getParameter("dateStartBid").equals("") ||
                    request.getParameter("dateEndBid").equals("") ||
                    request.getParameter("initialPrice").equals("") ||
                    request.getParameter("street").equals("") ||
                    request.getParameter("postalCode").equals("") ||
                    request.getParameter("city").equals("")
            ) {
                ArrayList<Category> categories = categoryManager.getAllCategory();
                request.setAttribute("categories", categories);
                request.setAttribute("error", "Veuillez remplir tous les champs");
                request.getRequestDispatcher("WEB-INF/createArticle.jsp").forward(request, response);

            } else {
                Withdrawal withdrawal = new Withdrawal(
                        request.getParameter("street"),
                        Integer.parseInt(request.getParameter("postalCode")),
                        request.getParameter("city")
                );
                Withdrawal address = withDrawalManager.addAddress(withdrawal);

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

                String dateStartBid = request.getParameter("dateStartBid");
                LocalDateTime dateStart = LocalDateTime.parse(dateStartBid);
                String dateEndBid = request.getParameter("dateEndBid");
                LocalDateTime dateEnd = LocalDateTime.parse(dateEndBid);

                Article article = new Article(
                        request.getParameter("name"),
                        request.getParameter("description"),
                        dateStart,
                        dateEnd,
                        Integer.parseInt(request.getParameter("initialPrice")),
                        -1,
                        "NOT_START",
                        userSession,
                        categoryManager.getById(Integer.parseInt(request.getParameter("category"))),
                        address
                );
                articleManager.addArticles(article);

                request.setAttribute("success", "Article mis en ligne");

                request.setAttribute("catSelected", -1);
                request.setAttribute("nameArticle", null);
                getServletContext().getRequestDispatcher("/home").forward(request, response);
            }
        } catch (DALException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("error", "Erreur");

        getServletContext().getRequestDispatcher("/home").forward(request, response);

    }

    /*
     * Récupération du nom du fichier dans la requête.
     */
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "Default.file";
    }
}
