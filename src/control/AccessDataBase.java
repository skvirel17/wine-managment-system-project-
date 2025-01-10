package control;

import entity.Consts;

import javax.management.Query;
import java.sql.*;

import static entity.Consts.CONN_STR;

public class AccessDataBase {

    public static void main(String[] args) {
        try {
            System.setProperty("ucanaccess.disable.autoLoadFunctions", "true");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/database.accdb");
            System.out.println("Connection established successfully!");
            query("select * FROM TblWines", connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void query(String query, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("wineCatalogNumber");
                String name = resultSet.getString("wineName");
                System.out.println("id = " + id + "," + " name = " + name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

