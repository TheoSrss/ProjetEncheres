package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User>  {

    public boolean selectWithloginAndPassword(String login, String password) throws SQLException, DALException;
}
