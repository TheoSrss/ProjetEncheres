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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/updateArticle")
public class ServletsUpdateArticle extends HttpServlet {
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
        if (request.getSession().getAttribute("user") != null) {
            String idArticle = request.getParameter("idArticle");
            try {
                Article a = articleManager.getArticleById(Integer.parseInt(idArticle));

                if (a.getUser().getId() != ((User) request.getSession().getAttribute("user")).getId()) {
                    getServletContext().getRequestDispatcher("/home").forward(request, response);
                } else {

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

        try {
            User userSession = (User) request.getSession().getAttribute("user");

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
                request.setAttribute("error", "Veuillez remplir tous les champs");
                doGet(request,response);

            } else {
                Withdrawal withdrawal = new Withdrawal(
                        Integer.parseInt(request.getParameter("idAddress")),
                        request.getParameter("street"),
                        Integer.parseInt(request.getParameter("postalCode")),
                        request.getParameter("city")
                );
                Withdrawal address = withDrawalManager.updateAddress(withdrawal);


                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

                String dateStartBid = request.getParameter("dateStartBid");
                LocalDateTime dateStart = LocalDateTime.parse(dateStartBid);
                String dateEndBid = request.getParameter("dateEndBid");
                LocalDateTime dateEnd = LocalDateTime.parse(dateEndBid);

                Article article = new Article(
                        Integer.parseInt(request.getParameter("idArticle")),
                        request.getParameter("name"),
                        request.getParameter("description"),
                        dateStart,
                        dateEnd,
                        Float.parseFloat(request.getParameter("initialPrice")),
                        -1,
                        "TO_SALE",
                        userSession,
                        categoryManager.getById(Integer.parseInt(request.getParameter("category"))),
                        address
                );

                articleManager.updateArticle(article);

                request.setAttribute("success", "Article modifier");

                request.setAttribute("catSelected", -1);
                request.setAttribute("nameArticle", null);
                getServletContext().getRequestDispatcher("/home").forward(request, response);




            }

        } catch (NumberFormatException | DALException | SQLException e) {
            e.printStackTrace();
        }
    }
}