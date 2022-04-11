package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;

import java.sql.SQLException;

public interface ArticleDao {

    public Article insert(User user) throws DALException, SQLException;

}
