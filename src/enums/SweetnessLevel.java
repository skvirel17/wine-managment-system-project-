package enums;

public enum SweetnessLevel {
    DRY("1", "Dry"),
    SEMIDRY("2", "SemiDry"),
    SEMISWEET("3", "SemiSweet"),
    SWEET("4", "Sweet");

    private String id;
    private String enumValue;

    SweetnessLevel( String id, String enumValue) {
        this.enumValue = enumValue;
        this.id = id;
    }

    public static SweetnessLevel fromValue(String text) {
        for (SweetnessLevel sweetnessLevel : SweetnessLevel.values()) {
            if (String.valueOf(sweetnessLevel.enumValue).equals(text)) {
                return sweetnessLevel;
            }
        }
        return null;
    }

    public static SweetnessLevel fromId(String id) {
        for (SweetnessLevel sweetnessLevel : SweetnessLevel.values()) {
            if (String.valueOf(sweetnessLevel.id).equals(id)) {
                return sweetnessLevel;
            }
        }
        return null;
    }

    public String getId(){
        return this.id;
    }

}
