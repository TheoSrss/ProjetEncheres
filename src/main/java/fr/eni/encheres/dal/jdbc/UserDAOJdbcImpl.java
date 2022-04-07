package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

import java.sql.*;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {
    PreparedStatement stmt = null;
    Connection con = null;

    private static final String INSERT = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public User selectWithloginAndPassword(String login, String password) throws SQLException, DALException {
//        User user = null;

        try {

            stmt = con.prepareStatement("SELECT * FROM user WHERE (username=? OR email=?) AND password=?");

            stmt.setString(1, login);
            stmt.setString(2, login);
            stmt.setString(3, password);

            ResultSet resultSet = stmt.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("surname"),
                        resultSet.getString("firstName"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("street"),
                        resultSet.getInt("postalCode"),
                        resultSet.getString("city"),
                        resultSet.getString("password"),
                        resultSet.getInt("credit"),
                        resultSet.getBoolean("admin")

                );
            }
            return user;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }

    }

    @Override
    public User insert(User user) throws DALException {

        try {
            con = ConnectionProvider.getConnection();

            stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
//             stmt = con.prepareStatement("INSERT INTO USER VALUES(null,'d','d','d','d',5,'d',5,'d','d',2,true)");
//
            stmt.setString(1, null);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getEmail());
            stmt.setInt(6, user.getPhone());
            stmt.setString(7, user.getStreet());
            stmt.setInt(8, user.getPostalCode());
            stmt.setString(9, user.getCity());
            stmt.setString(10, user.getPassword());
            stmt.setDouble(11, user.getCredit());
            stmt.setBoolean(12, user.isAdmin());

            int nb = stmt.executeUpdate();

            if (nb > 0) {
                ResultSet r = stmt.getGeneratedKeys();
                if (r.next()) {
                    user.setId(r.getInt(1));
                }

            }
            return user;


//            Connection con = ConnectionProvider.getConnection();
//
//            stmt = con.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
//            stmt = con.prepareStatement();

//            stmt.setInt(1, 8);
//            stmt.setString(2, user.getUsername());
//            stmt.setString(3, user.getSurname());
//            stmt.setString(4, user.getFirstName());
//            stmt.setString(5, user.getEmail());
//            stmt.setString(6, user.getPhone());
//            stmt.setString(7, user.getStreet());
//            stmt.setString(8, user.getPostalCode());
//            stmt.setString(9, user.getCity());
//            stmt.setString(10, user.getPassword());
//            stmt.setInt(11, user.getCredit());
//            stmt.setBoolean(12, user.isAdmin());

//            stmt.executeUpdate();

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
