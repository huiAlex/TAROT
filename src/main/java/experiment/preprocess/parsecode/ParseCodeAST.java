package experiment.preprocess.parsecode;

import edu.stanford.nlp.util.CoreMap;
import experiment.enums.CodeEnum;
import experiment.preprocess.StanfordNLP;
import experiment.preprocess.TextPreprocess;
import experiment.preprocess.parsecode.entity.ClassEntity;
import experiment.preprocess.parsecode.entity.FieldEntity;
import experiment.preprocess.parsecode.entity.MethodEntity;
import experiment.preprocess.parsecode.entity.ParameterEntity;
import experiment.preprocess.parsecode.util.ClassASTVisitor;
import experiment.preprocess.parsecode.util.JavaToXmlUtil;
import experiment.preprocess.parsecode.util.JdtAstUtil;
import experiment.project.ProjectConfig;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import util.FileIOUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ParseCodeAST {

    private String projectName;
    private Set<String> rtmClsSet;
    private ProjectConfig config;
    private List<ClassEntity> classEntityList;
    private Map<String, List<Comment>> clsBlockCommentMap;

    private Set<String> allClsSet;
    private Set<String> allMethodSet;

    public ParseCodeAST(String projectName) {
        this.projectName = projectName;
        this.config = new ProjectConfig(projectName);
        this.rtmClsSet = FileIOUtil.getClassNameFromRTM(config.rtmClassPath);
    }

    public static void main(String[] args) {
        ParseCodeAST parseCodeAST = new ParseCodeAST("test");
        parseCodeAST.parseCode();
        parseCodeAST.writeClassInfo();
    }

    // convert the Java source code file to AST, output the xml file
    private void parseCode() {
        classEntityList = new ArrayList<>();
        clsBlockCommentMap = new HashMap<>();
        FileIOUtil.initDirectory(config.astXmlPath);
        int classId = 0;
        ArrayList<File> fileList = FileIOUtil.getFileList(config.srcPath, "java");
        System.out.println(config.srcPath +" "+fileList.size());
        for (File file : fileList) {
//            System.out.println(file.getName());
            String clsName = file.getName().split("\\.")[0];
//            if (!rtmClsSet.contains(clsName)) continue;
            CompilationUnit comp = JdtAstUtil.getCompilationUnit(file.getPath());
            clsBlockCommentMap.put(clsName, comp.getCommentList());
            ClassASTVisitor visitor = new ClassASTVisitor();
            comp.accept(visitor);
            visitor.getClassEntity().setId(classId);
            visitor.getClassEntity().setField(visitor.getFieldList());
            visitor.getClassEntity().setMethod(visitor.getMethodList());
            ClassEntity classEntity = visitor.getClassEntity();
            classEntityList.add(classEntity);
            String xml = JavaToXmlUtil.beanToXml(classEntity);
            classId++;

            String fileName = file.getName();
            String fileMainName = fileName.substring(0, fileName.lastIndexOf("."));
            File outputFile = new File(config.astXmlPath, fileMainName + ".xml");
            try {
                FileWriter fileWriter = new FileWriter(outputFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(xml);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readAllMethod() {
        allClsSet = new HashSet<>();
        allMethodSet = new HashSet<>();
        for (ClassEntity cls : classEntityList) {
            allClsSet.add(cls.getClassName());
            List<MethodEntity> methodEntities = cls.getMethod();
            if (methodEntities != null) {
                for (MethodEntity method : methodEntities) {
                    allMethodSet.add(method.getMethodName());
                }
            }
        }
    }

    private void writeClassInfo() {
        config.initCodePartDir();
        readAllMethod();
        TextPreprocess preprocess;

        for (ClassEntity cls : classEntityList) {
            if (!rtmClsSet.contains(cls.getClassName())) continue;
            StringBuilder fieldNameSb = new StringBuilder();
            StringBuilder fieldTypeSb = new StringBuilder();
            StringBuilder methodNameSb = new StringBuilder();
            StringBuilder commentSb = new StringBuilder();
            StringBuilder paramNameSb = new StringBuilder();
            StringBuilder paramTypeSb = new StringBuilder();
            StringBuilder invokeMethodNameSb = new StringBuilder();

            StringBuilder processedFieldNameSb = new StringBuilder();
            StringBuilder processedFieldTypeSb = new StringBuilder();
            StringBuilder processedMethodNameSb = new StringBuilder();
            StringBuilder processedParamNameSb = new StringBuilder();
            StringBuilder processedParamTypeSb = new StringBuilder();
            StringBuilder processedInvokeMethodNameSb = new StringBuilder();

            List<MethodEntity> methodEntities = cls.getMethod();
            if (methodEntities != null) {
                for (MethodEntity method : methodEntities) {
                    methodNameSb.append(method.getMethodName() + "\n");
                    preprocess = new TextPreprocess(method.getMethodName());
                    processedMethodNameSb.append(preprocess.doCodeTextProcess() + "\n");

                    if (method.getInnerMethodInvokeList() != null) {
                        for (String invokeMethod : method.getInnerMethodInvokeList()) {
                            if (invokeMethod != "") {
//                                System.out.println(invokeMethod);
                                invokeMethod = invokeMethod.split(";")[1];
                                if (allMethodSet.contains(invokeMethod)) {
                                    invokeMethodNameSb.append(invokeMethod + "\n");
                                    preprocess = new TextPreprocess(invokeMethod);
                                    processedInvokeMethodNameSb.append(preprocess.doCodeTextProcess() + "\n");
                                }
                            }
                        }
                    }
                    List<ParameterEntity> paramEntities = method.getParametersList();
                    for (ParameterEntity param : paramEntities) {
                        if (param != null) {
                            paramNameSb.append(param.getParamName() + "\n");
                            preprocess = new TextPreprocess(param.getParamName());
                            processedParamNameSb.append(preprocess.doCodeTextProcess() + "\n");
                            if (rtmClsSet.contains(param.getParamType())) {
                                paramTypeSb.append(param.getParamType() + "\n");
                                preprocess = new TextPreprocess(param.getParamType());
                                processedParamTypeSb.append(preprocess.doCodeTextProcess() + "\n");
                            }
                        }
                    }
                }
            }

            List<FieldEntity> fieldEntities = cls.getField();
//            System.out.println(cls.getClassName() + ":" + fieldEntities.size());

            if (fieldEntities != null) {
                for (FieldEntity field : fieldEntities) {
                    fieldNameSb.append(field.getFieldName() + "\n");
                    preprocess = new TextPreprocess(field.getFieldName());
                    processedFieldNameSb.append(preprocess.doCodeTextProcess() + "\n");
                    if (rtmClsSet.contains(field.getFieldType())) {
                        fieldTypeSb.append(field.getFieldType() + "\n");
                        preprocess = new TextPreprocess(field.getFieldType());
                        processedFieldTypeSb.append(preprocess.doCodeTextProcess() + "\n");
                    }
                }
            }

            for (Comment comment : clsBlockCommentMap.get(cls.getClassName())) {
                if (comment.isBlockComment() || comment.isDocComment()) {
                    String comm = comment.toString();
                    if (!comm.contains("Licensed to the Apache Software Foundation (ASF) under one") && comm.length() < 1000) {
                        commentSb.append(comm + "\n");
                        processComment(comm, cls.getClassName());
                    }
                }
            }


            FileIOUtil.writeFile(cls.getClassName(), config.unprocessedClassNameDirPath + "/" + cls.getClassName() + ".txt");
            preprocess = new TextPreprocess(cls.getClassName());
            FileIOUtil.writeFile(preprocess.doCodeTextProcess(), config.processedClassNameDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(commentSb.toString(), config.unprocessedCommentDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(fieldNameSb.toString(), config.unprocessedFieldNameDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedFieldNameSb.toString(), config.processedFieldNameDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(fieldTypeSb.toString(), config.unprocessedFieldTypeDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedFieldTypeSb.toString(), config.processedFieldTypeDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(methodNameSb.toString(), config.unprocessedMethodNameDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedMethodNameSb.toString(), config.processedMethodNameDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(paramNameSb.toString(), config.unprocessedParamNameDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedParamNameSb.toString(), config.processedParamNameDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(paramTypeSb.toString(), config.unprocessedParamTypeDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedParamTypeSb.toString(), config.processedParamTypeDirPath + "/" + cls.getClassName() + ".txt");

            FileIOUtil.writeFile(invokeMethodNameSb.toString(), config.unprocessedInvokeMethodDirPath + "/" + cls.getClassName() + ".txt");
            FileIOUtil.writeFile(processedInvokeMethodNameSb.toString(), config.processedInvokeMethodDirPath + "/" + cls.getClassName() + ".txt");
        } // for

    }


    private void processComment(String comment, String clsName) {
        comment = comment.replaceAll("(<pre)([\\s\\S]*)(</pre>)", "")
                .replaceAll("(<([^>]*)>)", "")
                .replaceAll("(@author([^\n]*)\n)", "")
                .replaceAll("(@see([^\n]*)\n)", "")
                .replaceAll("(@since([^\n]*)\n)", "")
                .replaceAll("(@throws([^\n]*)\n)", "")
                .replaceAll("(@param([^\n]*)\n)", "")
                .replaceAll("(@version([^\n]*)\n)", "")
                .replaceAll("/\\*", "")
                .replaceAll("/\\*\\*", "")
                .replaceAll("\\*/", "")
                .replaceAll("\\*", "")
                .replaceAll("@[a-zA-z]*", "");

//        TextPreprocess preprocess = new TextPreprocess(comment);
//        FileIOUtil.writeFile(preprocess.doReqTextProcess(), config.processedCommentDirPath + "/" + clsName + ".txt");

        StanfordNLP stanfordNLP = new StanfordNLP();
        String termPairs = stanfordNLP.getTermPair(comment);
        FileIOUtil.continueWriteFile(termPairs, config.processedCommentDirPath + "/" + clsName + ".txt");
    }

}
