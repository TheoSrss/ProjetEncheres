package fr.eni.encheres.dal.jdbc;

import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) throws SQLException {
//        try {
        String s = "$";
        System.out.println(s.matches("^[a-zA-Z0-9]*$"));
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/encheres", "root", "");
////            Connection connection = ConnectionProvider.getConnection();;
//
//            Statement statement = connection.createStatement();
//            ResultSet re = statement.executeQuery("select * from user");
//
//
//            while (re.next()) {
//                System.out.println(re.getString("username"));
//            }

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
