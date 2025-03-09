package control;

import entity.UnproductiveEmployee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.*;

public class EmployeeLogic {
    private static EmployeeLogic _instance;

    private EmployeeLogic() {
    }

    public static EmployeeLogic getInstance() {
        if (_instance == null)
            _instance = new EmployeeLogic();
        return _instance;
    }

    public ArrayList<UnproductiveEmployee> getUnproductiveEmployeesByDate(String startDate, String endDate) {
        ArrayList<UnproductiveEmployee> results = new ArrayList<>();
        String sql = "SELECT TblSalesEmployees.ID, TblSalesEmployees.employeeFirstName, TblSalesEmployees.employeeLastName, " +
                "TblSalesEmployees.employeePhoneNumber, TblSalesEmployees.employeeOfficeAddressCity, " +
                "TblSalesEmployees.employeeOfficeAddressStreet, TblSalesEmployees.employeeOfficeAddressBulding, " +
                "TblSalesEmployees.employeeEmail, TblSalesEmployees.employeeStartDate, " +
                "Count(TblRegularOrders.orderNumber) AS CountRegularOrders, " +
                "Count(TblUrgentOrders.orderNumber) AS CountUrgentOrders, " +
                "Count(TblRegularOrders.orderNumber)+Count(TblUrgentOrders.orderNumber) AS TotalOrders " +
                "FROM (TblSalesEmployees " +
                "LEFT JOIN TblRegularOrders ON TblSalesEmployees.ID = TblRegularOrders.orderEmployee) " +
                "LEFT JOIN TblUrgentOrders ON TblSalesEmployees.ID = TblUrgentOrders.orderEmployee " +
                "WHERE (((TblRegularOrders.orderDate) Between ? And ? Or (TblRegularOrders.orderDate) Is Null) " +
                "AND ((TblUrgentOrders.orderDate) Between ? And ? Or (TblUrgentOrders.orderDate) Is Null)) " +
                "GROUP BY TblSalesEmployees.ID, TblSalesEmployees.employeeFirstName, TblSalesEmployees.employeeLastName, " +
                "TblSalesEmployees.employeePhoneNumber, TblSalesEmployees.employeeOfficeAddressCity, " +
                "TblSalesEmployees.employeeOfficeAddressStreet, TblSalesEmployees.employeeOfficeAddressBulding, " +
                "TblSalesEmployees.employeeEmail, TblSalesEmployees.employeeStartDate " +
                "HAVING (((Count(TblRegularOrders.orderNumber))<4)) OR (((Count(TblUrgentOrders.orderNumber))<2)) " +
                "ORDER BY Count(TblRegularOrders.orderNumber)+Count(TblUrgentOrders.orderNumber) DESC";

        try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            stmt.setString(3, startDate);
            stmt.setString(4, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new UnproductiveEmployee(
                            rs.getString("ID"),
                            rs.getString("employeeFirstName") + " " + rs.getString("employeeLastName"),
                            rs.getInt("TotalOrders")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}
