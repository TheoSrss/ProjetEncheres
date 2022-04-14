package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ArticleManager {
    private static ArticleManager articleManager;
    private static ArticleDao articleDao;
    private BidManager bidManager = BidManager.getInstance();
    private UserManager userManager = UserManager.getInstance();

    private ArticleManager() {
        articleDao = DAOFactory.getArticleDao();
    }

    public static ArticleManager getInstance() {
        if (articleManager == null) {
            articleManager = new ArticleManager();
        }
        return articleManager;
    }

    public Article addArticles(Article a) throws DALException, SQLException {
        return articleDao.insert(a);
    }

    public ArrayList<Article> getArticlesTOSALE() throws DALException, SQLException {

        ArrayList<Article> articles = articleDao.getAllArticles();
        ArrayList<Article> toReturn = new ArrayList<Article>();
        for (Article a : articles) {
            if (Objects.equals(a.getStateSale(), "TO_SALE")) {
                if (LocalDateTime.now().isAfter(a.getDateStartBid()) && LocalDateTime.now().isBefore(a.getDateEndBid())) {
                    toReturn.add(toReturn.size(), a);
                }
            }
        }
        return toReturn;
    }

    public ArrayList<Article> getArticlesWithFilter(int idCat, String name, String typeBid, Map<String, String> params, User u) throws DALException, SQLException {
        ArrayList<Article> articles = articleDao.getAllArticles();
        if (idCat != -1) {
            ArrayList<Article> articlesForCat = new ArrayList<Article>();
            for (Article a : articles) {
                if (a.getCategory().getId() == idCat) {
                    articlesForCat.add(articlesForCat.size(), a);
                }
            }
            articles = articlesForCat;
        }
        if (name != null) {
            ArrayList<Article> articlesForName = new ArrayList<Article>();
            for (Article a : articles) {
                if (a.getName().contains(name)) {
                    articlesForName.add(articlesForName.size(), a);
                }
            }
            articles = articlesForName;
        }

        if (Objects.equals(typeBid, "typeBidPurchase")) {
            ArrayList<Article> articlesFortypeBidPurchase = new ArrayList<Article>();
            int nbB = 0;
            for (Article a : articles) {
                if (!Objects.equals(params.get("bidOpen"), "on") && !Objects.equals(params.get("myBids"), "on") && !Objects.equals(params.get("myWinBids"), "on")) {
                    if (Objects.equals(a.getStateSale(), "TO_SALE")) {
                        articlesFortypeBidPurchase.add(articlesFortypeBidPurchase.size(), a);
                    } else if (Objects.equals(a.getStateSale(), "IS_WIN")) {
                        Bid lastBid = bidManager.getLastBidForIdArticle(a.getId());
                        if (lastBid != null) {
                            if (u.getId() == lastBid.getUser().getId()) {
                                articlesFortypeBidPurchase.add(articlesFortypeBidPurchase.size(), a);
                            }
                        }
                    }
                } else {
                    if (Objects.equals(params.get("bidOpen"), "on") && Objects.equals(a.getStateSale(), "TO_SALE")) {
                        articlesFortypeBidPurchase.add(articlesFortypeBidPurchase.size(), a);
                    } else if (Objects.equals(params.get("myBids"), "on")) {
                        Bid bid = bidManager.getBidForUserAndArticle(u, a);
                        if (bid != null) {
                            articlesFortypeBidPurchase.add(articlesFortypeBidPurchase.size(), a);
                        }
                    } else if (Objects.equals(params.get("myWinBids"), "on")) {
                        Bid lastBid = bidManager.getLastBidForIdArticle(a.getId());
                        if (lastBid != null) {
                            if (lastBid.getUser().getId() == u.getId()) {
                                articlesFortypeBidPurchase.add(articlesFortypeBidPurchase.size(), a);
                            }
                        }
                    }
                }
                nbB++;
            }
            articles = articlesFortypeBidPurchase;
        } else if (Objects.equals(typeBid, "typeBidMySell")) {
            ArrayList<Article> articlesFortypeBidMySell = new ArrayList<Article>();
            int nbBid = 0;
            for (Article a : articles) {
                if (!Objects.equals(params.get("mySellOpen"), "on") && !Objects.equals(params.get("mySellNotStart"), "on") && !Objects.equals(params.get("mySellFinish"), "on")) {
                    if (a.getUser().getId() == u.getId() && (Objects.equals(a.getStateSale(), "NOT_START") || Objects.equals(a.getStateSale(), "TO_SALE"))) {
                        articlesFortypeBidMySell.add(articlesFortypeBidMySell.size(), a);
                    } else if (a.getLastUser() != null) {
                        if (a.getLastUser().getId() == u.getId() && (Objects.equals(a.getStateSale(), "IS_WIN") || Objects.equals(a.getStateSale(), "NOT_SALE"))) {
                            articlesFortypeBidMySell.add(articlesFortypeBidMySell.size(), a);
                        }
                    }
                } else if (a.getUser().getId() == u.getId()) {
                    if (Objects.equals(params.get("mySellOpen"), "on") && Objects.equals(a.getStateSale(), "TO_SALE")) {
                        articlesFortypeBidMySell.add(articlesFortypeBidMySell.size(), a);
                    } else if (Objects.equals(params.get("mySellNotStart"), "on") && Objects.equals(a.getStateSale(), "NOT_START")) {
                        articlesFortypeBidMySell.add(articlesFortypeBidMySell.size(), a);
                    }
                } else if (a.getLastUser() != null) {
                    if (a.getLastUser().getId() == u.getId()) {
                        if (Objects.equals(params.get("mySellFinish"), "on") && (Objects.equals(a.getStateSale(), "IS_WIN") || Objects.equals(a.getStateSale(), "NOT_SALE"))) {
                            articlesFortypeBidMySell.add(articlesFortypeBidMySell.size(), a);
                        }
                    }
                }
                nbBid++;
            }
            articles = articlesFortypeBidMySell;
        }

        if (!Objects.equals(typeBid, "typeBidMySell")) {

            ArrayList<Article> toReturn = new ArrayList<Article>();
            for (Article a : articles) {
                if (Objects.equals(a.getStateSale(), "TO_SALE")) {
                    toReturn.add(toReturn.size(), a);
                } else if (u != null) {
                    if (Objects.equals(a.getStateSale(), "IS_WIN") && a.getUser().getId() == u.getId()) {
                        toReturn.add(toReturn.size(), a);
                    }
                }
            }
            articles = toReturn;
        }
        return articles;
    }

    public Article getArticleById(int i) throws DALException, SQLException {

        return articleDao.getArticleById(i);
    }

    public Article updateArticle(Article a) throws DALException, SQLException {

        return articleDao.updateArticle(a);
    }

    public void updateAllArticleState() throws DALException, SQLException {
        checkIfArticleCanStart();
        checkIfArticleCanFinish();
    }

    private void checkIfArticleCanStart() throws DALException, SQLException {

        ArrayList<Article> articles = articleDao.getArticlesWithState("NOT_START");

        for (Article a : articles) {
            if (LocalDateTime.now().isAfter(a.getDateStartBid()) && LocalDateTime.now().isBefore(a.getDateEndBid())) {
                a.setStateSale("TO_SALE");
                articleDao.updateArticle(a);
            }
        }
    }

    private void checkIfArticleCanFinish() throws DALException, SQLException {

        ArrayList<Article> articles = articleDao.getArticlesWithState("TO_SALE");

        for (Article a : articles) {
            if (LocalDateTime.now().isAfter(a.getDateEndBid())) {
                Bid lastBid = bidManager.getLastBidForIdArticle(a.getId());
                if (lastBid != null) {
                    System.out.println("win");


                    a.setStateSale("IS_WIN");
                    a.setLastUser(a.getUser());
                    a.setUser(lastBid.getUser());
                    User userCredit = a.getLastUser();
                    userCredit.setCredit(userCredit.getCredit() + lastBid.getAmount());
                    System.out.println(userCredit.getId());
                    System.out.println(userCredit.getCredit());

                    userManager.updateUser(userCredit);
                } else {
                    a.setStateSale("NOT_SALE");
                }
                articleDao.updateArticle(a);
            }
        }
    }

    public void deleteArticle(Article a) throws DALException, SQLException {
        articleDao.deleteArticle(a);
    }
}
