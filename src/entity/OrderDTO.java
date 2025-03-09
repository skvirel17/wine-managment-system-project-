package entity;

import enums.OrderStatus;
import java.util.Date;
import java.util.Objects;

public class OrderDTO {
    private String orderNumber;
    private Date orderDate;
    private OrderStatus orderStatus;
    private Date shipmentDate;
    private String employeeId;
    private double totalPrice;

    public OrderDTO(String orderNumber, Date orderDate, OrderStatus orderStatus,
                    Date shipmentDate, String employeeId, double totalPrice) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", shipmentDate=" + shipmentDate +
                ", employeeId='" + employeeId + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Double.compare(orderDTO.totalPrice, totalPrice) == 0 &&
                Objects.equals(orderNumber, orderDTO.orderNumber) &&
                Objects.equals(orderDate, orderDTO.orderDate) &&
                orderStatus == orderDTO.orderStatus &&
                Objects.equals(shipmentDate, orderDTO.shipmentDate) &&
                Objects.equals(employeeId, orderDTO.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, orderDate, orderStatus, shipmentDate, employeeId, totalPrice);
    }
}