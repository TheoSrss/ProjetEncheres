package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;
import fr.eni.encheres.bll. BLLException;

import java.sql.SQLException;

public class UserManager {
    private static UserManager userManager;
    private static UserDAO userDAO;

    private UserManager() {
        userDAO = DAOFactory.getUserDao();
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public static boolean connexionIsGood(String login, String password) throws BLLException, DALException, SQLException {

        boolean a=userDAO.selectWithloginAndPassword(login,password);

        return false;
    }


}
