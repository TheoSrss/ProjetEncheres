package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

import java.sql.*;

public class BidJdbcImpl implements BidDAO {
    PreparedStatement stmt = null;
    Connection con = null;


    @Override
    public Bid getLastBidForIdArticle(int id) throws DALException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM bid as b INNER JOIN user as u ON b.idUser=u.id WHERE idArticle=? AND amount=(SELECT MAX(amount) FROM bid WHERE idArticle=?)");
            stmt.setInt(1, id);
            stmt.setInt(2, id);

            ResultSet resultSet = stmt.executeQuery();
            Date lastDate = null;
            Bid b = null;
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
                        resultSet.getInt("credit"),
                        resultSet.getBoolean("admin")

                );
                b = new Bid(
                        user,
                        null,
                        lastDate,
                        resultSet.getFloat("amount")
                );
                return b;
            }


        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
        return null;
    }

    @Override
    public Bid setNewBid(Bid b) throws DALException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("INSERT INTO BID VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, b.getUser().getId());
            stmt.setInt(2, b.getArticleSold().getId());
            stmt.setDate(3, new Date(b.getDate().getTime()));
            stmt.setFloat(4, b.getAmount());
            stmt.setString(5, null);

            int resultSet = stmt.executeUpdate();

            return b;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }

    @Override
    public Bid getBidForUserAndArticle(User u, Article a) throws DALException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM bid WHERE idArticle=? AND idUser=?");
            stmt.setInt(1, u.getId());
            stmt.setInt(2, a.getId());

            ResultSet resultSet = stmt.executeQuery();
            Date lastDate = null;
            Bid b = null;
            while (resultSet.next()) {
                b = new Bid(
                        u,
                        a,
                        lastDate,
                        resultSet.getFloat("amount")
                );
                return b;
            }
            return null;

        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }
}
