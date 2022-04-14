package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArticleDAOJdbcImpl implements ArticleDao {
    PreparedStatement stmt = null;
    Connection con = null;
    private UserManager userManager = UserManager.getInstance();

    @Override
    public Article insert(Article a) throws DALException, SQLException {

        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("INSERT INTO ARTICLESOLD VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, null);
            stmt.setString(2, a.getName());
            stmt.setString(3, a.getDescription());
            stmt.setString(4, a.getDateStartBid().toString());
            stmt.setString(5, a.getDateEndBid().toString());
            stmt.setFloat(6, a.getInitialPrice());
            stmt.setFloat(7, a.getSoldPrice());
            stmt.setString(8, a.getStateSale());
            stmt.setInt(9, a.getUser().getId());
            stmt.setString(10, null);
            stmt.setInt(11, a.getCategory().getId());
            stmt.setInt(12, a.getWithdrawal().getId());

            int nb = stmt.executeUpdate();

            if (nb > 0) {
                ResultSet r = stmt.getGeneratedKeys();
                if (r.next()) {
                    a.setId(r.getInt(1));
                }
            }
            con.close();
            return a;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }

    @Override
    public Article getArticleById(int id) throws DALException, SQLException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM ARTICLESOLD  as a INNER JOIN USER as u ON u.id=a.idUser INNER JOIN CATEGORY as c ON c.id=a.idCategory INNER JOIN WITHDRAWAL as w ON w.id=a.idWithdrawal WHERE a.id=?");

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            int a = 0;

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("username"),
                        resultSet.getString("surname"),
                        resultSet.getString("firstName"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("u.street"),
                        resultSet.getInt("u.postalCode"),
                        resultSet.getString("u.city"),
                        resultSet.getString("password"),
                        resultSet.getFloat("credit"),
                        resultSet.getBoolean("admin")

                );
                Withdrawal withdrawal = new Withdrawal(
                        resultSet.getInt("w.id"),
                        resultSet.getString("w.street"),
                        resultSet.getInt("w.postalCode"),
                        resultSet.getString("w.city")
                );

                User lastUser = userManager.getUserById(resultSet.getInt("a.idLastUser"));

                Category category = new Category(
                        resultSet.getInt("c.id"),
                        resultSet.getString("c.libelle")
                );
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Article article = new Article(
                        resultSet.getInt("a.id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        LocalDateTime.parse(resultSet.getString("dateStartBid"), formatter),
                        LocalDateTime.parse(resultSet.getString("dateEndBid"), formatter),
                        resultSet.getInt("initialPrice"),
                        resultSet.getInt("soldPrice"),
                        resultSet.getString("stateSale"),
                        user,
                        lastUser,
                        category,
                        withdrawal
                );
                con.close();
                return article;
            }
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        } catch (BLLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Article> getAllArticles() throws DALException, SQLException {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM ARTICLESOLD as a INNER JOIN USER as u ON u.id=a.idUser INNER JOIN CATEGORY as c ON c.id=a.idCategory INNER JOIN WITHDRAWAL as w ON w.id=a.idWithdrawal" +
                    "");


            ResultSet resultSet = stmt.executeQuery();
            int a = 0;

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("username"),
                        resultSet.getString("surname"),
                        resultSet.getString("firstName"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("u.street"),
                        resultSet.getInt("u.postalCode"),
                        resultSet.getString("u.city"),
                        resultSet.getString("password"),
                        resultSet.getFloat("credit"),
                        resultSet.getBoolean("admin")

                );
                User lastUser = userManager.getUserById(resultSet.getInt("a.idLastUser"));

                Withdrawal withdrawal = new Withdrawal(
                        resultSet.getInt("w.id"),
                        resultSet.getString("w.street"),
                        resultSet.getInt("w.postalCode"),
                        resultSet.getString("w.city")
                );
                Category category = new Category(
                        resultSet.getInt("c.id"),
                        resultSet.getString("c.libelle")
                );

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Article article = new Article(
                        resultSet.getInt("a.id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        LocalDateTime.parse(resultSet.getString("dateStartBid"), formatter),
                        LocalDateTime.parse(resultSet.getString("dateEndBid"), formatter),
                        resultSet.getInt("initialPrice"),
                        resultSet.getInt("soldPrice"),
                        resultSet.getString("stateSale"),
                        user,
                        lastUser,
                        category,
                        withdrawal
                );
                articles.add(a, article);
                a++;
            }
            con.close();
            return articles;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        } catch (BLLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Article updateArticle(Article a) throws DALException, SQLException {

        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("UPDATE articlesold SET name=?, description=?, dateStartBid=?, dateEndBid=?, initialPrice=?, idCategory=?, stateSale=?, idLastUser=?, idUser=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getDescription());
            stmt.setString(3, a.getDateStartBid().toString());
            stmt.setString(4, a.getDateEndBid().toString());
            stmt.setFloat(5, a.getInitialPrice());
            stmt.setInt(6, a.getCategory().getId());
            stmt.setString(7, a.getStateSale());
            if (a.getLastUser() != null) {
                stmt.setInt(8, a.getLastUser().getId());
            } else {
                stmt.setString(8, null);
            }
            stmt.setInt(9, a.getUser().getId());

            stmt.setInt(10, a.getId());

            int nb = stmt.executeUpdate();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (nb > 0) {
                ResultSet resultSet = stmt.getGeneratedKeys();
                if (resultSet.next()) {
                    Article article = new Article(
                            resultSet.getInt("a.id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            LocalDateTime.parse(resultSet.getString("dateStartBid"), formatter),
                            LocalDateTime.parse(resultSet.getString("dateEndBid"), formatter),
                            resultSet.getInt("initialPrice"),
                            resultSet.getInt("soldPrice"),
                            resultSet.getString("stateSale"),
                            a.getUser(),
                            a.getCategory(),
                            a.getWithdrawal()
                    );
                    con.close();
                    return article;
                }
            }

        } catch (
                SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
        return null;
    }

    @Override
    public ArrayList<Article> getArticlesWithState(String state) throws DALException, SQLException {

        ArrayList<Article> toReturn = new ArrayList<>();
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM articlesold as a INNER JOIN USER as u ON u.id=a.idUser INNER JOIN CATEGORY as c ON c.id=a.idCategory INNER JOIN WITHDRAWAL as w ON w.id=a.idWithdrawal   WHERE stateSale=?");

            stmt.setString(1, state);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("username"),
                        resultSet.getString("surname"),
                        resultSet.getString("firstName"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("u.street"),
                        resultSet.getInt("u.postalCode"),
                        resultSet.getString("u.city"),
                        resultSet.getString("password"),
                        resultSet.getFloat("credit"),
                        resultSet.getBoolean("admin")

                );
                Withdrawal withdrawal = new Withdrawal(
                        resultSet.getInt("w.id"),
                        resultSet.getString("w.street"),
                        resultSet.getInt("w.postalCode"),
                        resultSet.getString("w.city")
                );
                Category category = new Category(
                        resultSet.getInt("c.id"),
                        resultSet.getString("c.libelle")
                );

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Article article = new Article(
                        resultSet.getInt("a.id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        LocalDateTime.parse(resultSet.getString("dateStartBid"), formatter),
                        LocalDateTime.parse(resultSet.getString("dateEndBid"), formatter),
                        resultSet.getInt("initialPrice"),
                        resultSet.getInt("soldPrice"),
                        resultSet.getString("stateSale"),
                        user,
                        category,
                        withdrawal
                );
                toReturn.add(toReturn.size(), article);
            }
            con.close();

            return toReturn;

        } catch (
                SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }

    @Override
    public void deleteArticle(Article a) throws DALException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("DELETE FROM articleSOld WHERE id=?");

            stmt.setInt(1, a.getId());
            stmt.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }
}
