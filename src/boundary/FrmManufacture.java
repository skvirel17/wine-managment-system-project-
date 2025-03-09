package boundary;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.ManufactureLogic;
import entity.Manufacture;


import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;


public class FrmManufacture extends RootLayout {

	private ArrayList<Manufacture> manufacturesArray;
	private Integer currentManufacture;
	private boolean inAddMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManufacture frame = new FrmManufacture();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManufacture() {
		initComponents();
		fetchAndRefresh();
		createEvents();
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 521);
		contentPane = new JPanel();
		updateMenuSelectionManufactures();
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Manifactures Details");
		contentPane.setLayout(null);
		pnlWineDetails.setBounds(21, 11, 700, 171);
		pnlWineDetails.setBorder(null);
		contentPane.add(pnlWineDetails);
		tfManufactureNumber = new JTextField();
		tfManufactureNumber.setBounds(160, 11, 250, 20);
		tfManufactureNumber.setEditable(true);
		tfManufactureNumber.setColumns(10);

		tfName = new JTextField();
		tfName.setEditable(true);
		tfName.setColumns(20);
		tfName.setBounds(160, 37, 250, 20);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setEditable(true);
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(160, 63, 250, 20);

		tfEmail = new JTextField();
		tfEmail.setEditable(true);
		tfEmail.setColumns(20);
		tfEmail.setBounds(160, 89, 250, 20);

		tfManufactureAddress = new JTextField();
		tfManufactureAddress.setColumns(20);
		tfManufactureAddress.setEditable(true);
		tfManufactureAddress.setBounds(160, 115, 250, 20);

		//pnlPhoneNumberManifacture.setBounds(370, 11, 355, 156);
		//contentPane.add(pnlPhoneNumberManifacture);

