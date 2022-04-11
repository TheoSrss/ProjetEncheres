package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.CategoryDao;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private static CategoryManager categoryManager;
    private static CategoryDao categoryDAO;

    private CategoryManager() {
        categoryDAO = DAOFactory.getCategoryDao();
    }

    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }

    public ArrayList<Category> getAllCategory() throws DALException, SQLException {

        return categoryDAO.getAllCategory();
    }
}
