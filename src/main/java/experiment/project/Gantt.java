package experiment.project;

public class Gantt implements Project {

    public static String projectName = "gantt";
    public static String projectPath = "dataset/gantt/";
    public static String rtmClassPath = projectPath + "rtm/RTM_CLASS.txt";
    public static String reqPath = projectPath + "req";
    public static String classDirPath = projectPath + "code";

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
