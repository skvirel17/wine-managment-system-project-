package boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.ManufactureLogic;
import control.WineLogic;
import entity.Manufacture;
import entity.Wine;
import enums.SweetnessLevel;
import enums.WineTypeE;


import javax.swing.table.DefaultTableModel;


public class FrmWine extends RootLayout {

    private ArrayList<Wine> wineArrayList;
    private Integer currentWine;
    private boolean inAddMode;

    private JPanel contentPane;
    private JPanel pnlActionBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JTextField tfWineCatalogNumber, tfName, tfWineDescription, tfWineProductYear, tfWinePricePerBottle;
    private JTextField tfWineType;
    private JTextField tfWineSweetnessLevel;
    private JTextField tfManufactureNumber;
    private JTextField tfNavigation;
    private JComboBox<SweetnessLevel> cbSweetnessLevel;
    private JComboBox<WineTypeE> cbWineType;

    private JButton btnFirst, btnPrev, btnNext, btnLast, btnAdd, btnSave, btnDelete;

    private JLabel lbWineCatalogNumber = new JLabel("Catalog number:");
    private JLabel lblName = new JLabel("Name:");
    private JLabel lblWineDescription = new JLabel("Wine description:");
    private JLabel lblWineProductYear = new JLabel("Wine year:");
    private JLabel lblWinePricePerBottle = new JLabel("Wine price per bottle:");
    private JLabel lblWineType = new JLabel("WineType:");
    private JLabel lblWineSweetnessLevel = new JLabel("Wine sweetness level:");
    private JLabel lblManufactureNumber = new JLabel("Manufacture #:");


