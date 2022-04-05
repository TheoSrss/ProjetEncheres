package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;

public class DAOFactory {


    public static UserDAO getUserDao() {
        UserDAO userDAO = new UserDAOJdbcImpl();

        return userDAO;
    }

}
