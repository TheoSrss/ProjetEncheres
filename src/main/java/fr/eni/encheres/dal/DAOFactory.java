package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.CategoryDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.WithdrawalJdbcImpl;

public class DAOFactory {
    public static UserDAO getUserDao() {

        return new UserDAOJdbcImpl();
    }
    public static ArticleDao getArticleDao() {

        return new ArticleDAOJdbcImpl();
    }
    public static CategoryDao getCategoryDao() {
        return new CategoryDAOJdbcImpl();
    }
    public static WithdrawalJdbcImpl getWithDrawalDao() {
        return new WithdrawalJdbcImpl();
    }

}
