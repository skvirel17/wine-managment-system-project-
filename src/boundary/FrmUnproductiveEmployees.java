package boundary;

import control.EmployeeLogic;
import entity.UnproductiveEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FrmUnproductiveEmployees extends RootLayout {

    private ArrayList<UnproductiveEmployee> employeesArray;
    private Integer currentEmployee;
    private boolean inAddMode;

    private JPanel pnlEmployeeDetails;
    private JPanel contentPane;
    private JTextField tfEmployeeId, tfEmployeeName, tfTotalOrders, tfNavigation;
    private JButton btnFirst, btnPrev, btnNext, btnLast, btnRefresh;
    private JPanel pnlActionBtn;
    private JTextField tfStartDate, tfEndDate;
    private JButton btnFetch;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmUnproductiveEmployees frame = new FrmUnproductiveEmployees();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmUnproductiveEmployees() {
        initComponents();
        createEvents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblStartDate = new JLabel("Start Date (YYYY-MM-DD):");
        lblStartDate.setBounds(20, 20, 200, 25);
        contentPane.add(lblStartDate);

        tfStartDate = new JTextField();
        tfStartDate.setBounds(220, 20, 120, 25);
        contentPane.add(tfStartDate);

        JLabel lblEndDate = new JLabel("End Date (YYYY-MM-DD):");
        lblEndDate.setBounds(20, 60, 200, 25);
        contentPane.add(lblEndDate);

        tfEndDate = new JTextField();
        tfEndDate.setBounds(220, 60, 120, 25);
        contentPane.add(tfEndDate);

        btnFetch = new JButton("Fetch Employees");
        btnFetch.setBounds(360, 40, 150, 30);
        contentPane.add(btnFetch);

        JLabel lblEmployeeId = new JLabel("Employee ID:");
        lblEmployeeId.setBounds(20, 100, 120, 25);
        contentPane.add(lblEmployeeId);

        tfEmployeeId = new JTextField();
        tfEmployeeId.setBounds(150, 100, 200, 25);
        tfEmployeeId.setEditable(false);
        contentPane.add(tfEmployeeId);

        JLabel lblEmployeeName = new JLabel("Employee Name:");
        lblEmployeeName.setBounds(20, 140, 120, 25);
        contentPane.add(lblEmployeeName);

        tfEmployeeName = new JTextField();
        tfEmployeeName.setBounds(150, 140, 200, 25);
        tfEmployeeName.setEditable(false);
        contentPane.add(tfEmployeeName);

        JLabel lblTotalOrders = new JLabel("Total Orders:");
        lblTotalOrders.setBounds(20, 180, 120, 25);
        contentPane.add(lblTotalOrders);

        tfTotalOrders = new JTextField();
        tfTotalOrders.setBounds(150, 180, 200, 25);
        tfTotalOrders.setEditable(false);
        contentPane.add(tfTotalOrders);

        tfNavigation = new JTextField();
        tfNavigation.setEditable(false);
        tfNavigation.setBounds(20, 220, 330, 25);
        contentPane.add(tfNavigation);

        pnlActionBtn = new JPanel();
        pnlActionBtn.setBounds(20, 260, 450, 40);
        pnlActionBtn.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnFirst = new JButton("First");
        btnPrev = new JButton("Prev");
        btnNext = new JButton("Next");
        btnLast = new JButton("Last");
        btnRefresh = new JButton("Refresh");

        pnlActionBtn.add(btnFirst);
        pnlActionBtn.add(btnPrev);
        pnlActionBtn.add(btnNext);
        pnlActionBtn.add(btnLast);
        pnlActionBtn.add(btnRefresh);
        contentPane.add(pnlActionBtn);
    }
    private void btnFirstOnClick() {
        currentEmployee = 1;
        refreshControls();
    }


    private void btnLastOnClick() {
        currentEmployee = employeesArray.size();
        refreshControls();
    }


    private void btnPrevOnClick() {
        if (currentEmployee > 1) {
            currentEmployee--;
            refreshControls();
        }
    }


    private void btnNextOnClick() {
        if (currentEmployee < employeesArray.size()) {
            currentEmployee++;
            refreshControls();
        }
    }



    private void createEvents() {
        btnFirst.addActionListener(e -> btnFirstOnClick());
        btnLast.addActionListener(e -> btnLastOnClick());
        btnPrev.addActionListener(e -> btnPrevOnClick());
        btnNext.addActionListener(e -> btnNextOnClick());
        btnRefresh.addActionListener(e -> fetchAndRefresh());
        btnRefresh.addActionListener(e -> fetchAndRefresh());

    }

    private void fetchAndRefresh() {
        String startDate = tfStartDate.getText(); // קבלת תאריך התחלה מהמשתמש
        String endDate = tfEndDate.getText(); // קבלת תאריך סיום מהמשתמש

        employeesArray = EmployeeLogic.getInstance().getUnproductiveEmployeesByDate(startDate, endDate);
        currentEmployee = (!employeesArray.isEmpty()) ? 1 : null;
        refreshControls();
    }

    private void refreshControls() {
        refreshNavigation();
        refreshEmployeeFields();
    }
    private void refreshNavigation() {
        tfNavigation.setText((currentEmployee != null) ?
                currentEmployee + " of " + employeesArray.size() : "No records available");
    }


    private void refreshEmployeeFields() {
        if (employeesArray.isEmpty() || currentEmployee == null || currentEmployee <= 0) {
            tfEmployeeId.setText("");
            tfEmployeeName.setText("");
            tfTotalOrders.setText("");
            return;
        }
    }

}
