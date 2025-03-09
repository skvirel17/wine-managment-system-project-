package control;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import com.sun.org.apache.xpath.internal.operations.Or;
import entity.*;
import enums.OccasionE;
import enums.OrderStatus;
import enums.WineTypeE;

public class OrderLogic {
        private static OrderLogic _instance;

        private OrderLogic() { }

        public static OrderLogic getInstance() {
            if (_instance == null)
                _instance = new OrderLogic();
            return _instance;
        }

        public ArrayList<Order> getOrders() {
            ArrayList<Order> results = new ArrayList<>();

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                     PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ORDERS);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        results.add(new Order(
                                rs.getInt("orderNumber"),
                                rs.getDate("orderDate"),
                                OrderStatus.fromId(rs.getInt("orderCurrentStatus")),
                                rs.getDate("orderShipmentDate"),
                                rs.getString("orderEmployee"),
                                rs.getInt("orderCountBottle"),
                                rs.getInt("wineCatalogNumber")
                        ) );
                    }
                    serializeOrders(results, "orders.ser");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return results;
        }

        private void serializeOrders(ArrayList<Order> orders, String fileName) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(orders);
                System.out.println("Data serialized successfully: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean addOrder(Order order) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                     PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_ORDER)) {

                    stmt.setInt(1, order.getPriorityLevel());
                    stmt.setInt(2, order.getOrderNumber());
                    stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
                    stmt.setObject(4, order.getOrderStatus(), java.sql.Types.VARCHAR);
                    stmt.setDate(5, new java.sql.Date(order.getShipmentDate().getTime()));
                    stmt.setString(6, order.getEmployeeId());
                    stmt.setDouble(7, order.getTotalPrice());
                    stmt.setInt(8, order.getCountBottle());

                    int affectedRows = stmt.executeUpdate();
                    return affectedRows > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean removeOrder(String orderNumber) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                     PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DEL_ORDER)) {

                    stmt.setString(1, orderNumber);

                    int affectedRows = stmt.executeUpdate();
                    return affectedRows > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }

    public boolean editOrder(Order order) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_ORDER)) {

                stmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));

                // Поскольку orderCurrentStatus — это Number, передаем его как Integer
                if (order.getOrderStatus() != null) {
                    stmt.setInt(2, order.getOrderStatus().getId()); // Передаем ID статуса
                } else {
                    stmt.setNull(2, java.sql.Types.INTEGER); // Если статус пустой, ставим NULL
                }

                stmt.setDate(3, new java.sql.Date(order.getShipmentDate().getTime()));
                stmt.setInt(4, Integer.parseInt(order.getEmployeeId())); // Преобразуем строку в число
                stmt.setInt(5, order.getOrderNumber());

                int affectedRows = stmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }





    // 56
        public ArrayList<Order> getOrderDetails(int orderNumber) {
            ArrayList<Order> results = new ArrayList<>();

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                     PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ORDERS)) {
                    stmt.setInt(1, orderNumber);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            String orderNum = rs.getString("orderNumber");
                            String orderStatusStr = rs.getString("orderstatus");
                            OrderStatus orderStatus = OrderStatus.fromValue(orderStatusStr);

                            Order order = new Order(
                                    orderNumber,
                                    rs.getDate("orderDate"),
                                    orderStatus,
                                    rs.getDate("shipmentDate"),
                                    rs.getString("employeeId")
                            );
                            results.add(order);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return results;
        }

//
//    public List<OrderDTO> getFilteredOrdersFromDB(List<String> orderStatuses) {
//        List<OrderDTO> orderList = new ArrayList<>();
//        String dbURL = Consts.CONN_STR;
//
//        try (Connection conn = DriverManager.getConnection(dbURL)) {
//            StringBuilder queryBuilder = new StringBuilder("""
//                    SELECT
//                        TblOrders.orderNumber,
//                        TblOrders.orderDate,
//                        TblOrders.orderstatus,
//                        TblOrders.shipmentDate,
//                        TblOrders.employeeId,
//                        TblOrders.totalPrice
//                    FROM TblOrders
//                    WHERE 1=1
//                """);
//
//            // הוספת פילטר לפי סטטוס הזמנה
//            if (!orderStatuses.isEmpty()) {
//                queryBuilder.append(" AND TblOrders.orderstatus IN  " +
//                        "('" + orderStatuses.stream().collect(Collectors.joining("', '")) + "')");
//            }
//
//            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryBuilder.toString())) {
//                while (rs.next()) {
//                    String orderNumber = rs.getString("orderNumber");
//                    String orderStatusStr = rs.getString("orderstatus");
//                    OrderStatus orderStatus = OrderStatus.fromValue(orderStatusStr);
//
//                    OrderDTO order = new OrderDTO(
//                            orderNumber,
//                            rs.getDate("orderDate"),
//                            orderStatus,
//                            rs.getDate("shipmentDate"),
//                            rs.getString("employeeId"),
//                            rs.getDouble("totalPrice")
//                    );
//                    orderList.add(order);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return orderList;
//    }
    }


