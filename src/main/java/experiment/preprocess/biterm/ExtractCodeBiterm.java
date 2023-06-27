package experiment.preprocess.biterm;

import experiment.enums.CodeEnum;
import experiment.enums.ProjectEnum;
import experiment.preprocess.StanfordNLP;
import experiment.project.Project;
import util.FileIOUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractCodeBiterm {
    private Project project;
    private StanfordNLP stanfordNlpUtil;

    private Map<String, Map<CodeEnum, Map<String, Integer>>> codeLayerBitermsNumMap;
    private Set<String> clsBitermsSet;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class projectClass = Class.forName(ProjectEnum.TEST.getName());
        Project project = (Project) projectClass.newInstance();
        ExtractCodeBiterm extractCodeBiterm = new ExtractCodeBiterm(project);
    }

    public ExtractCodeBiterm(Project project) {
        this.project = project;
        this.stanfordNlpUtil = new StanfordNLP();
        this.codeLayerBitermsNumMap = new HashMap<>();
        this.clsBitermsSet = new HashSet<>();
        extractBitermMap();
    }


    private void extractBitermMap() {
        File dir = new File(project.getClsNameDirPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            Map<CodeEnum, Map<String, Integer>> m = genCodeLayerBitermNumMap();
            codeLayerBitermsNumMap.put(cls, m);
        }

        File[] clsNameFiles = new File(project.getClsNameDirPath()).listFiles();
        for (File file : clsNameFiles) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            List<List<String>> list = new ArrayList<>();
            Map<String, Integer> bitermNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.CLASS_NAME);
            List<String> lineList = FileIOUtil.readFile2List(file);
            for (String line : lineList) {
                List<String> termList = Arrays.stream(line.split(" ")).collect(Collectors.toList());
                List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);

                list.addAll(bitermList);
                bitermList.stream().forEach(tp -> {
                    String biterm = BitermUtil.getNewTerm(tp);
                    bitermNumMap.put(biterm, bitermNumMap.getOrDefault(biterm, 0) + 2);
                });
            }
            clsBitermsSet.addAll(bitermNumMap.keySet());
        }

        File[] methodNameFiles = new File(project.getMethodNameDirPath()).listFiles();
        for (File file : methodNameFiles) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            List<List<String>> list = new ArrayList<>();
            Map<String, Integer> bitermNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.METHOD_NAME);
            List<String> lineList = FileIOUtil.readFile2List(file);
            for (String line : lineList) {
                List<String> termList = Arrays.stream(line.split(" ")).collect(Collectors.toList());
                List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                list.addAll(bitermList);
                bitermList.stream().forEach(bl -> {
                    String biterm = BitermUtil.getNewTerm(bl);
                    bitermNumMap.put(biterm, bitermNumMap.getOrDefault(biterm, 0) + 2);
                });
            }
            clsBitermsSet.addAll(bitermNumMap.keySet());
        }
        expandBiterm();
    }

    /**
     * expend biterm from param name, param type, field name, field type,
     * invoke method, comment
     */
    private void expandBiterm() {
        File[] invokeFiles = new File(project.getInvokeMethodDirPath()).listFiles();
        Map<String, List<String>> clsCallMap = new HashMap<>();
        for (File f : invokeFiles) {
            String cls = f.getName().split("\\.")[0];
            clsCallMap.put(cls, FileIOUtil.readFile2List(f));
        }

        File[] fieldNameFiles = new File(project.getFieldNameDirPath()).listFiles();
        Map<String, List<String>> clsFieldNameMap = new HashMap<>();
        for (File f : fieldNameFiles) {
            String cls = f.getName().split("\\.")[0];
            clsFieldNameMap.put(cls, FileIOUtil.readFile2List(f));
        }

        File[] fieldTypeFiles = new File(project.getFieldTypeDirPath()).listFiles();
        Map<String, List<String>> clsFieldTypeMap = new HashMap<>();
        for (File f : fieldTypeFiles) {
            String cls = f.getName().split("\\.")[0];
            clsFieldTypeMap.put(cls, FileIOUtil.readFile2List(f));
        }

        File[] paramNameFiles = new File(project.getParamNameDirPath()).listFiles();
        Map<String, List<String>> clsParamNameMap = new HashMap<>();
        for (File f : paramNameFiles) {
            String cls = f.getName().split("\\.")[0];
            clsParamNameMap.put(cls, FileIOUtil.readFile2List(f));
        }

        File[] paramTypeFiles = new File(project.getParamTypeDirPath()).listFiles();
        Map<String, List<String>> clsParamTypeMap = new HashMap<>();
        for (File f : paramTypeFiles) {
            String cls = f.getName().split("\\.")[0];
            clsParamTypeMap.put(cls, FileIOUtil.readFile2List(f));
        }

        Map<String, List<List<String>>> commentBitermMap = new HashMap<>();
        File dir = new File(project.getCommentDirPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            List<List<String>> l = new ArrayList<>();
            commentBitermMap.put(cls, l);
            List<String> bitermList = FileIOUtil.readFile2List(file);
            if (bitermList.size() > 0) {
                for (String tp : bitermList) {
                    List<String> list = new LinkedList<>();
                    String relation = tp.split(":")[1];
                    tp = tp.split(":")[0];
                    if (tp.split(" ").length > 2) continue;
                    list.add(tp.split(" ")[0]);
                    list.add(tp.split(" ")[1]);
                    l.add(list);
                }
            }
        }

        int addNum = 1;
        for (String cls : codeLayerBitermsNumMap.keySet()) {
            if (cls.contains("jsp")) continue;

            // extract from invoked method name
            List<String> callList = clsCallMap.get(cls);
            Map<String, Integer> invokeNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.INVOKED_METHOD);
            if (callList != null && callList.size() != 0) {
                for (String call : callList) {
                    List<String> termList = Arrays.stream(call.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = BitermUtil.getNewTerm(tp);
                        invokeNumMap.put(biterm, invokeNumMap.getOrDefault(biterm, addNum));
                    });
                    clsBitermsSet.addAll(invokeNumMap.keySet());
                }
            }

            // extract from field name
            List<String> fieldNameList = clsFieldNameMap.get(cls);
            Map<String, Integer> fieldNameNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.FIELD_NAME);
            if (fieldNameList != null && fieldNameList.size() != 0) {
                for (String fieldName : fieldNameList) {
                    List<String> termList = Arrays.stream(fieldName.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = BitermUtil.getNewTerm(tp);
                        fieldNameNumMap.put(biterm, fieldNameNumMap.getOrDefault(biterm, addNum));
                    });
                    clsBitermsSet.addAll(fieldNameNumMap.keySet());
                }
            }

            // extract from field type
            List<String> fieldTypeList = clsFieldTypeMap.get(cls);
            Map<String, Integer> fieldTypeNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.FIELD_TYPE);
            if (fieldTypeList != null && fieldTypeList.size() != 0) {
                for (String fieldType : fieldTypeList) {
                    List<String> termList = Arrays.stream(fieldType.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = BitermUtil.getNewTerm(tp);
                        fieldTypeNumMap.put(biterm, fieldTypeNumMap.getOrDefault(biterm, addNum));
                    });
                    clsBitermsSet.addAll(fieldTypeNumMap.keySet());
                }
            }

            // extract from param name
            List<String> paramNameList = clsParamNameMap.get(cls);
            Map<String, Integer> paramNameNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.PARAM_NAME);
            if (paramNameList != null && paramNameList.size() != 0) {
                for (String paramName : paramNameList) {
                    List<String> termList = Arrays.stream(paramName.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = BitermUtil.getNewTerm(tp);
                        paramNameNumMap.put(biterm, paramNameNumMap.getOrDefault(biterm, addNum));
                    });
                    clsBitermsSet.addAll(paramNameNumMap.keySet());
                }
            }

            // extract from param type
            List<String> paramTypeList = clsFieldTypeMap.get(cls);
            Map<String, Integer> paramTypeNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.PARAM_TYPE);
            if (paramTypeList != null && paramTypeList.size() != 0) {
                for (String paramType : paramTypeList) {
                    List<String> termList = Arrays.stream(paramType.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = BitermUtil.combineTwoTerm(termList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = BitermUtil.getNewTerm(tp);
                        paramTypeNumMap.put(biterm, paramTypeNumMap.getOrDefault(biterm, addNum));
                    });
                    clsBitermsSet.addAll(paramTypeNumMap.keySet());
                }
            }

            // extract from comment
            Map<String, Integer> commentNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.COMMENT);
            if (commentBitermMap.get(cls) != null) {
                List<List<String>> bitermList = commentBitermMap.get(cls);
                bitermList.stream().forEach(tp -> {
                    String biterm = BitermUtil.getNewTerm(tp);
                    commentNumMap.put(biterm, commentNumMap.getOrDefault(biterm, 0) + 1);
                });
                clsBitermsSet.addAll(commentNumMap.keySet());
            }
        }
    }

    private static Map<CodeEnum, Map<String, Integer>> genCodeLayerBitermNumMap() {
        Map<CodeEnum, Map<String, Integer>> map = new HashMap<>();
        map.put(CodeEnum.CLASS_NAME, new HashMap<String, Integer>());
        map.put(CodeEnum.METHOD_NAME, new HashMap<String, Integer>());
        map.put(CodeEnum.INVOKED_METHOD, new HashMap<String, Integer>());
        map.put(CodeEnum.COMMENT, new HashMap<String, Integer>());
        map.put(CodeEnum.FIELD_NAME, new HashMap<String, Integer>());
        map.put(CodeEnum.FIELD_TYPE, new HashMap<String, Integer>());
        map.put(CodeEnum.PARAM_NAME, new HashMap<String, Integer>());
        map.put(CodeEnum.PARAM_TYPE, new HashMap<String, Integer>());
        return map;
    }

    public Map<String, Map<CodeEnum, Map<String, Integer>>> getCodeLayerBitermsNumMap() {
        return codeLayerBitermsNumMap;
    }

    public Set<String> getClsBitermsSet() {
        return clsBitermsSet;
    }
}
