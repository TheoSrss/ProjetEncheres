package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

import java.sql.*;

public class ArticleDAOJdbcImpl implements ArticleDao {
    PreparedStatement stmt = null;
    Connection con = null;

    @Override
    public Article insert(Article a) throws DALException, SQLException {

        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("INSERT INTO ARTICLESOLD VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, null);
            stmt.setString(2, a.getName());
            stmt.setString(3, a.getDescription());
            stmt.setDate(4, new Date(a.getDateStartBid().getTime()));
            stmt.setDate(5, new Date(a.getDateEndBid().getTime()));
            stmt.setFloat(6, a.getInitialPrice());
            stmt.setFloat(7, a.getSoldPrice());
            stmt.setString(8, a.getStateSale());
            stmt.setInt(9, a.getUser().getId());
            stmt.setInt(10, a.getCategory().getId());
            stmt.setInt(11, a.getWithdrawal().getId());

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
}
