package experiment.enums;

public enum LayerEnum {
    TITLE(1, "title"),
    PRE_CON(2, "precon"),
    MAIN_FLOW(3, "mainflow"),
    SUB_FLOW(4, "subflow"),
    ALT_FLOW(5, "altflow"),
    SUMMARY(1, "summary"),
    DESC(1, "description"),
    UC_TEXT(1),
    ;

    private double weight;
    private String name;

    public String getName() {
        return name;
    }


    LayerEnum(double weight) {
        this.weight = weight;
    }

    LayerEnum(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public static LayerEnum getType(String name) {
        switch (name) {
            case "title":
                return TITLE;
            case "pre":
                return PRE_CON;
            case "main":
                return MAIN_FLOW;
            case "sub":
                return SUB_FLOW;
            case "alternative":
                return ALT_FLOW;
            case "summary":
                return SUMMARY;
            case "description":
                return DESC;
            default:
                return null;
        }
    }
}
