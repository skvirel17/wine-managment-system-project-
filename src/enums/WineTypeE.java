package enums;

public enum WineTypeE {
    RED("544332", "red"),
    WHITE("676676", "white"),
    SPARKLING("898765", "sparkling"),
    FORTIFIED("345456", "fortified"),
    ROSE("987888", "rose"),
    DESSERT("900999", "desert");

    private String id;
    private String enumValue;

    WineTypeE( String id, String enumValue) {
        this.enumValue = enumValue;
        this.id = id;
    }

    public static WineTypeE fromValue(String text) {
        for (WineTypeE wineTypeE : WineTypeE.values()) {
            if (String.valueOf(wineTypeE.enumValue).equals(text)) {
                return wineTypeE;
            }
        }
        return null;
    }

    public static WineTypeE fromId(String id) {
        for (WineTypeE wineTypeE : WineTypeE.values()) {
            if (String.valueOf(wineTypeE.id).equals(id)) {
                return wineTypeE;
            }
        }
        return null;
    }
}
