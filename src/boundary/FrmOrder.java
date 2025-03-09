package boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.border.EmptyBorder;

import control.OrderLogic;
import entity.Order;
import entity.Wine;
import enums.OrderStatus;
import enums.SweetnessLevel;

import javax.swing.table.DefaultTableModel;

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
    private JComboBox<OrderStatus> cbOrderStatus;
    private JButton btnFirst, btnPrev, btnNext, btnLast, btnAddOrder, btnSave, btnDelete;
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
        setBounds(100, 100, 500, 500); // Увеличил высоту окна для нового поля
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        String[] labels = {"Order Number:", "Order Date:", "Shipment Date:", "Employee ID:", "Total Price:", "Order Status:"};
        JTextField[] fields = new JTextField[labels.length - 1]; // Убираем одно поле для выпадающего списка

        int labelX = 20, fieldX = 150, y = 20, width = 120, height = 25;

        for (int i = 0; i < labels.length - 1; i++) { // Последнее поле будет JComboBox
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

        JLabel lblOrderStatus = new JLabel("Order Status:");
        lblOrderStatus.setBounds(labelX, y, width, height);
        contentPane.add(lblOrderStatus);
        OrderStatus[] orderStatuses = OrderStatus.values();
        cbOrderStatus = new JComboBox<>(orderStatuses);
        cbOrderStatus.setBounds(fieldX, y, width, height);
        contentPane.add(cbOrderStatus);

        y += 40;

        tfNavigation = new JTextField();
        tfNavigation.setEditable(false);
        tfNavigation.setBounds(labelX, y, fieldX + width - labelX, height);
        contentPane.add(tfNavigation);

        y += 40;

        btnFirst = new JButton("First");
        btnPrev = new JButton("Prev");
        btnNext = new JButton("Next");
        btnLast = new JButton("Last");
        btnAddOrder = new JButton("Add");
        btnAddOrder.setPreferredSize(new Dimension(120, 30));
        btnAddOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmAddOrder addOrderWindow = new FrmAddOrder();
                addOrderWindow.setVisible(true);
            }
        });
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, y, 460, 100); // Увеличил высоту панели для видимости кнопки
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(btnFirst);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnLast);
        buttonPanel.add(btnAddOrder);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);

        contentPane.add(buttonPanel);
    }



    private void createEvents() {
        btnFirst.addActionListener(e -> btnFirstOnClick());
        btnLast.addActionListener(e -> btnLastOnClick());
        btnPrev.addActionListener(e -> btnPrevOnClick());
        btnNext.addActionListener(e -> btnNextOnClick());
        btnAddOrder.addActionListener(e -> btnAddOnClick());
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
        cbOrderStatus.setSelectedIndex(0);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void btnSaveOnClick() {
        try {
            int orderNumber = Integer.parseInt(tfOrderNumber.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = dateFormat.parse(tfOrderDate.getText().trim());
            Date shipmentDate = dateFormat.parse(tfShipmentDate.getText().trim());

            // Получаем статус из JComboBox
            OrderStatus orderStatus = (OrderStatus) cbOrderStatus.getSelectedItem();
            if (orderStatus == null) {
                orderStatus = OrderStatus.INPROCESS; // Значение по умолчанию
            }

            String employeeId = tfEmployeeId.getText().trim();
            double totalPrice = Double.parseDouble(tfTotalPrice.getText().trim());

            Order newOrder = new Order(orderNumber, orderDate, orderStatus, shipmentDate, employeeId, totalPrice);

            if (inAddMode) {
                OrderLogic.getInstance().addOrder(newOrder);
            } else {
                OrderLogic.getInstance().editOrder(newOrder);
            }
            fetchAndRefresh();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Use yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        if (ordersArray.isEmpty() || currentOrder == null || currentOrder <= 0) {
            tfOrderNumber.setText("");
            tfOrderDate.setText("");
            tfShipmentDate.setText("");
            tfEmployeeId.setText("");
            tfTotalPrice.setText("");
            cbOrderStatus.setSelectedIndex(-1);
            return;
        }

        Order order = ordersArray.get(currentOrder - 1);
        tfOrderNumber.setText(String.valueOf(order.getOrderNumber()));
        tfOrderDate.setText(order.getOrderDate().toString());
        tfShipmentDate.setText(order.getShipmentDate().toString());
        tfEmployeeId.setText(order.getEmployeeId());
        tfTotalPrice.setText(String.valueOf(order.getTotalPrice()));

        // Преобразуем OrderStatus в индекс для JComboBox
        cbOrderStatus.setSelectedItem(order.getOrderStatus());
    }

//    private void refreshWineFields() {
//        Wine wine = (!inAddMode) ? wineArrayList.get(currentWine - 1) : null;
//        tfManufactureNumber.setText(wine.manufactureNumber);
//        tfName.setText(wine.getName());
//        tfWineDescription.setText(String.valueOf(wine.getDescription()));
//        tfWineProductYear.setText(String.valueOf(wine.getProductionYear()));
//        cbWineType.setSelectedItem(wine.getWineType());
//        tfWinePricePerBottle.setText(String.valueOf(wine.getPricePerBottle()));
//        //tfWineSweetnessLevel.setText(String.valueOf(wine.getSweetnessLevel()));
//        tfWineCatalogNumber.setText(wine.getCatalogNumber());
//        cbSweetnessLevel.setSelectedItem(wine.getSweetnessLevel());
//    }
}
