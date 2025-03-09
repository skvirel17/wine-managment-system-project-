package boundary;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.OrderLogic;
import entity.Order;
import enums.OrderStatus;

import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class FrmOrder extends RootLayout {

    private ArrayList<Order> ordersArray;
    private Integer currentOrder;
    private boolean inAddMode;

    private JPanel pnlOrderDetails;
    private JPanel contentPane;
    private JTextField tfOrderNumber;
    private JTextField tfOrderDate;
    private JTextField tfShipmentDate;
    private JTextField tfEmployeeId;
    private JTextField tfTotalPrice;
    private JTextField tfNavigation;
    private JTextField tfOrderStatus;
    private JButton btnFirst, btnPrev, btnNext, btnLast, btnAdd, btnSave, btnDelete;
    private JPanel pnlActionBtn;
    private DefaultTableModel tableModel;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmOrder frame = new FrmOrder();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmOrder() {
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

        String[] labels = {"Order Number:", "Order Date:", "Shipment Date:", "Employee ID:", "Total Price:", "Orders status:"};
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

        tfOrderNumber = fields[0];
        tfOrderDate = fields[1];
        tfShipmentDate = fields[2];
        tfEmployeeId = fields[3];
        tfTotalPrice = fields[4];
        tfOrderStatus = fields[5];

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
        currentOrder = 1;
        refreshControls();
    }

    private void btnLastOnClick() {
        currentOrder = ordersArray.size();
        refreshControls();
    }

    private void btnPrevOnClick() {
        if (currentOrder > 1) {
            currentOrder--;
            refreshControls();
        }
    }

    private void btnNextOnClick() {
        if (currentOrder < ordersArray.size()) {
            currentOrder++;
            refreshControls();
        }
    }

    private void btnAddOnClick() {
        inAddMode = true;
        tfOrderNumber.setText("");
        tfOrderDate.setText("");
        tfShipmentDate.setText("");
        tfEmployeeId.setText("");
        tfTotalPrice.setText("");
        tfOrderStatus.setText("");
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void btnSaveOnClick() {
        Order newOrder = new Order(
                Integer.parseInt(tfOrderNumber.getText()),
                tfOrderDate.getText(),
                new Date(),
                OrderStatus.fromValue(tfOrderStatus.getText()), // יש לשנות זאת בהתאם לערך בפועל
                new Date(),
                tfEmployeeId.getText(),
                Double.parseDouble(tfTotalPrice.getText())
        );

        if (inAddMode) {
            OrderLogic.getInstance().addOrder(newOrder);
        } else {
            OrderLogic.getInstance().editOrder(newOrder);
        }
        fetchAndRefresh();
    }

    private void btnDeleteOnClick() {
        OrderLogic.getInstance().removeOrder(tfOrderNumber.getText());
        fetchAndRefresh();
    }

    private void fetchAndRefresh() {
        ordersArray = OrderLogic.getInstance().getOrders();
        currentOrder = (!ordersArray.isEmpty()) ? 1 : null;
        inAddMode = (ordersArray == null);
        refreshControls();
    }

    private void refreshControls() {
        refreshNavigation();
        refreshOrderFields();
    }

    private void refreshNavigation() {
        tfNavigation.setText((!inAddMode) ?
                "" + currentOrder + " of " + ordersArray.size() :
                "" + (ordersArray.size() + 1) + " of " + (ordersArray.size() + 1));
    }

    private void refreshOrderFields() {
        Order order = (!inAddMode) ? ordersArray.get(currentOrder - 1) : null;
        tfOrderNumber.setText(order != null ? order.getOrderNumber() : "");
        tfOrderDate.setText(order != null ? order.getOrderDate().toString() : "");
        tfShipmentDate.setText(order != null ? order.getShipmentDate().toString() : "");
        tfEmployeeId.setText(order != null ? order.getEmployeeId() : "");
        tfTotalPrice.setText(order != null ? String.valueOf(order.getTotalPrice()) : "");
        tfOrderStatus.setText(order != null ? String.valueOf(order.getOrderStatus()) : "");
    }
}
