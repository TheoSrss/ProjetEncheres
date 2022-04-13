package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BidManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet("/article")
public class ServletsArticle extends HttpServlet {
    private ArticleManager articleManager;
    private BidManager bidManager;
    private UserManager userManager;

    public void init() throws ServletException {
        articleManager = ArticleManager.getInstance();
        bidManager = BidManager.getInstance();
        userManager = UserManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idArticle = request.getParameter("idArticle");

        if (idArticle != null && !idArticle.equals("")) {
            try {
                Article a = articleManager.getArticleById(Integer.parseInt(idArticle));
                float min = a.getInitialPrice();
                if (a != null) {
                    Bid b = bidManager.getLastBidForIdArticle(a.getId());

                    if (b != null) {
                        b.setArticleSold(a);
                        min = b.getAmount();
                    }
                    boolean canUpdateArticle = true;

                    if (LocalDateTime.now().isAfter(a.getDateStartBid())) {
                        canUpdateArticle = false;
                    }

                    request.setAttribute("canUpdateArticle", canUpdateArticle);
                    request.setAttribute("article", a);
                    request.setAttribute("bid", b);
                    request.setAttribute("min", min + 1);

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idArticle = request.getParameter("idArticle");
        User user = (User) request.getSession().getAttribute("user");
        float price = Float.parseFloat(request.getParameter("price"));
        try {
            Article article = articleManager.getArticleById(Integer.parseInt(idArticle));

            if (article.getUser().getId() == user.getId()) {
                getServletContext().getRequestDispatcher("/home").forward(request, response);
            } else {
                boolean canBid = userManager.checkIfUserCanBid(user.getCredit(), price);

                if (canBid) {
                    Bid bid = new Bid(user, article, new Date(), price);
                    bidManager.setNewBid(bid);

                    UserManager.updateCreditUser(price, user.getId());
                    float firstPrice = user.getCredit();
                    ((User) request.getSession().getAttribute("user")).setCredit(firstPrice - price);

                    boolean canUpdateArticle = true;

                    if (article.getDateStartBid().isAfter(LocalDateTime.now())) {
                        canUpdateArticle = false;
                    }
                    request.setAttribute("canUpdateArticle", canUpdateArticle);

                    request.setAttribute("article", article);
                    request.setAttribute("bid", bid);
                    request.setAttribute("min", bid.getAmount() + 1);

                    request.setAttribute("success", "Vous avez enchérir pour " + bid.getAmount() + " points");

                } else {
                    request.setAttribute("error", "Impossible d'enchérir, solde de points insuffisants");
                }
                doGet(request, response);

            }
        } catch (DALException | SQLException e) {
            e.printStackTrace();

        }
    }
}
