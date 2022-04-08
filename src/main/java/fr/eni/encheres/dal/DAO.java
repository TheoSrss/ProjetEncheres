package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {

    public T insert(T t) throws DALException, SQLException;

    public void deleteUser(int id) throws DALException;
    public User getUserById(int id) throws DALException;
    public void updateUser(T t) throws DALException;
}

