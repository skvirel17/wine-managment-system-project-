package entity;

import enums.OrderStatus;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer priorityLevel;
    private int orderNumber;
    private Date orderDate;
    private OrderStatus orderStatus;
    private Date shipmentDate;
    private String employeeId;
    private double totalPrice;

    public Order(Integer priorityLevel, int orderNumber, Date orderDate, OrderStatus orderStatus, Date shipmentDate, String employeeId, double totalPrice) {
        this.priorityLevel = priorityLevel;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
        this.totalPrice = totalPrice;
    }

    public Order(int orderNumber, Date orderDate, OrderStatus orderStatus, Date shipmentDate, String employeeId) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
    }

    public Order(int orderNumber, Date orderDate, OrderStatus orderStatus, Date shipmentDate,
                 String employeeId, double totalPrice) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
        this.totalPrice = totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;  // ✅ Теперь возвращает правильное значение
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
