package experiment.project;

public class iTrust implements Project {

    public static String projectName = "itrust";
    public static String projectPath = "dataset/itrust";
    ProjectConfig config = new ProjectConfig(projectName);

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
        return config.processedClassNameDirPath;
    }

    @Override
    public String getMethodNameDirPath() {
        return config.processedMethodNameDirPath;
    }

    @Override
    public String getCommentDirPath() {
        return config.processedCommentDirPath;
    }

    @Override
    public String getInvokeMethodDirPath() {
        return config.processedInvokeMethodDirPath;
    }

    @Override
    public String getFieldNameDirPath() {
        return config.processedFieldNameDirPath;
    }

    @Override
    public String getFieldTypeDirPath() {
        return config.processedFieldTypeDirPath;
    }

    @Override
    public String getParamNameDirPath() {
        return config.processedParamNameDirPath;
    }

    @Override
    public String getParamTypeDirPath() {
        return config.processedParamTypeDirPath;
    }

    @Override
    public String getUnprocessedClsNameDirPath() {
        return config.unprocessedClassNameDirPath;
    }

    @Override
    public String getUnprocessedMethodNameDirPath() {
        return config.unprocessedMethodNameDirPath;
    }

    @Override
    public String getUnprocessedCommentDirPath() {
        return config.unprocessedCommentDirPath;
    }

    @Override
    public String getUnprocessedInvokeMethodDirPath() {
        return config.unprocessedInvokeMethodDirPath;
    }

    @Override
    public String getUnprocessedFieldNameDirPath() {
        return config.unprocessedFieldNameDirPath;
    }

    @Override
    public String getUnprocessedFieldTypeDirPath() {
        return config.unprocessedFieldTypeDirPath;
    }

    @Override
    public String getUnprocessedParamNameDirPath() {
        return config.unprocessedParamNameDirPath;
    }

    @Override
    public String getUnprocessedParamTypeDirPath() {
        return config.unprocessedParamTypeDirPath;
    }

    @Override
    public String getClsNameBitermDirPath() {
        return config.classNameBitermDirPath;
    }

    @Override
    public String getMethodNameBitermDirPath() {
        return config.methodNameBitermDirPath;
    }

    @Override
    public String getCommentBitermDirPath() {
        return config.commentBitermDirPath;
    }

    @Override
    public String getInvokeMethodBitermDirPath() {
        return config.invokeMethodBitermDirPath;
    }

    @Override
    public String getFieldNameBitermDirPath() {
        return config.fieldNameBitermDirPath;
    }

    @Override
    public String getFieldTypeBitermDirPath() {
        return config.fieldTypeBitermDirPath;
    }

    @Override
    public String getParamNameBitermDirPath() {
        return config.paramNameBitermDirPath;
    }

    @Override
    public String getParamTypeBitermDirPath() {
        return config.paramTypeBitermDirPath;
    }

    @Override
    public String getUnprocessedUcTitleDirPath() {
        return config.unprocessedUcTitleDirPath;
    }

    @Override
    public String getUnprocessedUcPreconDirPath() {
        return config.unprocessedUcPreconDirPath;
    }

    @Override
    public String getUnprocessedUcMainflowDirPath() {
        return config.unprocessedUcMainflowDirPath;
    }

    @Override
    public String getUnprocessedUcSubflowDirPath() {
        return config.unprocessedUcSubflowDirPath;
    }

    @Override
    public String getUnprocessedUcAlterflowDirPath() {
        return config.unprocessedUcAlterflowDirPath;
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
    public String getUnprocessedIssueSummDirPath() {
        return null;
    }

    @Override
    public String getUnprocessedIssueDescripDirPath() {
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
