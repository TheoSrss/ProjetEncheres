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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/createArticle")
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


        try {
            ArrayList<Category> categories = categoryManager.getAllCategory();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("WEB-INF/createArticle.jsp").forward(request, response);

        } catch (DALException | SQLException e) {
            e.printStackTrace();
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
            ){
                ArrayList<Category> categories = categoryManager.getAllCategory();
                request.setAttribute("categories", categories);
                request.setAttribute("error", "Veuillez remplir tous les champs");
                request.getRequestDispatcher("WEB-INF/createArticle.jsp").forward(request, response);

            }else {

                Withdrawal withdrawal = new Withdrawal(
                        request.getParameter("street"),
                        Integer.parseInt(request.getParameter("postalCode")),
                        request.getParameter("city")
                );
                Withdrawal address = withDrawalManager.addAddress(withdrawal);

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

                String dateStartBid = request.getParameter("dateStartBid");
                Date dateStart = (Date) formatter.parse(dateStartBid);
                String dateEndBid = request.getParameter("dateEndBid");
                Date dateEnd = (Date) formatter.parse(dateEndBid);

                Article article = new Article(
                        request.getParameter("name"),
                        request.getParameter("description"),
                        dateStart,
                        dateEnd,
                        Integer.parseInt(request.getParameter("initialPrice")),
                        -1,
                        "TO_SALE",
                        userSession,
                        categoryManager.getById(Integer.parseInt(request.getParameter("category"))),
                        address
                );
                articleManager.addArticles(article);

                request.setAttribute("success", "Article mis en ligne");

                getServletContext().getRequestDispatcher("/home").forward(request, response);
            }
        } catch (DALException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
