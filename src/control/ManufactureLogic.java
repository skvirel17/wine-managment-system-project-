package control;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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


    public ArrayList <Manufacture> getManufactures() {
		ArrayList<Manufacture> results = new ArrayList<>();

		try {
			// Загружаем драйвер
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			// Подключаемся к базе данных
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_MANUFACTURES);
				 ResultSet rs = stmt.executeQuery()) {

				// Читаем данные из базы
				while (rs.next()) {
					results.add(new Manufacture(
							rs.getString("manufactureId"),
							rs.getString("manufactureFullName"),
							rs.getInt("manufacturePhoneNumber"),
							rs.getString("manufactureAddressCity") + " " +
									rs.getString("manufactureAddressStreet") + " " +
									rs.getString("manufactureAddressBulding"),
							rs.getString("manufactureEmail")
					));
				}


				serializeManufactures(results, "database.ser");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return results;
	}

	private void serializeManufactures(ArrayList<Manufacture> manufactures, String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(manufactures);
			System.out.println("Data serialized sucsesfuly: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	/**
	 * Добавление нового производителя
	 */
	public boolean addManufacture(Manufacture manufacture) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_MANUFACTURE)) {

				stmt.setString(1, manufacture.getManifactureNumber());
				stmt.setString(2, manufacture.getFullName());
				stmt.setInt(3, manufacture.getPhoneNumber());
				stmt.setString(4, manufacture.getAddressManifacture());
				stmt.setString(5, manufacture.getEmail());

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

	/**
	 * Удаление производителя по ID
	 */
	public boolean removeManufacture(String manufactureId) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DEL_MANUFACTURE)) {

				stmt.setString(1, manufactureId);

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

	/**
	 * Редактирование информации о производителе
	 */
	public boolean editManufacture(Manufacture manufacture) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_MANUFACTURE)) {

				stmt.setString(1, manufacture.getFullName());
				stmt.setInt(2, manufacture.getPhoneNumber());
				stmt.setString(3, manufacture.getAddressManifacture());
				stmt.setString(4, manufacture.getEmail());
				stmt.setString(5, manufacture.getManifactureNumber());

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

	public static List<Wine> getWineInfoByManufacturer(String manufactureNumber) {
	List<Wine> wineInfo = new ArrayList<Wine>();
	 return wineInfo;
	}
}
