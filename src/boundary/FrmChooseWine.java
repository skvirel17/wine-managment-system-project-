package boundary;

import control.ManufactureLogic;
import entity.Manufacture;
import enums.OccasionE;
import enums.WineTypeE;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FrmChooseWine extends RootLayout {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    boundary.FrmChooseWine frame = new boundary.FrmChooseWine();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmChooseWine() {
        initComponents();
        fetchAndRefresh();
        createEvents();
    }

    private void createEvents() {
    }

    private void fetchAndRefresh() {
    }

    private void initComponents() {
        setTitle("Choose Wine Preferences");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLayout(new BorderLayout());

        // Панель для размещения групп чекбоксов
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        // Создание групп чекбоксов
        mainPanel.add(createCheckBoxPanel("Food Pairings", Arrays.asList("Cheese", "Meat", "Seafood", "Vegetarian").toArray(new String[0])));
        mainPanel.add(createCheckBoxPanel("Occasion", new String[]{"Birthday", "Anniversary", "Dinner Party", "Casual"}));
        mainPanel.add(createCheckBoxPanel("Wine Type", Arrays.asList("Red", "White", "Rosé", "Sparkling").toArray(new String[0])));


        // Добавление панели в окно
        add(mainPanel, BorderLayout.CENTER);

        // Кнопка подтверждения
        JButton btnSubmit = new JButton("Submit");
        add(btnSubmit, BorderLayout.SOUTH);

        // Отображение окна
        setVisible(true);

    }

    private JPanel createCheckBoxPanel(String title, String[] options) {
        // Панель для группы чекбоксов
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        // Создание и добавление чекбоксов
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            panel.add(checkBox);
        }

        return panel;
    }
}
