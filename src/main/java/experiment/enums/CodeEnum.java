package experiment.enums;

public enum CodeEnum {
    CLASS_NAME(1, "clsName"),
    METHOD_NAME(2, "methodName"),
    COMMENT(3, "comment"),
    INVOKED_METHOD(4, "invokeMethod"),
    FIELD_NAME(5, "fieldName"),
    FIELD_TYPE(6, "fieldType"),
    PARAM_NAME(7, "paramName"),
    PARAM_TYPE(8, "paramType");

    private double weight;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    CodeEnum(double weight) {
        this.weight = weight;
    }

    CodeEnum(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public static CodeEnum getType(String name) {
        switch (name) {
            case "clsName":
                return CLASS_NAME;
            case "methodName":
                return METHOD_NAME;
            case "comment":
                return COMMENT;
            case "invokeMethod":
                return INVOKED_METHOD;
            case "fieldName":
                return FIELD_NAME;
            case "fieldType":
                return FIELD_TYPE;
            case "paramName":
                return PARAM_NAME;
            case "paramType":
                return PARAM_TYPE;
            default:
                return null;
        }
    }

}
