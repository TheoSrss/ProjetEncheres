package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User>  {

    public User selectWithloginAndPassword(String login, String password) throws SQLException, DALException;
    public User insert(User user) throws DALException, SQLException;
    public User getUserById(int id) throws DALException, SQLException;
}
