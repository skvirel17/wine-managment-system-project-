package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import entity.Consts;
import entity.Manufacture;
import entity.ManufactureDetails;
import entity.Wine;
//import entity.Order ;
//import entity.ManufactureDetails ;
//import entity.Product;
//
//import entity.Shipper;
//import entity.Customer;
//import entity.Employee;




public class ManufactureLogic {
	private static ManufactureLogic _instance;

    private ManufactureLogic() { }

    public static ManufactureLogic getInstance() {
        if (_instance == null)
            _instance = new ManufactureLogic();
        return _instance;
    }

  //////////////////////////////////////////////////////////////////
  //READ ORDERS FROM DB
  //////////////////////////////////////////////////////////////////
//    public ArrayList <Customer> getCustomers() {
//      	 ArrayList<Customer> results = new ArrayList<Customer>();
//
//   	        try {
//   	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//   	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//   	                    PreparedStatement stmt =
//   	                            conn.prepareStatement(Consts.SQL_SEL_CUSTOMERS);
//   	                    ResultSet rs = stmt.executeQuery()) {
//   	                while (rs.next()) {
//   	                    int i = 1;
//   	                    results.add(new Customer(rs.getString(i++), rs.getString(i++) ));
//   	                }
//   	            } catch (SQLException e) {
//   	                e.printStackTrace();
//   	            }
//   	        } catch (ClassNotFoundException e) {
//   	            e.printStackTrace();
//   	        }
//
//   	        return results;
//      }
//
//    public ArrayList <Shipper> getshippers() {
//     	 ArrayList<Shipper> results = new ArrayList<Shipper>();
//
//  	        try {
//  	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//  	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//  	                    PreparedStatement stmt =
//  	                            conn.prepareStatement(Consts.SQL_SEL_SHIPPERS);
//  	                    ResultSet rs = stmt.executeQuery()) {
//  	                while (rs.next()) {
//  	                    int i = 1;
//  	                    results.add(new Shipper(rs.getLong(i++), rs.getString(i++) ));
//  	                }
//  	            } catch (SQLException e) {
//  	                e.printStackTrace();
//  	            }
//  	        } catch (ClassNotFoundException e) {
//  	            e.printStackTrace();
//  	        }
//
//  	        return results;
//     }
//
//    public ArrayList <Product> getProducts() {
//     	 ArrayList<Product> results = new ArrayList<Product>();
//
//  	        try {
//  	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//  	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//  	                    PreparedStatement stmt =
//  	                            conn.prepareStatement(Consts.SQL_SEL_PRODUCTS);
//  	                    ResultSet rs = stmt.executeQuery()) {
//  	                while (rs.next()) {
//  	                    int i = 1;
//  	                    results.add(new Product(Long.parseLong(rs.getString(i++)), rs.getString(i++),Long.parseLong(rs.getString(i++)),Long.parseLong(rs.getString(i++)),new BigDecimal( rs.getString(i++)),Integer.parseInt(rs.getString(i++)) ));
//  	                }
//  	            } catch (SQLException e) {
//  	                e.printStackTrace();
//  	            }
//  	        } catch (ClassNotFoundException e) {
//  	            e.printStackTrace();
//  	        }
//
//  	        return results;
//     }
//
//
    public ArrayList <Manufacture> getManufactures() {
   	 ArrayList<Manufacture> results = new ArrayList<Manufacture>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C://database.accdb");
	                    PreparedStatement stmt =
	                            conn.prepareStatement(Consts.SQL_SEL_MANUFACTURES);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    results.add(new Manufacture(rs.getString("manufactureId"),
								rs.getString("manufactureFullName"),
	                            rs.getInt("manufacturePhoneNumber"),
								(rs.getString("manufactureAddressCity") + " " +
								rs.getString("manufactureAddressStreet") + " " +
								rs.getString("manufactureAddressBulding")),
								rs.getString("manufactureEmail")));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return results;
   }
//    public boolean addOrder(String customerID, long employeeID,Date orderDate,Date requiredDate,
//    		Date shippedDate,int shipVia,String shipAddress,String shipCity,String shipCountry) {
//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//                    CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_ORDER)) {
//                int i = 1;
//
//                stmt.setString(i++, customerID);  // can't be null
//                stmt.setLong(i++, employeeID); // can't be null
//
//
//                if (orderDate != null)
//                    stmt.setDate(i++, new java.sql.Date(orderDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//                if (requiredDate != null)
//                    stmt.setDate(i++, new java.sql.Date(requiredDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//                if (shippedDate != null)
//                    stmt.setDate(i++, new java.sql.Date(shippedDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//
//
//                stmt.setInt(i++, shipVia); // can't be null
//
//                if (shipAddress != null)
//                    stmt.setString(i++, shipAddress);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//                if (shipCity != null)
//                    stmt.setString(i++, shipCity);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//                if (shipCountry != null)
//                    stmt.setString(i++, shipCountry);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//
//
//                stmt.executeUpdate();
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//
//    }
//    public boolean removeOrder(long orderID) {
//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//            		CallableStatement stmt1 = conn.prepareCall(Consts.SQL_DEL_ORDER_DETAILS);
//
//            		) {
//                stmt1.setLong(1, orderID);
//                stmt1.executeUpdate();
//
//
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//
//            		CallableStatement stmt2 = conn.prepareCall(Consts.SQL_DEL_ORDER)
//            		) {
//
//                stmt2.setLong(1, orderID);
//                stmt2.executeUpdate();
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public boolean editOrder(long orderID, String customerID, long employeeID,Date orderDate,Date requiredDate,
//    		Date shippedDate,int shipVia,String shipAddress,String shipCity,String shipCountry) {
//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//                    CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_ORDER)) {
//                int i = 1;
//
//                stmt.setString(i++, customerID);  // can't be null
//                stmt.setLong(i++, employeeID); // can't be null
//
//
//                if (orderDate != null)
//                    stmt.setDate(i++, new java.sql.Date(orderDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//                if (requiredDate != null)
//                    stmt.setDate(i++, new java.sql.Date(requiredDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//                if (shippedDate != null)
//                    stmt.setDate(i++, new java.sql.Date(shippedDate.getTime()));
//                else
//                    stmt.setNull(i++, java.sql.Types.DATE);
//
//
//                stmt.setInt(i++, shipVia); // can't be null
//
//                if (shipAddress != null)
//                    stmt.setString(i++, shipAddress);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//                if (shipCity != null)
//                    stmt.setString(i++, shipCity);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//                if (shipCountry != null)
//                    stmt.setString(i++, shipCountry);
//                else
//                    stmt.setNull(i++, java.sql.Types.VARCHAR);
//
//                stmt.setLong(i++, orderID);
//
//                stmt.executeUpdate();
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//
//
    public ArrayList <Manufacture> getManufactureDetails(String manifactureNumber) {
   	 ArrayList<Manufacture> results = new ArrayList<Manufacture>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =
	                            conn.prepareStatement(Consts.SQL_SEL_MANUFACTURES_DETAILS);)
	            {

	            	stmt.setString(1, manifactureNumber);
	            	ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    int i = 1;
	                    results.add(new Manufacture(rs.getString(i++), rs.getString(i++),
								rs.getInt(i++), rs.getString(i++), rs.getString(i++)));


	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return results;
   }
    public boolean editManufactureDetails(long orderID, long productID, int quantity,float discount) {
//
//           try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//            try (
//            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//                CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_ORDER_DETAILS))
//            {
//
//                int i = 1;
//
//                 stmt.setInt(i++, quantity);
//
//                 stmt.setFloat(i++, discount);// can't be null
//
//
//                stmt.setLong(i++, orderID); // can't be null
//
//                stmt.setLong(i++, productID); // can't be null
//
//
//                stmt.executeUpdate();
//
//
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return false;
    }
//    public boolean addOrderDetails(long orderID, long productID, int quantity,float discount) {
//
//        try {
//         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//         try (
//         	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//             CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_ORDER_DETAILS))
//         {
//
//             int i = 1;
//
//             stmt.setLong(i++, orderID); // can't be null
//             stmt.setLong(i++, productID); // can't be null
//             stmt.setInt(i++, quantity);
//             stmt.setFloat(i++, discount);// can't be null
//             stmt.executeUpdate();
//
//
//             return true;
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//     }
//     return false;
// }
//    public boolean removeOrderDetail(long orderID,long productID) {
//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//
//            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//            		CallableStatement stmt1 = conn.prepareCall(Consts.SQL_DEL_ORDER_DETAILS_PRODUCT);
//
//            		) {
//                stmt1.setLong(1, orderID);
//                stmt1.setLong(2, productID);
//                stmt1.executeUpdate();
//
//
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
public static List<Wine> getWineInfoByManufacturer(String manufactureNumber) {
	List<Wine> wineInfo = new ArrayList<Wine>();
	 return wineInfo;
}


}
