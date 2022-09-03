package experiment.project;

public class ProjectConfig {
    private static String projectPath;

    public static String rtmClassPath;
    public static String reqDirPath;
    public static String classDirPath;

    public static String classNameDirPath;
    public static String methodNameDirPath;
    public static String commentDirPath;
    public static String invokeMethodDirPath;
    public static String fieldNameDirPath;
    public static String fieldTypeDirPath;
    public static String paramNameDirPath;
    public static String paramTypeDirPath;

    public static String unProcessedUcTitleDirPath;
    public static String unProcessedUcPreconDirPath;
    public static String unProcessedUcMainflowDirPath;
    public static String unProcessedUcSubflowDirPath;
    public static String unProcessedUcAlterflowDirPath;

    public static String ucTitleBitermDirPath;
    public static String ucPreconBitermDirPath;
    public static String ucMainflowBitermDirPath;
    public static String ucSubflowBitermDirPath;
    public static String ucAlterflowBitermDirPath;

    public static String unProcessedIssueSummDirPath;
    public static String unProcessedIssueDescripDirPath;

    public static String issueSummBitermDirPath;
    public static String issueDescripBitermDirPath;

    public ProjectConfig(String projectPath) {
        this.projectPath = projectPath;
        initProjectPath();
    }

    public static void initProjectPath() {
        rtmClassPath = projectPath + "rtm/RTM_CLASS.txt";
        reqDirPath = projectPath + "/processed/req";
        classDirPath = projectPath + "/processed/code";

        classNameDirPath = projectPath + "/processed/code_part/class_name";
        methodNameDirPath = projectPath + "/processed/code_part/method_name";
        commentDirPath = projectPath + "/processed/code_part/comment";
        invokeMethodDirPath = projectPath + "/processed/code_part/invoke_method";
        fieldNameDirPath = projectPath + "/processed/code_part/field/fieldName";
        fieldTypeDirPath = projectPath + "/processed/code_part/field/fieldType";
        paramNameDirPath = projectPath + "/processed/code_part/param/paramName";
        paramTypeDirPath = projectPath + "/processed/code_part/param/paramType";

        unProcessedUcTitleDirPath = projectPath + "/unprocessed/req_part/title";
        unProcessedUcPreconDirPath = projectPath + "/unprocessed/req_part/precon";
        unProcessedUcMainflowDirPath = projectPath + "/unprocessed/req_part/mf";
        unProcessedUcSubflowDirPath = projectPath + "/unprocessed/req_part/sf";
        unProcessedUcAlterflowDirPath = projectPath + "/unprocessed/req_part/af";

        ucTitleBitermDirPath = projectPath + "/processed/req_biterm/title";
        ucPreconBitermDirPath = projectPath + "/processed/req_biterm/precon";
        ucMainflowBitermDirPath = projectPath + "/processed/req_biterm/mf";
        ucSubflowBitermDirPath = projectPath + "/processed/req_biterm/sf";
        ucAlterflowBitermDirPath = projectPath + "/processed/req_biterm/af";

        unProcessedIssueSummDirPath = projectPath + "/unprocessed/req_part/summary";
        unProcessedIssueDescripDirPath = projectPath + "/unprocessed/req_part/description";

        issueSummBitermDirPath = projectPath + "/processed/req_biterm/summary";
        issueDescripBitermDirPath = projectPath + "/processed/req_biterm/description";
    }
}
