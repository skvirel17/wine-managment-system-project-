package enums;

public enum OccasionE {
    MARRIAGE("Marriage"),
    HIKE("Hike"),
    CHEESY_NIGHT("Cheesy Night"),
    FESTIVAL("Festival"),
    BIRTHDAY("Birthday"),
    BRUNCH("Brunch"),
    PICNIC("Picnic"),
    SUMMIT("Summit"),
    RETRIT("Retrit"),
    ANNIVERSARY("Anniversary");
    private String enumValue;

    OccasionE(String enumValue) {
        this.enumValue = enumValue;
    }

    public static OccasionE fromValue(String text) {
        for (OccasionE occasionE : OccasionE.values()) {
            if (String.valueOf(occasionE.enumValue).equals(text)) {
                return occasionE;
            }
        }
        return null;
    }
}