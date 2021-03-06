package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.bo.Withdrawal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryDao {

    public Category insert(Category cat) throws DALException, SQLException;
    public Category getByID(int id) throws DALException, SQLException;

    public ArrayList<Category> getAllCategory() throws DALException, SQLException;

}
