package boundary;

import control.ManufactureLogic;
import control.WineLogic;
import entity.Manufacture;
import entity.ManufactureDetails;
import entity.Wine;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.swing.event.*;
import java.util.List;
import java.util.ArrayList;


public class FrmManufactureDetailsInternal extends JPanel {

	private static final long serialVersionUID = 1L;
	private int iteration;
	private static JTable table;
	private JScrollPane scrollPane;
	private static DefaultTableModel tableModel;
	private static JComboBox<Item> comboBoxProductName;
	private JButton btnSaveOrderDetails = new JButton("Save");
	private JButton btnDeleteOrderDetails;
	private JLabel lblTotalOrderPrice;
	private JTextField tfTotalOrderPrice;
	private JTextField tfCatalogNumber = new JTextField();
	private JTextField tfName = new JTextField();
	private JTextField tfDescription = new JTextField();
	private JTextField tfProductionYear = new JTextField();
	private JTextField tfPricePerBottle = new JTextField();
	private JTextField tfSweetnessLevel = new JTextField();
	private JTextField tfProductImage = new JTextField();
	private JTextField tfWineType = new JTextField();
	private String manifactureNumber;
	private static ArrayList<Wine> wines = new ArrayList<Wine>();
	private ArrayList<Manufacture> manufactures = new ArrayList<Manufacture>();

	public FrmManufactureDetailsInternal() {
		iteration = 0;
		initComponents();
	}

	public void initComponents() {

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		tableModel = new DefaultTableModel();
		String header[] = new String[]{"catalog Number", " Name", "description", "production Year", "price PerBottle","sweetness Level","productI mage","wine Type"};
		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		table.setRowHeight(25);

		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500, 165));
		scrollPane.setMaximumSize(new Dimension(450, 20000));
		add(scrollPane);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This method is been used by the primary form when user browse between orders
//This method clear table rows and selections and set data according to order selected in primary form

	/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void refreshComp(String manifactureNumber) {
		setManifactureNumber(manifactureNumber);
		initTableData();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//This method set the order to be displayed
	/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setManifactureNumber(String manifactureNumber) {
		this.manifactureNumber = manifactureNumber;
	}

	/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initTableData() {
		//Following code clear table (used while browsing between orders)
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		// מה שהיה
		List <Wine> wine = WineLogic.getWineInfoByManufacturer(this.manifactureNumber);

		int i = 0;
		while (i < wine.size()) {
			Vector<Object> data = new Vector<Object>();
			System.out.println(wine.get(i).catalogNumber+"try");
			data.add(wine.get(i).catalogNumber);
			data.add(wine.get(i).name);
			data.add(wine.get(i).description);
			data.add(wine.get(i).productionYear);
			data.add(wine.get(i).pricePerBottle);
			data.add(wine.get(i).sweetnessLevel);
			data.add(wine.get(i).productImage);
			data.add(wine.get(i).wineType);
			i++;
			tableModel.addRow(data);
		}

		// 	Add empty Row
		addEmptyRow();

		// 	First row is been focused and selected by default
		table.changeSelection(0, 0, false, false);
		table.requestFocus();

		//Notifies all listeners that all cell values in the table's rows may have changed.
		tableModel.fireTableDataChanged();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This method add Empty Row in order to allow adding new items
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void addEmptyRow () {
		Vector<Object> data = new Vector<Object>();
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.addRow(data);
	}
}
