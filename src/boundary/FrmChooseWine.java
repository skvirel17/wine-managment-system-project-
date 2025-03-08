package boundary;

import control.WineLogic;
import entity.ChooseWineDTO;
import entity.Wine;
import enums.WineTypeE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FrmChooseWine extends RootLayout {
    private JPanel foodPairingsPanel;
    private JPanel occasionPanel;
    private JPanel wineTypePanel;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmChooseWine frame = new FrmChooseWine();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmChooseWine() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Choose Wine Preferences");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        setLayout(new BorderLayout());

        // Панель для чекбоксов
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Создание групп чекбоксов
        foodPairingsPanel = createCheckBoxPanel("Food Pairings", Arrays.asList("Cheese", "Meat", "Seafood",
                "Sushi", "Vegetables", "Fruits", "Fish", "Sweets", "Chicken", "Italian").toArray(new String[0]));
        occasionPanel = createCheckBoxPanel("Occasion", new String[]{"Birthday", "Anniversary",
                "Marriage", "Hike", "Cheesy Night", "Festival", "Brunch", "Picnic", "Summit", "Retreat"});
        wineTypePanel = createCheckBoxPanel("Wine Type", Arrays.asList("red", "white", "rose", "sparkling", "dessert",
                "fortified").toArray(new String[0]));

        // Добавление групп чекбоксов на панель
        mainPanel.add(foodPairingsPanel);
        mainPanel.add(occasionPanel);
        mainPanel.add(wineTypePanel);

        // Добавление основной панели
        add(mainPanel, BorderLayout.CENTER);

        // Таблица для отображения результатов
        tableModel = new DefaultTableModel(new Object[]{"WineName","Description", "Food", "Occasion", "WineType"}, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.NORTH);

        // Кнопка подтверждения
        JButton btnSubmit = new JButton("Filter");
        add(btnSubmit, BorderLayout.SOUTH);

        // Обработчик событий кнопки Submit
        btnSubmit.addActionListener(e -> handleSubmit(false)); // Используем метод, который позволяет переключаться между базой и объектами

        setVisible(true);
    }

    private JPanel createCheckBoxPanel(String title, String[] options) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            panel.add(checkBox);
        }

        return panel;
    }

    private void handleSubmit(boolean useDatabase) {
        // Получение выбранных фильтров
        List<String> foodPairings = getSelectedCheckboxes(foodPairingsPanel);
        List<String> occasions = getSelectedCheckboxes(occasionPanel);
        List<String> wineTypes = getSelectedCheckboxes(wineTypePanel);

        // Преобразование типа вина
        WineTypeE wineType = !wineTypes.isEmpty() ? WineTypeE.valueOf(wineTypes.get(0).toUpperCase()) : null;

        // Объединение ключевых слов
        List<String> keywords = new ArrayList<>();
        keywords.addAll(foodPairings);
        keywords.addAll(occasions);

        WineLogic wineLogic = WineLogic.getInstance();
        List<ChooseWineDTO> filteredWines = wineLogic.getFilteredWines(occasions, foodPairings, wineTypes);

        // Обновление таблицы результатами
        updateResultTable(filteredWines);
    }

    private List<String> getSelectedCheckboxes(JPanel panel) {
        return Arrays.stream(panel.getComponents())
                .filter(component -> component instanceof JCheckBox)
                .map(component -> (JCheckBox) component)
                .filter(JCheckBox::isSelected)
                .map(JCheckBox::getText)
                .collect(Collectors.toList());
    }

    private void updateResultTable(List<ChooseWineDTO> wines) {
        tableModel.setRowCount(0);

        // Очистка таблицы
        for (ChooseWineDTO wine : wines) {
            tableModel.addRow(new Object[]{
                    wine.getWineName(),
                    wine.getDescription(),
                    wine.getFood().getName(),
                    wine.getOccasion(),
                    wine.getWineType()
            });
        }
    }
}
