package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleManager {
    private static ArticleManager articleManager;
    private static ArticleDao articleDao;

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

    public ArrayList<Article> getAllArticles() throws DALException, SQLException {
        return articleDao.getAllArticles();
    }

    public ArrayList<Article> getArticlesWithFilter(int idCat, String name) throws DALException, SQLException {

        ArrayList<Article> articles = getAllArticles();

        if (idCat != -1) {
            ArrayList<Article> articlesForCat = new ArrayList<Article>();
            int nb = 0;
            for (Article a : articles) {
                if (a.getCategory().getId() == idCat) {
                    articlesForCat.add(nb, a);
                    nb++;
                }
            }
            articles = articlesForCat;
        }
        if (name != null) {
            ArrayList<Article> articlesForName = new ArrayList<Article>();
            int nbN = 0;
            for (Article a : articles) {
                if (a.getName().contains(name)) {
                    articlesForName.add(nbN, a);
                    nbN++;
                }
            }
            articles = articlesForName;
        }
        return articles;
    }

    public Article getArticleById(int i) throws DALException, SQLException {

        return articleDao.getArticleById( i);
    }
}
