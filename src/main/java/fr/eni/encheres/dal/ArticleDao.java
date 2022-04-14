package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ArticleDao {

    public Article insert(Article a) throws DALException, SQLException;
    public Article getArticleById(int id) throws DALException, SQLException;

    public ArrayList<Article> getAllArticles() throws DALException, SQLException;
    public Article updateArticle(Article a) throws DALException, SQLException;
    public ArrayList<Article> getArticlesWithState(String state) throws DALException, SQLException;

}
