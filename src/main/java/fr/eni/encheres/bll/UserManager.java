package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

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

    public static User connexionIsGood(String login, String password) throws BLLException, DALException, SQLException {
        return userDAO.selectWithloginAndPassword(login,password);
    }

    public User registration(User user) throws BLLException, DALException {
        try {
            User userReturn=userDAO.insert(user);
            return userReturn;

        }catch (DALException e) {
            throw new BLLException("BLL "+ e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserById(int id) throws BLLException, DALException, SQLException {

        return userDAO.getUserById(id);
    }
    public void deleteUser(int id) throws BLLException, DALException, SQLException {

        userDAO.deleteUser(id);
    }

    public void updateUser(User user) throws DALException {
        userDAO.updateUser(user);
    }
}
