package enums;

public enum OrderStatus {
    INPROCESS("1", "InProcess"),
    DISPATCHED("2", "Dispatched"),
    DELIVERED("3", "Delivered"),
    PAID("4", "Paid"),
    SUSPENDED("5", "Suspended"),
    CANCELED("6", "Canceled");

    private String id;
    private String enumValue;

    OrderStatus( String id, String enumValue) {
        this.enumValue = enumValue;
        this.id = id;
    }

    public static OrderStatus fromValue(String text) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (String.valueOf(orderStatus.enumValue).equals(text)) {
                return orderStatus;
            }
        }
        return null;
    }

    public static OrderStatus fromId(String id) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (String.valueOf(orderStatus.id).equals(id)) {
                return orderStatus;
            }
        }
        return null;
    }

}
