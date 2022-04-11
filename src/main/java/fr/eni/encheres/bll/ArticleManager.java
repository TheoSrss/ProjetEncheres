package fr.eni.encheres.bll;

import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.DAOFactory;

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
}
