package control;

import entity.Consts;

import javax.management.Query;
import java.sql.*;

public class AccessDataBase {
//    public static void main(String[] args) { /Users/maksimaverman/Desktop/proga/HW2_315063081_337759070/src/entity/database.accdb
//        query("select * FROM TblWines");
//
//    }


    public static void main(String[] args) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://Users/maksimaverman/Desktop/proga/HW2_315063081_337759070/src/entity/database.accdb");
            System.out.println("Connection established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }
}
//    public static void query(String query){
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/entity/database.accdb");
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while(resultSet.next()){
//                int id = resultSet.getInt("wineCatalogNumber");
//                String name = resultSet.getString("wineName");
//                System.out.println("id = " + id + "," + " name = " + name);
//            }
//
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }


//    }
//}