		pnlActionBtn = new JPanel();
 		pnlActionBtn.setBounds(21, 400, 607, 33);
		tfNavigation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNavigation.setText("showing X of Y");
		tfNavigation.setColumns(6);
		contentPane.add(pnlActionBtn);
		pnlActionBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFirst.setHorizontalAlignment(SwingConstants.LEFT);
		pnlActionBtn.add(btnFirst);
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 10));
		pnlActionBtn.add(btnPrev);
		pnlActionBtn.add(tfNavigation);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 10));
		pnlActionBtn.add(btnNext);
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 10));
		pnlActionBtn.add(btnLast);

		pnlActionBtn.add(btnAdd);
		pnlActionBtn.add(btnSave);
		pnlActionBtn.add(btnDelete);

		btnSave.setEnabled(true);
		btnDelete.setEnabled(true);


        initGroupLayoutleftPanel();
        initGroupLayoutRightPanel();

        panel = new FrmManufactureDetailsInternal();
        panel.setForeground(SystemColor.control);
        panel.setBackground(SystemColor.activeCaptionBorder);
        panel.setBounds(31, 193, 515, 171);
        contentPane.add(panel);
	}

	private void createEvents() {

		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFirstOnClick(e);
			}
		});
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLastOnClick(e);
			}
		});
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnPrevOnClick(evt);
		}
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 btnNextOnClick(evt);
			}
		});
	}



	private void initGroupLayoutRightPanel() {
//			pnlPhoneNumberManifacture.setLayout(null);
//
//			JLabel lblShipVia = new JLabel("Ship Via:");
//			lblShipVia.setBounds(10, 14, 54, 14);
//			pnlPhoneNumberManifacture.add(lblShipVia);
//
//			tfShipViaID = new JTextField();
//			tfShipViaID.setColumns(3);
//			tfShipViaID.setBounds(84, 11, 37, 20);
//			pnlPhoneNumberManifacture.add(tfShipViaID);
//
//
//			cbShipViaName.setBounds(131, 11, 156, 20);
//			pnlPhoneNumberManifacture.add(cbShipViaName);
//
//			JPanel panel = new JPanel();
//			panel.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//			panel.setBounds(0, 39, 347, 112);
//			pnlPhoneNumberManifacture.add(panel);
//			panel.setLayout(null);
//
//			JLabel lblShipAddress = new JLabel("Ship Address:");
//			lblShipAddress.setBounds(10, 24, 79, 14);
//			panel.add(lblShipAddress);
//
//
//			tfShipAddress.setBounds(99, 21, 238, 20);
//			tfShipAddress.setColumns(3);
//			panel.add(tfShipAddress);
//
//			JLabel lblShipCity = new JLabel("Ship City:");
//			lblShipCity.setBounds(10, 51, 79, 14);
//			panel.add(lblShipCity);
//
//			tfShipCity = new JTextField();
//			tfShipCity.setColumns(3);
//			tfShipCity.setBounds(99, 48, 238, 20);
//			panel.add(tfShipCity);
//
//			JLabel lblShipCountry = new JLabel("Ship Country:");
//			lblShipCountry.setBounds(10, 79, 79, 14);
//			panel.add(lblShipCountry);
//
//			tfCountry = new JTextField();
//			tfCountry.setColumns(3);
//			tfCountry.setBounds(99, 76, 238, 20);
//			panel.add(tfCountry);

	}

	private void initGroupLayoutleftPanel() {
		pnlWineDetails.setLayout(null);
		lblManufactureNumber.setBounds(10, 14, 120, 14);
		pnlWineDetails.add(lblManufactureNumber);
		lblName.setBounds(10, 40, 120, 14);
		pnlWineDetails.add(lblName);
		lblPhoneNumber.setBounds(10, 66, 120, 14);
		pnlWineDetails.add(lblPhoneNumber);
		lblEmail.setBounds(10, 90, 120, 14);
		pnlWineDetails.add(lblEmail);
		lblAddress.setBounds(10, 115, 120, 14);
		pnlWineDetails.add(lblAddress);
		pnlWineDetails.add(tfManufactureNumber);
		pnlWineDetails.add(tfName);
		pnlWineDetails.add(tfPhoneNumber);
		pnlWineDetails.add(tfEmail);
		pnlWineDetails.add(tfManufactureAddress);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// fetches primary form data - orders , and refreshes controls.
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void fetchAndRefresh() {
		manufacturesArray = ManufactureLogic.getInstance().getManufactures();
		currentManufacture = (!manufacturesArray.isEmpty()) ? 1 : null;
		inAddMode = (manufacturesArray == null);
		refreshControls();
	}

    private void refreshControls() {
		refreshNavigation();
		refreshManufactureFields();
	}

	private void refreshNavigation() {
		tfNavigation.setText((!inAddMode) ?
				"" + currentManufacture + " of " + manufacturesArray.size() :
				"" + (manufacturesArray.size() + 1) + " of " + (manufacturesArray.size() + 1));

		btnFirst.setEnabled(currentManufacture != null && currentManufacture > 1);
		btnPrev.setEnabled(currentManufacture != null && currentManufacture > 1);
		btnNext.setEnabled(currentManufacture != null && currentManufacture < manufacturesArray.size());
		btnLast.setEnabled(currentManufacture != null && currentManufacture < manufacturesArray.size());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// updates the empArray controls with a given employee's information.
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void refreshManufactureFields() {
		Manufacture manufacture = (!inAddMode) ? manufacturesArray.get(currentManufacture - 1) : null;
		tfManufactureNumber.setText(manufacture.manufactureNumber);
		tfName.setText(manufacture.getFullName());
		tfPhoneNumber.setText(String.valueOf(manufacture.getPhoneNumber()));
		tfEmail.setText(manufacture.getEmail());
		tfManufactureAddress.setText(manufacture.getAddressManifacture());
		panel.refreshComp((manufacture != null) ? (manufacture.getManifactureNumber()) : "0");
	}

	//	    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//		// Updates the view to present previous order
	//
	//		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void btnPrevOnClick(ActionEvent evt) {
		inAddMode = false;
		currentManufacture--;
		refreshControls();
	}

	private void btnAddOnClick() {
		inAddMode = true;
		tfManufactureNumber.setText("");
		tfName.setText("");
		tfPhoneNumber.setText("");
		tfEmail.setText("");
		tfManufactureAddress.setText("");

		btnSave.setEnabled(true);
		btnDelete.setEnabled(false);
	}

	private void btnSaveOnClick() {
		Manufacture newManufacture = new Manufacture(
				tfManufactureNumber.getText(),
				tfName.getText(),
				Integer.parseInt(tfPhoneNumber.getText()),
				tfManufactureAddress.getText(),
				tfEmail.getText()
		);

		if (inAddMode) {
			ManufactureLogic.getInstance().addManufacture(newManufacture);
		} else {
			ManufactureLogic.getInstance().editManufacture(newManufacture);
		}

		fetchAndRefresh();
	}

	private void btnDeleteOnClick() {
		ManufactureLogic.getInstance().removeManufacture(tfManufactureNumber.getText());
		fetchAndRefresh();
	}

	//		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//		// Updates the view to present next order
	//		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void btnNextOnClick(ActionEvent evt) {
		currentManufacture++;
		refreshControls();
	}

	//		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//		// Updates the view to present last order on list
	//		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void btnLastOnClick(ActionEvent evt) {
		currentManufacture = manufacturesArray.size();
		refreshControls();
	}

	// Updates the view to present first order on list
	private void btnFirstOnClick(ActionEvent evt) {
		inAddMode = false;
		currentManufacture = 1;
		refreshControls();
	}

	private JPanel pnlWineDetails = new JPanel();
	private JPanel contentPane;


	private JTextField tfManufactureNumber;
	private JTextField tfName;
	private JTextField tfPhoneNumber;
	private JTextField tfEmail;
	private JTextField tfNavigation = new JTextField();
	private JTextField tfManufactureAddress;

	private JLabel lblEmployeeID = new JLabel("Employee:");
	private JLabel lblCustomer = new JLabel("Customer:");
	private JLabel lblRequiredDate = new JLabel("Required Date:");
	private JLabel lblManufactureNumber = new JLabel("Manufacture #:");
	private JLabel lblName = new JLabel("Name:");
	private JLabel lblPhoneNumber = new JLabel("Phone number:");
	private JLabel lblEmail = new JLabel("Email:");
	private JLabel lblAddress = new JLabel("Address:");

	private JButton btnFirst = new JButton("|<");
	private JButton btnPrev = new JButton("<<");

	private JButton btnNext = new JButton(">>");
	private JButton btnLast = new JButton(">|");

	private JButton btnAdd = new JButton("Add");
	private JButton btnSave = new JButton("Save");
	private JButton btnDelete = new JButton("Delete");

	private JPanel pnlActionBtn;
	private JTextField tfShipViaID;
	private JTextField tfShipAddress= new JTextField();
	private JTextField tfShipCity;
	private JTextField tfCountry;

	private DefaultTableModel tableModel;
	FrmManufactureDetailsInternal panel;
}
