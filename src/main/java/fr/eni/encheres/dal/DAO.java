package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

import java.util.List;


public interface DAO<T> {

    public T insert(T t) throws DALException;

    public List<T> selectAll() throws DALException;

    public T selectByID(int id) throws DALException;

    public void update(T t) throws DALException;

    public void delete(int id) throws DALException;



}

