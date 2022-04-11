package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.CategoryDao;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOJdbcImpl implements CategoryDao {
    PreparedStatement stmt = null;
    Connection con = null;

    @Override
    public Category insert(Category cat) throws DALException, SQLException {
        return null;
    }

    @Override
    public Category getByID(int id) throws DALException, SQLException {
        Category cat=null;
        try{
            con = ConnectionProvider.getConnection();

            stmt = con.prepareStatement("SELECT * FROM CATEGORY WHERE id=?");

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                cat = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("libelle")
                );
                return cat;
            }

        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }

        return null;
    }

    public ArrayList<Category> getAllCategory() throws DALException, SQLException {

        try {
            ArrayList<Category> categories = new ArrayList<Category>();

            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM category");

            ResultSet resultSet = stmt.executeQuery();
            int a = 0;
            while (resultSet.next()) {
                Category cat = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("libelle")
                );
                categories.add(a, cat);
                a++;
            }
            return categories;
        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }
    }
}
