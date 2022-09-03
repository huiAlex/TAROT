package experiment.project;


public class iTrust implements Project {

    private static String projectName = "itrust";
    private static String projectPath = "dataset/itrust/";

    //    private static String rtmClassPath = projectPath + "rtm/RTM_CLASS.txt";
//    private static String reqDirPath = projectPath + "req";
//    private static String classDirPath = projectPath + "code";
//
//    private static String classNameDirPath = projectPath + "code_part/class_name";
//    private static String methodNameDirPath = projectPath + "code_part/method_name";
//    private static String commentDirPath = projectPath + "code_part/comment";
//    private static String invokeMethodDirPath = projectPath + "code_part/invoke_method";
//    private static String fieldNameDirPath = projectPath + "code_part/field/fieldName";
//    private static String fieldTypeDirPath = projectPath + "code_part/field/fieldType";
//    private static String paramNameDirPath = projectPath + "code_part/param/paramName";
//    private static String paramTypeDirPath = projectPath + "code_part/param/paramType";
//
//
//    private static String unProcessedUcTitleDirPath = projectPath + "/unprocessed/req_part/uc_title";
//    private static String unProcessedUcPreconDirPath = projectPath + "/unprocessed/req_part/uc_precon";
//    private static String unProcessedUcMainflowDirPath = projectPath + "/unprocessed/req_part/uc_mainflow";
//    private static String unProcessedUcSubflowDirPath = projectPath + "/unprocessed/req_part/uc_subflow";
//    private static String unProcessedUcAlterflowDirPath = projectPath + "/unprocessed/req_part/uc_alterflow";
//
//    private static String ucTitleBitermDirPath = projectPath + "/processed/req_biterm/uc_title";
//    private static String ucPreconBitermDirPath = projectPath + "/processed/req_biterm/uc_precon";
//    private static String ucMainflowBitermDirPath = projectPath + "/processed/req_biterm/uc_mainflow";
//    private static String ucSubflowBitermDirPath = projectPath + "/processed/req_biterm/uc_subflow";
//    private static String ucAlterflowBitermDirPath = projectPath + "/processed/req_biterm/uc_alterflow";
    ProjectConfig config = new ProjectConfig(projectPath);

    @Override
    public String getRtmClassPath() {
        return config.rtmClassPath;
    }

    @Override
    public String getReqPath() {
        return config.reqDirPath;
    }

    @Override
    public String getClassDirPath() {
        return config.classDirPath;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public String getProjectPath() {
        return projectPath;
    }

    @Override
    public String getClsNameDirPath() {
        return config.classNameDirPath;
    }

    @Override
    public String getMethodNameDirPath() {
        return config.methodNameDirPath;
    }

    @Override
    public String getCommentDirPath() {
        return config.commentDirPath;
    }

    @Override
    public String getInvokeMethodDirPath() {
        return config.invokeMethodDirPath;
    }

    @Override
    public String getFieldNameDirPath() {
        return config.fieldNameDirPath;
    }

    @Override
    public String getFieldTypeDirPath() {
        return config.fieldTypeDirPath;
    }

    @Override
    public String getParamNameDirPath() {
        return config.paramNameDirPath;
    }

    @Override
    public String getParamTypeDirPath() {
        return config.paramTypeDirPath;
    }

    @Override
    public String getUnProcessedUcTitleDirPath() {
        return config.unProcessedUcTitleDirPath;
    }

    @Override
    public String getUnProcessedUcPreconDirPath() {
        return config.unProcessedUcPreconDirPath;
    }

    @Override
    public String getUnProcessedUcMainflowDirPath() {
        return config.unProcessedUcMainflowDirPath;
    }

    @Override
    public String getUnProcessedUcSubflowDirPath() {
        return config.unProcessedUcSubflowDirPath;
    }

    @Override
    public String getUnProcessedUcAlterflowDirPath() {
        return config.unProcessedUcAlterflowDirPath;
    }

    @Override
    public String getUcTitleBitermDirPath() {
        return config.ucTitleBitermDirPath;
    }

    @Override
    public String getUcPreconBitermDirPath() {
        return config.ucPreconBitermDirPath;
    }

    @Override
    public String getUcMainflowBitermDirPath() {
        return config.ucMainflowBitermDirPath;
    }

    @Override
    public String getUcSubflowBitermDirPath() {
        return config.ucSubflowBitermDirPath;
    }

    @Override
    public String getUcAlterflowBitermDirPath() {
        return config.ucAlterflowBitermDirPath;
    }

    @Override
    public String getUnProcessedIssueSummDirPath() {
        return null;
    }

    @Override
    public String getUnProcessedIssueDescripDirPath() {
        return null;
    }

    @Override
    public String getIssueSummBitermDirPath() {
        return null;
    }

    @Override
    public String getIssueDescripBitermDirPath() {
        return null;
    }
}
