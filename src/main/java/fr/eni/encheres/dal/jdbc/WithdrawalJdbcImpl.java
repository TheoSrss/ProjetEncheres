package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.WithDrawalDAO;

import java.sql.*;

public class WithdrawalJdbcImpl implements WithDrawalDAO {
    PreparedStatement stmt = null;
    Connection con = null;

    @Override
    public Withdrawal addAddress(Withdrawal w) throws DALException {

        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("INSERT INTO WITHDRAWAL VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, w.getStreet());
            stmt.setInt(3, w.getPostalCode());
            stmt.setString(4, w.getCity());

            int nb = stmt.executeUpdate();
            if (nb > 0) {
                ResultSet r = stmt.getGeneratedKeys();
                if (r.next()) {
                    w.setId(r.getInt(1));
                }
            }
            con.close();
            return w;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }

    @Override
    public Withdrawal updateAddress(Withdrawal w) throws DALException, SQLException {
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("UPDATE withdrawal SET street=?, postalCode=?, city=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, w.getStreet());
            stmt.setInt(2, w.getPostalCode());
            stmt.setString(3, w.getCity());
            stmt.setInt(4, w.getId());

            int nb = stmt.executeUpdate();
            if (nb > 0) {
                ResultSet r = stmt.getGeneratedKeys();
                if (r.next()) {
                    w.setId(r.getInt(1));
                }
            }
            con.close();
            return w;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }


    @Override
    public Withdrawal insert(Withdrawal withdrawal) throws DALException, SQLException {
        return null;
    }
}
