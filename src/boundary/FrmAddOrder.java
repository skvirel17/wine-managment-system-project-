package boundary;

import control.OrderLogic;
import entity.Order;
import enums.OrderStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmAddOrder extends JFrame {
    private JTextField tfOrderNumber, tfOrderDate, tfShipmentDate, tfEmployeeId, tfTotalPrice, tfPriorityLevel;
    private JComboBox<OrderStatus> cbOrderStatus;
    private JButton btnSave, btnCancel;

    public FrmAddOrder() {
        setTitle("Add New Order");
        setSize(400, 350);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Order Number:", "Order Date (yyyy-MM-dd):", "Shipment Date (yyyy-MM-dd):", "Employee ID:", "Total Price:", "Priority Level:", "Order Status:"};
        JComponent[] fields = {
                tfOrderNumber = new JTextField(15),
                tfOrderDate = new JTextField(15),
                tfShipmentDate = new JTextField(15),
                tfEmployeeId = new JTextField(15),
                tfTotalPrice = new JTextField(15),
                tfPriorityLevel = new JTextField(15),
                cbOrderStatus = new JComboBox<>(OrderStatus.values())
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrder();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть окно
            }
        });
    }

    private void saveOrder() {
        try {
            int orderNumber = Integer.parseInt(tfOrderNumber.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = dateFormat.parse(tfOrderDate.getText().trim());
            Date shipmentDate = dateFormat.parse(tfShipmentDate.getText().trim());
            int priorityLevel = Integer.parseInt(tfPriorityLevel.getText().trim());
            OrderStatus orderStatus = (OrderStatus) cbOrderStatus.getSelectedItem();
            int employeeId = Integer.parseInt(tfEmployeeId.getText().trim());
            double totalPrice = Double.parseDouble(tfTotalPrice.getText().trim());

            Order newOrder = new Order(priorityLevel, orderNumber, orderDate, orderStatus, shipmentDate, String.valueOf(employeeId), totalPrice);
            boolean success = OrderLogic.getInstance().addOrder(newOrder);

            if (success) {
                JOptionPane.showMessageDialog(this, "Order added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding order!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
