package experiment.project;

import util.FileIOUtil;

public class ProjectConfig {
    private String projectPath;
    private String unprocessedPath;
    private String processedPath;

    public String rtmClassPath;
    public String reqDirPath;
    public String classDirPath;

    public String srcPath;
    public String astXmlPath;

    public String processedClassNameDirPath;
    public String processedMethodNameDirPath;
    public String processedCommentDirPath;
    public String processedInvokeMethodDirPath;
    public String processedFieldNameDirPath;
    public String processedFieldTypeDirPath;
    public String processedParamNameDirPath;
    public String processedParamTypeDirPath;

    public String unprocessedClassNameDirPath;
    public String unprocessedMethodNameDirPath;
    public String unprocessedCommentDirPath;
    public String unprocessedInvokeMethodDirPath;
    public String unprocessedFieldNameDirPath;
    public String unprocessedFieldTypeDirPath;
    public String unprocessedParamNameDirPath;
    public String unprocessedParamTypeDirPath;

    public String unprocessedUcTitleDirPath;
    public String unprocessedUcPreconDirPath;
    public String unprocessedUcMainflowDirPath;
    public String unprocessedUcSubflowDirPath;
    public String unprocessedUcAlterflowDirPath;

    public String ucTitleBitermDirPath;
    public String ucPreconBitermDirPath;
    public String ucMainflowBitermDirPath;
    public String ucSubflowBitermDirPath;
    public String ucAlterflowBitermDirPath;

    public String classNameBitermDirPath;
    public String methodNameBitermDirPath;
    public String commentBitermDirPath;
    public String invokeMethodBitermDirPath;
    public String fieldNameBitermDirPath;
    public String fieldTypeBitermDirPath;
    public String paramNameBitermDirPath;
    public String paramTypeBitermDirPath;

    public String unprocessedIssueSummDirPath;
    public String unprocessedIssueDescripDirPath;

    public String issueSummBitermDirPath;
    public String issueDescripBitermDirPath;

    public ProjectConfig(String projectName) {
        this.projectPath = "dataset/" + projectName;
        this.unprocessedPath = projectPath + "/unprocessed";
        this.processedPath = projectPath + "/processed";

        setProjectPath();
    }

    public void setProjectPath() {

        rtmClassPath = projectPath + "/rtm/RTM_CLASS.txt";
        srcPath = unprocessedPath + "/code/src";
        astXmlPath = unprocessedPath + "/code/ast_xml";
        reqDirPath = processedPath + "/req";
        classDirPath = processedPath + "/code/class";

        processedClassNameDirPath = processedPath + "/code/part/class_name";
        processedMethodNameDirPath = processedPath + "/code/part/method_name";
        processedCommentDirPath = processedPath + "/code/part/comment";
        processedInvokeMethodDirPath = processedPath + "/code/part/invoke_method";
        processedFieldNameDirPath = processedPath + "/code/part/field/fieldName";
        processedFieldTypeDirPath = processedPath + "/code/part/field/fieldType";
        processedParamNameDirPath = processedPath + "/code/part/param/paramName";
        processedParamTypeDirPath = processedPath + "/code/part/param/paramType";

        unprocessedClassNameDirPath = unprocessedPath + "/code/part/class_name";
        unprocessedMethodNameDirPath = unprocessedPath + "/code/part/method_name";
        unprocessedCommentDirPath = unprocessedPath + "/code/part/comment";
        unprocessedInvokeMethodDirPath = unprocessedPath + "/code/part/invoke_method";
        unprocessedFieldNameDirPath = unprocessedPath + "/code/part/field/fieldName";
        unprocessedFieldTypeDirPath = unprocessedPath + "/code/part/field/fieldType";
        unprocessedParamNameDirPath = unprocessedPath + "/code/part/param/paramName";
        unprocessedParamTypeDirPath = unprocessedPath + "/code/part/param/paramType";

        unprocessedUcTitleDirPath = unprocessedPath + "/req/part/title";
        unprocessedUcPreconDirPath = unprocessedPath + "/req/part/precon";
        unprocessedUcMainflowDirPath = unprocessedPath + "/req/part/mf";
        unprocessedUcSubflowDirPath = unprocessedPath + "/req/part/sf";
        unprocessedUcAlterflowDirPath = unprocessedPath + "/req/part/af";

        classNameBitermDirPath = projectPath + "/biterm/code/class_name";
        methodNameBitermDirPath = projectPath + "/biterm/code/method_name";
        commentBitermDirPath = projectPath + "/biterm/code/comment";
        invokeMethodBitermDirPath = projectPath + "/biterm/code/invoke_method";
        fieldNameBitermDirPath = projectPath + "/biterm/code/fieldName";
        fieldTypeBitermDirPath = projectPath + "/biterm/code/fieldType";
        paramNameBitermDirPath = projectPath + "/biterm/code/paramName";
        paramTypeBitermDirPath = projectPath + "/biterm/code/paramType";


        ucTitleBitermDirPath = processedPath + "/req_biterm/title";
        ucPreconBitermDirPath = processedPath + "/req_biterm/precon";
        ucMainflowBitermDirPath = processedPath + "/req_biterm/mf";
        ucSubflowBitermDirPath = processedPath + "/req_biterm/sf";
        ucAlterflowBitermDirPath = processedPath + "/req_biterm/af";

        unprocessedIssueSummDirPath = unprocessedPath + "/req/part/summary";
        unprocessedIssueDescripDirPath = unprocessedPath + "/req/part/description";

        issueSummBitermDirPath = processedPath + "/req_biterm/summary";
        issueDescripBitermDirPath = processedPath + "/req_biterm/description";
    }

    public void initCodePartDir() {
        FileIOUtil.initDirectory(unprocessedCommentDirPath);
        FileIOUtil.initDirectory(unprocessedClassNameDirPath);
        FileIOUtil.initDirectory(unprocessedFieldNameDirPath);
        FileIOUtil.initDirectory(unprocessedFieldTypeDirPath);
        FileIOUtil.initDirectory(unprocessedInvokeMethodDirPath);
        FileIOUtil.initDirectory(unprocessedMethodNameDirPath);
        FileIOUtil.initDirectory(unprocessedParamNameDirPath);
        FileIOUtil.initDirectory(unprocessedParamTypeDirPath);

        FileIOUtil.initDirectory(processedCommentDirPath);
        FileIOUtil.initDirectory(processedClassNameDirPath);
        FileIOUtil.initDirectory(processedFieldNameDirPath);
        FileIOUtil.initDirectory(processedFieldTypeDirPath);
        FileIOUtil.initDirectory(processedInvokeMethodDirPath);
        FileIOUtil.initDirectory(processedMethodNameDirPath);
        FileIOUtil.initDirectory(processedParamNameDirPath);
        FileIOUtil.initDirectory(processedParamTypeDirPath);

    }

}
