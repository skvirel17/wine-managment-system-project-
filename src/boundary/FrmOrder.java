package boundary;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
    private JTextField tfOrderNumber, tfOrderDate, tfShipmentDate, tfEmployeeId, tfTotalPrice, tfNavigation;
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
        btnSave.setEnabled(true);
        btnDelete.setEnabled(false);
    }

    private void btnSaveOnClick() {
        Order newOrder = new Order(
                Integer.parseInt(tfOrderNumber.getText()),
                tfOrderDate.getText(),
                new Date(),
                OrderStatus.INPROCESS, // יש לשנות זאת בהתאם לערך בפועל
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
    }
}
