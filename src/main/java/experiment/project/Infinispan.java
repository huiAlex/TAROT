package experiment.project;

public class Infinispan implements Project {

    public static String projectName = "infinispan";
    public static String projectPath = "dataset/infinispan/";
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
    public String getUnProcessedIssueSummDirPath() {
        return config.unProcessedIssueSummDirPath;
    }

    @Override
    public String getUnProcessedIssueDescripDirPath() {
        return config.unProcessedIssueDescripDirPath;
    }

    @Override
    public String getIssueSummBitermDirPath() {
        return config.issueSummBitermDirPath;
    }

    @Override
    public String getIssueDescripBitermDirPath() {
        return config.issueDescripBitermDirPath;
    }

    @Override
    public String getUnProcessedUcTitleDirPath() {
        return null;
    }

    @Override
    public String getUnProcessedUcPreconDirPath() {
        return null;
    }

    @Override
    public String getUnProcessedUcMainflowDirPath() {
        return null;
    }

    @Override
    public String getUnProcessedUcSubflowDirPath() {
        return null;
    }

    @Override
    public String getUnProcessedUcAlterflowDirPath() {
        return null;
    }

    @Override
    public String getUcTitleBitermDirPath() {
        return null;
    }

    @Override
    public String getUcPreconBitermDirPath() {
        return null;
    }

    @Override
    public String getUcMainflowBitermDirPath() {
        return null;
    }

    @Override
    public String getUcSubflowBitermDirPath() {
        return null;
    }

    @Override
    public String getUcAlterflowBitermDirPath() {
        return null;
    }
}
