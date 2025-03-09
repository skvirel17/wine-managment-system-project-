package boundary;

import control.WineLogic;
import entity.Wine;
import enums.SweetnessLevel;
import enums.WineTypeE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddWine extends JFrame {
    private JTextField tfWineCatalogNumber, tfManufactureNumber, tfName, tfWineDescription, tfWineProductYear, tfWinePricePerBottle;
    private JComboBox<SweetnessLevel> cbSweetnessLevel;
    private JComboBox<WineTypeE> cbWineType;
    private JButton btnSave, btnCancel;

    public FrmAddWine() {
        setTitle("Add New Wine");
        setSize(400, 350);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Catalog Number:", "Manufacture Number:", "Name:", "Description:", "Production Year:", "Price per Bottle:", "Wine Type:", "Sweetness Level:"};
        JComponent[] fields = {
                tfWineCatalogNumber = new JTextField(15),
                tfManufactureNumber = new JTextField(15),
                tfName = new JTextField(15),
                tfWineDescription = new JTextField(15),
                tfWineProductYear = new JTextField(15),
                tfWinePricePerBottle = new JTextField(15),
                cbWineType = new JComboBox<>(WineTypeE.values()),
                cbSweetnessLevel = new JComboBox<>(SweetnessLevel.values())
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
                saveWine();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть окно
            }
        });
    }

    private void saveWine() {
        try {
            String catalogNumber = tfWineCatalogNumber.getText().trim();
            String manufactureNumber = tfManufactureNumber.getText().trim();
            String name = tfName.getText().trim();
            String description = tfWineDescription.getText().trim();
            int productionYear = Integer.parseInt(tfWineProductYear.getText().trim());
            float pricePerBottle = Float.parseFloat(tfWinePricePerBottle.getText().trim());
            WineTypeE wineType = (WineTypeE) cbWineType.getSelectedItem();
            SweetnessLevel sweetnessLevel = (SweetnessLevel) cbSweetnessLevel.getSelectedItem();

            Wine newWine = new Wine(catalogNumber, manufactureNumber, name, description, productionYear, pricePerBottle,  sweetnessLevel, null, wineType);
            boolean success = WineLogic.getInstance().addWine(newWine);

            if (success) {
                JOptionPane.showMessageDialog(this, "Wine added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding wine!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
