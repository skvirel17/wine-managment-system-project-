package entity;

import enums.OrderStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer priorityLevel;
    private String orderNumber;
    private Date orderDate;
    private OrderStatus orderStatus;
    private Date shipmentDate;
    private String employeeId;
    private double totalPrice;
    //private List<Wine> items;
   // public SalesEmployee orderEmployee;


    public Order(Integer priorityLevel, String orderNumber, Date orderDate, OrderStatus orderStatus, Date shipmentDate, String employeeId, double totalPrice) {
        this.priorityLevel = priorityLevel;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
        this.totalPrice = totalPrice;
    }

    public Order( String orderNumber, Date orderDate, OrderStatus orderStatus, Date shipmentDate, String employeeId) {

        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
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
