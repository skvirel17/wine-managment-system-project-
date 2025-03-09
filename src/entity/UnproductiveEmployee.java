package entity;

public class UnproductiveEmployee {
    private String employeeId;
    private String name;
    private int totalOrders;

    public UnproductiveEmployee(String employeeId, String name, int totalOrders) {
        this.employeeId = employeeId;
        this.name = name;
        this.totalOrders = totalOrders;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Override
    public String toString() {
        return "UnproductiveEmployee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", totalOrders=" + totalOrders +
                '}';
    }
}
