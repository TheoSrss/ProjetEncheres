package fr.eni.encheres.bo.dal.jdbc;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.dal.ConnectionProvider;
import fr.eni.encheres.bo.dal.DALException;
import fr.eni.encheres.bo.dal.UserDAO;

import java.sql.*;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {
    PreparedStatement stmt = null;
    Connection con = null;
    @Override
    public boolean selectWithloginAndPassword(String login, String password) throws SQLException, DALException {
        User user = null;

        try {
            Connection connection = ConnectionProvider.getConnection();
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/encheres", "root", "");

            Statement statement = connection.createStatement();
            ResultSet re = statement.executeQuery("select * from user");
            con = ConnectionProvider.getConnection();

            String request = "SELECT * FROM user WHERE (username=? OR email=?) AND password=?";

            stmt = con.prepareStatement(request);

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

        try {
            Connection connection = ConnectionProvider.getConnection();


            String request = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(request);

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

            stmt.executeQuery();

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
