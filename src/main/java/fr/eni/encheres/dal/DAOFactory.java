package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.*;

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
    public static BidJdbcImpl getBidDao() {
        return new BidJdbcImpl();
    }

}
