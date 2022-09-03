package experiment.enums;

public enum IREnum {
    VSM("ir.model.VSM"),LSI("ir.model.LSI"),JSD("ir.model.JSD");

    private final String model;

    private IREnum(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
