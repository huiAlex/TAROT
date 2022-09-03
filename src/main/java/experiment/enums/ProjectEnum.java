package experiment.enums;

public enum ProjectEnum {

    ITRUST("experiment.project.iTrust"),
    GANTT("experiment.project.Gantt"),
    MAVEN("experiment.project.Maven"),
    INFINISPAN("experiment.project.Infinispan"),
    SEAM("experiment.project.Seam"),
    GROOVY("experiment.project.Groovy"),
    DROOLS("experiment.project.Drools"),
    PIG("experiment.project.Pig"),
    DERBY("experiment.project.Derby"),
    ;

    String name;

    ProjectEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProjectEnum getProject(String projectName) {
        switch (projectName) {
            case "itrust":
                return ITRUST;
            case "gantt":
                return GANTT;
            case "maven":
                return MAVEN;
            case "infinispan":
                return INFINISPAN;
            case "groovy":
                return GROOVY;
            case "seam":
                return SEAM;
            case "drools":
                return DROOLS;
            case "pig":
                return PIG;
            case "derby":
                return DERBY;
            default:
                return null;
        }
    }
}
