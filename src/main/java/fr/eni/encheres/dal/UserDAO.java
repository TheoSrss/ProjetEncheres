package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User>  {

    public User selectWithloginAndPassword(String login, String password) throws SQLException, DALException;
    public User insert(User user) throws DALException, SQLException;
    void updateUser(User user) throws DALException;
    public void deleteUser(int id) throws DALException;
    public User getUserById(int id) throws DALException;
    public User getUserByEmailAndUsername(User user) throws DALException;

    public User updateCredit(float price,int id) throws DALException;
}
