package enums;

public enum OrderStatus {
    INPROCESS(1, "InProcess"),
    DISPATCHED(2, "Dispatched"),
    DELIVERED(3, "Delivered"),
    PAID(4, "Paid"),
    SUSPENDED(5, "Suspended"),
    CANCELED(6, "Canceled");

    private final int id;
    private final String enumValue;

    OrderStatus(int id, String enumValue) {
        this.id = id;
        this.enumValue = enumValue;
    }

    public int getId() {
        return id;
    }

    public static OrderStatus fromValue(String text) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.enumValue.equals(text)) {
                return orderStatus;
            }
        }
        return null;
    }

    public static OrderStatus fromId(int id) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.id == id) {
                return orderStatus;
            }
        }
        return null;
    }
}

