package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {
    PreparedStatement stmt = null;
    Connection con = null;

    @Override
    public boolean selectWithloginAndPassword(String login, String password) throws SQLException, DALException {
        User user = null;

        try {
//            ResultSet re = statement.executeQuery("select * from user");
            con = ConnectionProvider.getConnection();

            stmt = con.prepareStatement("SELECT * FROM user WHERE (username=? OR email=?) AND password=?");

            stmt.setString(1, login);
            stmt.setString(2, login);
            stmt.setString(3, password);

            ResultSet resultSet = stmt.executeQuery();
            System.out.println(resultSet);
            boolean exist = false;
            while (resultSet.next()) {
                exist = true;
            }
            System.out.println(exist);
            return exist;

        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }

    }

    @Override
    public boolean insert(User user) throws DALException {

        try {
            Connection con = ConnectionProvider.getConnection();

            stmt = con.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, 8);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPhone());
            stmt.setString(7, user.getStreet());
            stmt.setString(8, user.getPostalCode());
            stmt.setString(9, user.getCity());
            stmt.setString(10, user.getPassword());
            stmt.setInt(11, user.getCredit());
            stmt.setBoolean(12, user.isAdmin());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            System.out.println("ooooo");
//            if (rs.next()) {
//                System.out.println(rs);
////                user.setn(rs.getInt(1));
//            }
//            if(rs.next()) {
//                System.out.println(rs.getInt(1));
//            }
return true;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }


    }

    @Override
    public List<User> selectAll() throws DALException {
        return null;
    }

    @Override
    public User selectByID(int id) throws DALException {
        return null;
    }

    @Override
    public void update(User user) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }
}