    private JTextField tfShipViaID;
    private JTextField tfShipAddress= new JTextField();
    private JTextField tfShipCity;
    private JTextField tfCountry;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmWine frame = new FrmWine();
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
    public FrmWine() {
        setTitle("Wine Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        initComponents();
        fetchAndRefresh();
        createEvents();
    }

    private void initComponents() {

        // Панель для остальных компонентов (под таблицей)
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        contentPane.add(pnlMain, BorderLayout.CENTER);

        JPanel pnlWineDetails = new JPanel();
        pnlWineDetails.setLayout(new GridLayout(8, 2, 10, 10));
        pnlMain.add(pnlWineDetails);


        JLabel lblCatalogNumber = new JLabel("Catalog Number:");
        tfWineCatalogNumber = new JTextField();
        JLabel lblManufactureNumber = new JLabel("Manufacture Number:");
        tfManufactureNumber = new JTextField();
        tfManufactureNumber.setEditable(false);
        JLabel lblName = new JLabel("Name:");
        tfName = new JTextField();
        JLabel lblDescription = new JLabel("Description:");
        tfWineDescription = new JTextField();
        JLabel lblYear = new JLabel("Production Year:");
        tfWineProductYear = new JTextField();
        JLabel lblPrice = new JLabel("Price per Bottle:");
        tfWinePricePerBottle = new JTextField();

        JLabel lblType = new JLabel("Wine Type:");
        WineTypeE[] wineType = WineTypeE.values();
        cbWineType = new JComboBox<>(wineType);
        cbWineType.setBounds(120, 50, 150, 25);

        JLabel lblSweetness = new JLabel("Sweetness Level:");
        SweetnessLevel[] sweetnessLevels = SweetnessLevel.values();
        cbSweetnessLevel = new JComboBox<>(sweetnessLevels);
        cbSweetnessLevel.setBounds(120, 50, 150, 25);


        pnlWineDetails.add(lblCatalogNumber);
        pnlWineDetails.add(tfWineCatalogNumber);
        pnlWineDetails.add(lblManufactureNumber);
        pnlWineDetails.add(tfManufactureNumber);
        pnlWineDetails.add(lblName);
        pnlWineDetails.add(tfName);
        pnlWineDetails.add(lblDescription);
        pnlWineDetails.add(tfWineDescription);
        pnlWineDetails.add(lblYear);
        pnlWineDetails.add(tfWineProductYear);
        pnlWineDetails.add(lblPrice);
        pnlWineDetails.add(tfWinePricePerBottle);
        pnlWineDetails.add(lblType);
        pnlWineDetails.add(cbWineType);
        pnlWineDetails.add(lblSweetness);
        pnlWineDetails.add(cbSweetnessLevel);

        pnlActionBtn = new JPanel();
        pnlMain.add(pnlActionBtn);
        pnlActionBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        btnFirst = new JButton("|<");
        btnPrev = new JButton("<<");
        tfNavigation = new JTextField("showing X of Y", 6);
        btnNext = new JButton(">>");
        btnLast = new JButton(">|");
        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(120, 30));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmAddWine addWineWindow = new FrmAddWine();
                addWineWindow.setVisible(true);
                addWineWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        fetchAndRefresh(); // После закрытия окна обновляем данные
                    }
                });
            }
        });
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);

        pnlActionBtn.add(btnFirst);
        pnlActionBtn.add(btnPrev);
        pnlActionBtn.add(tfNavigation);
        pnlActionBtn.add(btnNext);
        pnlActionBtn.add(btnLast);
        pnlActionBtn.add(btnAdd);
        pnlActionBtn.add(btnSave);
        pnlActionBtn.add(btnDelete);


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

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDeleteOnClick();
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
        lblWineDescription.setBounds(10, 66, 120, 14);
        pnlWineDetails.add(lblWineDescription);
        lblWineProductYear.setBounds(10, 90, 120, 14);
        pnlWineDetails.add(lblWineProductYear);
        lblWinePricePerBottle.setBounds(10, 115, 120, 14);
        pnlWineDetails.add(lblWinePricePerBottle);
        lblWineSweetnessLevel.setBounds(10, 140, 120, 14);
        pnlWineDetails.add(lblWineSweetnessLevel);
        lblWineType.setBounds(10, 165, 120, 14);
        pnlWineDetails.add(lblWineType);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // fetches primary form data - orders , and refreshes controls.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void fetchAndRefresh() {
        wineArrayList = (ArrayList<Wine>) WineLogic.getInstance().getWines();
        currentWine = (!wineArrayList.isEmpty()) ? 1 : null;
        inAddMode = (wineArrayList == null);
        refreshControls();
    }

    private void refreshControls() {
        refreshNavigation();
        refreshWineFields();
    }

    private void refreshNavigation() {
        tfNavigation.setText((!inAddMode) ?
                "" + currentWine + " of " + wineArrayList.size() :
                "" + (wineArrayList.size() + 1) + " of " + (wineArrayList.size() + 1));

        btnFirst.setEnabled(currentWine != null && currentWine > 1);
        btnPrev.setEnabled(currentWine != null && currentWine > 1);
        btnNext.setEnabled(currentWine != null && currentWine < wineArrayList.size());
        btnLast.setEnabled(currentWine != null && currentWine < wineArrayList.size());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // updates the empArray controls with a given employee's information.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void refreshWineFields() {
        Wine wine = (!inAddMode) ? wineArrayList.get(currentWine - 1) : null;
        tfManufactureNumber.setText(wine.manufactureNumber);
        tfName.setText(wine.getName());
        tfWineDescription.setText(String.valueOf(wine.getDescription()));
        tfWineProductYear.setText(String.valueOf(wine.getProductionYear()));
        cbWineType.setSelectedItem(wine.getWineType());
        tfWinePricePerBottle.setText(String.valueOf(wine.getPricePerBottle()));
        //tfWineSweetnessLevel.setText(String.valueOf(wine.getSweetnessLevel()));
        tfWineCatalogNumber.setText(wine.getCatalogNumber());
        cbSweetnessLevel.setSelectedItem(wine.getSweetnessLevel());
    }

    //	    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //		// Updates the view to present previous order
    //		 //////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void btnPrevOnClick(ActionEvent evt) {
        inAddMode = false;
        currentWine--;
        refreshControls();
    }

    private void btnAddOnClick() {
        refreshControls();
    }

    private void btnSaveOnClick() {
        refreshControls();
    }

    private void btnDeleteOnClick() {
        if (wineArrayList.isEmpty() || currentWine == null) {
            JOptionPane.showMessageDialog(this, "No wine to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this wine?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Удаление текущего вина

            WineLogic.removeWine(wineArrayList.get(currentWine - 1).getCatalogNumber());
            wineArrayList.remove(currentWine - 1);

            // Обновление currentWine: если удалили последний элемент, уменьшаем индекс
            if (wineArrayList.isEmpty()) {
                currentWine = null;
                inAddMode = true;
            } else if (currentWine > wineArrayList.size()) {
                currentWine = wineArrayList.size();
            }

            // Обновление UI
            refreshControls();
        }
    }


    private void btnNextOnClick(ActionEvent evt) {
        currentWine++;
        refreshControls();
    }

    private void btnLastOnClick(ActionEvent evt) {
        currentWine = wineArrayList.size();
        refreshControls();
    }

    private void btnFirstOnClick(ActionEvent evt) {
        inAddMode = false;
        currentWine = 1;
        refreshControls();
    }

    private JPanel pnlWineDetails = new JPanel();



//    private JLabel lblEmployeeID = new JLabel("Employee:");
//    private JLabel lblCustomer = new JLabel("Customer:");
//    private JLabel lblRequiredDate = new JLabel("Required Date:");
//    private JLabel lblManufactureNumber = new JLabel("Manufacture #:");
//    private JLabel lblName = new JLabel("Name:");
//    private JLabel lblPhoneNumber = new JLabel("Phone number:");
//    private JLabel lblEmail = new JLabel("Email:");
//    private JLabel lblAddress = new JLabel("Address:");

}
