package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.DALException;

import java.sql.SQLException;

public class ArticleDAOJdbcImpl  implements ArticleDao {
    @Override
    public Article insert(User user) throws DALException, SQLException {
        return null;
    }
}
