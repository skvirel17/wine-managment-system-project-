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
import javax.swing.table.DefaultTableModel;

import control.StorageLogic;
import entity.StorageLocation;
import entity.WineStorageLocation;

//import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class FrmWineLocation extends RootLayout {

    private ArrayList<WineStorageLocation> storageArray;
    private Integer currentStorage;
    private boolean inAddMode;

    private JPanel pnlStorageDetails;
    private JPanel contentPane;
    private JTextField tfStorageNumber, tfStorageName, tfCatalogNumber, tfManufactureNumber, tfBottleCount, tfNavigation;
    private JButton btnFirst, btnPrev, btnNext, btnLast, btnAdd, btnSave, btnDelete;
    private JPanel pnlActionBtn;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmWineLocation frame = new FrmWineLocation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmWineLocation() {
        initComponents();
        fetchAndRefresh();
        createEvents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        String[] labels = {"Storage Number:", "Storage Name:", "Catalog Wine Number:", "Manufacture Number:", "Bottle Count:"};
        JTextField[] fields = new JTextField[labels.length];

        int labelX = 20, fieldX = 150, y = 20, width = 120, height = 25;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(labelX, y, width, height);
            contentPane.add(label);

            fields[i] = new JTextField();
            fields[i].setBounds(fieldX, y, width, height);
            contentPane.add(fields[i]);

            y += 40;
        }

        tfStorageNumber = fields[0];
        tfStorageName = fields[1];
        tfCatalogNumber = fields[2];
        tfManufactureNumber = fields[3];
        tfBottleCount = fields[4];


        tfNavigation = new JTextField();
        tfNavigation.setEditable(false);
        tfNavigation.setBounds(labelX, y, fieldX + width - labelX, height);
        contentPane.add(tfNavigation);

        y += 40;

        btnFirst = new JButton("First");
        btnPrev = new JButton("Prev");
        btnNext = new JButton("Next");
        btnLast = new JButton("Last");
        btnAdd = new JButton("Add");
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(50, y, 400, 40);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(btnFirst);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnLast);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);

        contentPane.add(buttonPanel);
    }


    private void createEvents() {
        btnFirst.addActionListener(e -> btnFirstOnClick());
        btnLast.addActionListener(e -> btnLastOnClick());
        btnPrev.addActionListener(e -> btnPrevOnClick());
        btnNext.addActionListener(e -> btnNextOnClick());
        btnAdd.addActionListener(e -> btnAddOnClick());
        btnSave.addActionListener(e -> btnSaveOnClick());
        btnDelete.addActionListener(e -> btnDeleteOnClick());
    }

    private void btnFirstOnClick() {
        currentStorage = 1;
        refreshControls();
    }

    private void btnLastOnClick() {
        currentStorage = storageArray.size();
        refreshControls();
    }

    private void btnPrevOnClick() {
        if (currentStorage > 1) {
            currentStorage--;
            refreshControls();
        }
    }

    private void btnNextOnClick() {
        if (currentStorage < storageArray.size()) {
            currentStorage++;
            refreshControls();
        }
    }

    private void btnAddOnClick() {
        inAddMode = true;
        tfStorageNumber.setText("");
        tfStorageName.setText("");
        tfCatalogNumber.setText("");
        tfManufactureNumber.setText("");
        tfBottleCount.setText("");
        btnSave.setEnabled(true);
        btnDelete.setEnabled(false);
    }

    private void btnSaveOnClick() {
        WineStorageLocation newStorage = new WineStorageLocation(
                tfStorageNumber.getText(),
                tfStorageName.getText(),
                tfCatalogNumber.getText(),
                tfManufactureNumber.getText(),
                Integer.parseInt(tfBottleCount.getText())
        );

        if (inAddMode) {
            StorageLogic.getInstance().addStorage(newStorage);
        } else {
            StorageLogic.getInstance().editStorage(newStorage);
        }
        fetchAndRefresh();
    }

    private void btnDeleteOnClick() {
        StorageLogic.getInstance().removeStorage(tfStorageNumber.getText());
        fetchAndRefresh();
    }

    private void fetchAndRefresh() {
        storageArray = StorageLogic.getInstance().getStorageLocations();
        currentStorage = (!storageArray.isEmpty()) ? 1 : null;
        inAddMode = (storageArray == null);
        refreshControls();
    }

    private void refreshControls() {
        refreshNavigation();
        refreshStorageFields();
    }

    private void refreshNavigation() {
        tfNavigation.setText((!inAddMode) ?
                "" + currentStorage + " of " + storageArray.size() :
                "" + (storageArray.size() + 1) + " of " + (storageArray.size() + 1));
    }

    private void refreshStorageFields() {
        if (storageArray.isEmpty() || currentStorage == null || currentStorage <= 0){
            tfStorageNumber.setText("");
            tfStorageName.setText("");
            tfCatalogNumber.setText("");
            tfManufactureNumber.setText("");
            tfBottleCount.setText("");
            return;
        }
        WineStorageLocation wineStorageLocation = storageArray.get(currentStorage-1);


        tfStorageNumber.setText(wineStorageLocation.getStorageNumber() != null ? wineStorageLocation.getStorageNumber() : "");
        tfStorageName.setText(wineStorageLocation.getStorageName() != null ? wineStorageLocation.getStorageName() : "");
        tfCatalogNumber.setText(wineStorageLocation.getCatalogNumber() != null ? wineStorageLocation.getCatalogNumber() : "");
        tfManufactureNumber.setText(wineStorageLocation.getManufactureNumber() != null ? wineStorageLocation.getManufactureNumber() : "");
        tfBottleCount.setText(wineStorageLocation.getBottleCount() != null ? String.valueOf(wineStorageLocation.getBottleCount()) : "");
    }
    }

