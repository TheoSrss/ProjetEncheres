package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBC {

    public static void main(String[] args) throws SQLException, DALException {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("SELECT * FROM category");

            ResultSet resultSet = stmt.executeQuery();
            List<Category> categories = null;

            while (resultSet.next()) {
                categories.add(categories.size(), (Category) resultSet);

            }
            System.out.println(categories);
//            return categories;

        } catch (SQLException e) {
            throw new DALException("Couche DAL - " + e);
        }

    }
}
