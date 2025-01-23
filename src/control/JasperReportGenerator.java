package control;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportGenerator {

    /**
     * Метод для создания отчёта и экспорта в PDF.
     *
     * @param jasperFilePath Путь к скомпилированному файлу .jasper
     * @param outputPdfPath  Путь для сохранения PDF-файла
     * @param dataList       Данные для отчёта (список объектов)
     * @param parameters     Параметры отчёта (если нужны)
     * @throws JRException Исключение JasperReports
     */
    public static void generatePdfReport(String jasperFilePath, String outputPdfPath,
                                         List<?> dataList, Map<String, Object> parameters) throws JRException {
        // Источник данных
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        // Заполнение отчёта
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameters, dataSource);

        // Экспорт в PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);

        System.out.println("Отчёт успешно сгенерирован: " + outputPdfPath);
    }

    public static void main(String[] args) {
        try {
            // Путь к скомпилированному шаблону отчёта (.jasper)
            String jasperFilePath = "C:\\Users\\averm\\OneDrive\\Desktop\\homework-2024-25\\tcn\\TRG3\\T\\out\\report.jasper";

            // Путь для сохранения PDF
            String outputPdfPath = "output/report.pdf";

            // Данные для отчёта
            List<MyData> dataList = List.of(
                    new MyData("Alice", "Developer", 25, 55000.0),
                    new MyData("Bob", "Designer", 30, 60000.0),
                    new MyData("Charlie", "Manager", 35, 75000.0),
                    new MyData("Diana", "Analyst", 28, 58000.0),
                    new MyData("Edward", "Tester", 32, 48000.0)
            );

            // Параметры отчёта
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Отчёт о сотрудниках");
            parameters.put("CompanyName", "Example Corp");

            // Генерация отчёта
            generatePdfReport(jasperFilePath, outputPdfPath, dataList, parameters);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Класс данных для отчёта.
 */
class MyData {
    private String name;
    private String position;
    private int age;
    private double salary;

    public MyData(String name, String position, int age, double salary) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}
