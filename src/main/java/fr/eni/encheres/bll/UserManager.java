package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.dal.DALException;
import fr.eni.encheres.bo.dal.DAOFactory;
import fr.eni.encheres.bo.dal.UserDAO;

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

    public void registration(User user) throws BLLException, DALException {

        try {
            userDAO.insert(user);

        }catch (DALException e) {
            throw new BLLException("Fail registration");
        }
    }


}