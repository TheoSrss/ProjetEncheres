package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

import java.sql.SQLException;

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
}
