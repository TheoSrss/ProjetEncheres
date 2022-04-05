package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

import java.sql.*;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {

    @Override
    public boolean selectWithloginAndPassword(String login, String password) throws SQLException, DALException {
        User user = null;
        PreparedStatement stmt = null;
        Connection con = null;

        try {
            Connection connection = ConnectionProvider.getConnection();
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/encheres", "root", "");

            Statement statement = connection.createStatement();
            ResultSet re = statement.executeQuery("select * from user");
            con = ConnectionProvider.getConnection();

            String r = "SELECT * FROM user WHERE (username=? OR email=?) AND password=?";

            stmt = con.prepareStatement(r);

            stmt.setString(1, login);
            stmt.setString(2, login);
            stmt.setString(3, password);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet != null) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }

    }

    @Override
    public void insert(User user) throws DALException {

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
