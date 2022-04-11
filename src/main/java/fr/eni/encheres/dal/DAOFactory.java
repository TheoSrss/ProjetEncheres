package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.CategoryDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;

public class DAOFactory {


    public static UserDAO getUserDao() {
        UserDAO userDAO = new UserDAOJdbcImpl();

        return userDAO;
    }
    public static ArticleDao getArticleDao() {
        ArticleDao articleDao =  new ArticleDAOJdbcImpl();

        return articleDao;
    }
    public static CategoryDao getCategoryDao() {
        CategoryDao categoryDao =  new CategoryDAOJdbcImpl();

        return categoryDao;
    }

}
