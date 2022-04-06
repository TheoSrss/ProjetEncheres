package fr.eni.encheres.bo.dal;

import fr.eni.encheres.bo.dal.jdbc.UserDAOJdbcImpl;

public class DAOFactory {


    public static UserDAO getUserDao() {
        UserDAO userDAO = new UserDAOJdbcImpl();

        return userDAO;
    }

}
