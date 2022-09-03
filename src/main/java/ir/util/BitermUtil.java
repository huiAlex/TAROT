package ir.util;

import document.Artifact;
import document.ArtifactsCollection;
import document.TextDataset;
import experiment.enums.CodeEnum;
import experiment.enums.LayerEnum;
import experiment.enums.ProjectEnum;
import experiment.project.Project;
import util.FileIOUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class BitermUtil {
    private static ProjectEnum projectEnum;
    private static Project project;

//    private static String classNameDir;
//    private static String commentBitermDirPath;
//    private static String invokeMethodDirPath;
//    private static String methodNameDirPath;
//    private static String fieldNameDirPath;
//    private static String fieldTypeDirPath;
//    private static String paramNameDirPath;
//    private static String paramTypeDirPath;
//
//    private static String reqDirPath;
//
//    public static String titleBitermDirPath;
//    public static String mfBitermDirPath;
//    public static String preconBitermDirPath;
//    public static String sfBitermDirPath;
//    public static String afBitermDirPath;
//
//    public static String descripBitermDirPath;
//    public static String summBitermDirPath;

    private static Map<String, Map<LayerEnum, Set<String>>> reqLayerBitermsMap;
    private static Map<String, Map<LayerEnum, Map<String, Integer>>> reqLayerBitermNumMap;
    private static Map<String, Map<String, Integer>> reqBitermsNumMap;

    private static Map<String, List<List<String>>> initCodeBitermsMap;
    private static Map<String, Map<CodeEnum, Set<String>>> codeLayerBiterms;
    private static Map<String, Map<CodeEnum, Map<String, Integer>>> codeLayerBitermsNumMap;
    private static Map<String, Map<String, Integer>> codeBitermsNumMap;

    private static Map<String, Map<String, Integer>> reqBitermsMap;
    private static Map<String, Map<String, Integer>> codeBitermsMap;

    public Map<String, Map<String, Integer>> getReqBitermsMap() {
        return reqBitermsMap;
    }

    public Map<String, Map<String, Integer>> getCodeBitermsMap() {
        return codeBitermsMap;
    }

    public Map<String, Map<LayerEnum, Set<String>>> getReqLayerBitermsMap() {
        return reqLayerBitermsMap;
    }

    public Map<String, Map<CodeEnum, Set<String>>> getCodeLayerBiterms() {
        return codeLayerBiterms;
    }

    public BitermUtil(Project project) {
        this.project = project;
        this.projectEnum = ProjectEnum.getProject(project.getProjectName());
        String projectPath = project.getProjectPath();

//        this.classNameDir = projectPath + "code_part/class_name";
//        this.methodNameDirPath = projectPath + "code_part/method_name";
//        this.commentBitermDirPath = projectPath + "code_part/comment";
//        this.invokeMethodDirPath = projectPath + "code_part/invoke_method";
//        this.fieldNameDirPath = projectPath + "code_part/field/fieldName";
//        this.fieldTypeDirPath = projectPath + "code_part/field/fieldType";
//        this.paramNameDirPath = projectPath + "code_part/param/paramName";
//        this.paramTypeDirPath = projectPath + "code_part/param/paramType";
//
//        this.reqDirPath = projectPath + "req";
//        this.titleBitermDirPath = projectPath + "req_biterm/title";
//        this.preconBitermDirPath = projectPath + "req_biterm/precon";
//        this.mfBitermDirPath = projectPath + "req_biterm/mf";
//        this.sfBitermDirPath = projectPath + "req_biterm/sf";
//        this.afBitermDirPath = projectPath + "req_biterm/af";
//
//        this.descripBitermDirPath = projectPath + "req_biterm/description";
//        this.summBitermDirPath = projectPath + "req_biterm/summary";

        this.codeLayerBitermsNumMap = new HashMap<>();
        this.codeBitermsNumMap = new HashMap<>();
        this.initCodeBitermsMap = new HashMap<>();
        this.reqLayerBitermsMap = new HashMap<>();
        this.reqLayerBitermNumMap = new HashMap<>();
        this.codeLayerBiterms = new HashMap<>();
        this.reqBitermsNumMap = new HashMap<>();
        this.reqBitermsMap = new HashMap<>();
        this.codeBitermsMap = new HashMap<>();

        extractBiterms();
    }

    private static void extractBiterms() {
        initCodeBitermsMap = getBitermMap();
        reqBitermsMap = getStanfordReqBitermMap(initCodeBitermsMap);
        codeBitermsMap = getFinalClassBitermMap(codeLayerBitermsNumMap, reqBitermsMap);
    }

    private static Map<String, Map<String, Integer>> getFinalClassBitermMap(
            Map<String, Map<CodeEnum, Map<String, Integer>>> codeLayerBitermNumMap,
            Map<String, Map<String, Integer>> reqBitermNumMap) {

        Set<String> reqBitermSet = new HashSet<>();
        for (String req : reqBitermNumMap.keySet()) {
            Map<String, Integer> map = reqBitermNumMap.get(req);
            for (String biterm : map.keySet()) {
                reqBitermSet.add(biterm);
            }
        }
        for (String clazz : codeLayerBitermNumMap.keySet()) {
            Map<String, Integer> clsNameBitermMap = new HashMap<>();
            Map<String, Integer> methodNameBitermMap = new HashMap<>();
            Map<String, Integer> invokeNameBitermMap = new HashMap<>();
            Map<String, Integer> commentNameBitermMap = new HashMap<>();
            Map<String, Integer> fieldNameBitermMap = new HashMap<>();
            Map<String, Integer> fieldTypeBitermMap = new HashMap<>();
            Map<String, Integer> paramNameBitermMap = new HashMap<>();
            Map<String, Integer> paramTypeBitermMap = new HashMap<>();

            Map<String, Integer> tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.CLASS_NAME);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    clsNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.METHOD_NAME);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    methodNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.INVOKED_METHOD);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    invokeNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.COMMENT);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    commentNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.FIELD_NAME);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    fieldNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.FIELD_TYPE);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    fieldTypeBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.PARAM_NAME);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    paramNameBitermMap.put(biterm, tmpMap.get(biterm));
            }
            tmpMap = codeLayerBitermNumMap.get(clazz).get(CodeEnum.PARAM_TYPE);
            for (String biterm : tmpMap.keySet()) {
                if (reqBitermSet.contains(biterm))
                    paramTypeBitermMap.put(biterm, tmpMap.get(biterm));
            }

            Map<String, Integer> temp1 = mergeMap(clsNameBitermMap, methodNameBitermMap);
            Map<String, Integer> temp2 = mergeMap(invokeNameBitermMap, commentNameBitermMap);
            Map<String, Integer> temp3 = mergeMap(fieldNameBitermMap, fieldTypeBitermMap);
            Map<String, Integer> temp4 = mergeMap(paramNameBitermMap, paramTypeBitermMap);

            Map<String, Integer> tmp = mergeMap(temp1, temp2);
            tmp = mergeMap(tmp, mergeMap(temp3, temp4));
            codeBitermsNumMap.put(clazz, tmp);
        }
        return codeBitermsNumMap;
    }

    private static Map<String, Integer> mergeMap(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (String biterm : map2.keySet()) {
            map1.merge(biterm, map2.get(biterm), Integer::sum);
        }
        return map1;
    }

    public static TextDataset updateTextDataSet(TextDataset textDataset, Map<String, Map<String, Integer>> reqBitermMap,
                                                Map<String, Map<String, Integer>> codeBitermMap) {
        ArtifactsCollection sourceCollection = textDataset.getSourceCollection();
        ArtifactsCollection targetCollection = textDataset.getTargetCollection();

        ArtifactsCollection sc = new ArtifactsCollection();
        ArtifactsCollection tc = new ArtifactsCollection();

        Map<String, String> reqSupMap = getAddString(reqBitermMap);
        Map<String, String> codeSupMap = getAddString_code(codeBitermMap);

        for (String source : sourceCollection.keySet()) {
            Artifact artifact = sourceCollection.get(source);
            String newText;
            if (reqSupMap.containsKey(source)) {
                newText = artifact.text.trim() + " " + reqSupMap.get(source).trim();
            } else {
                newText = artifact.text.trim();
            }
            sc.put(source, new Artifact(artifact.id, newText));
        }

        for (String target : targetCollection.keySet()) {
            Artifact artifact = targetCollection.get(target);
            String newText;
            if (codeSupMap.containsKey(target)) {
                newText = artifact.text.trim() + " " + codeSupMap.get(target).trim();
            } else {
                newText = artifact.text.trim();
            }
            tc.put(target, new Artifact(artifact.id, newText));
        }
        TextDataset newTd = new TextDataset(sc, tc, textDataset.getRtm());
        return newTd;
    }


    private static Map<String, String> getAddString(Map<String, Map<String, Integer>> map) {
        Map<String, String> res = new HashMap<>();
        for (String req : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            Map<String, Integer> tmp = map.get(req);
            for (String biterm : tmp.keySet()) {
                Integer cnt = tmp.get(biterm);
                for (int i = 0; i < cnt; i++) {
                    sb.append(biterm).append(" ");
                }
            }
            res.put(req, sb.toString().trim());
        }
        return res;
    }

    private static Map<String, String> getAddString_code(Map<String, Map<String, Integer>> map) {
        Map<String, String> res = new HashMap<>();
        for (String cls : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            Map<String, Integer> tmp = map.get(cls);
            for (String biterm : tmp.keySet()) {
                Integer cnt = tmp.get(biterm);
                for (int i = 0; i < cnt; i++) {
                    sb.append(biterm).append(" ");
                }
            }
            res.put(cls, sb.toString().trim());
        }
        return res;
    }


    private static Map<String, Map<String, Integer>> getStanfordReqBitermMap(Map<String, List<List<String>>> codeBitermMap) {
        Set<String> bag = new HashSet<>();
        for (String key : codeBitermMap.keySet()) {
            List<List<String>> list = codeBitermMap.get(key);
            for (List<String> biterm : list) {
                bag.add(getNewTerm(biterm));
            }
        }
        File dir = new File(project.getReqPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String req = file.getName().split(".txt")[0];
            Map<String, Integer> tmp = new HashMap<>();
            reqBitermsNumMap.put(req, tmp);
            Map<LayerEnum, Set<String>> layerBitermMap = genLayerBitermMap();
            reqLayerBitermsMap.put(req, layerBitermMap);
            Map<LayerEnum, Map<String, Integer>> m = genLayerBitermNumMap();
            reqLayerBitermNumMap.put(req, m);
        }

        if (projectEnum.getName().equals(ProjectEnum.MAVEN.getName())
                || projectEnum.getName().equals(ProjectEnum.SEAM.getName())
                || projectEnum.getName().equals(ProjectEnum.GROOVY.getName())
                || projectEnum.getName().equals(ProjectEnum.PIG.getName())
                || projectEnum.getName().equals(ProjectEnum.DERBY.getName())
                || projectEnum.getName().equals(ProjectEnum.INFINISPAN.getName())
                || projectEnum.getName().equals(ProjectEnum.DROOLS.getName())) {
            readLayerBiterm(LayerEnum.SUMMARY, project.getIssueSummBitermDirPath(), reqBitermsNumMap, bag);
            readLayerBiterm(LayerEnum.DESC, project.getIssueDescripBitermDirPath(), reqBitermsNumMap, bag);
        } else {
            readLayerBiterm(LayerEnum.TITLE, project.getUcTitleBitermDirPath(), reqBitermsNumMap, bag);
            readLayerBiterm(LayerEnum.PRE_CON, project.getUcPreconBitermDirPath(), reqBitermsNumMap, bag);
            readLayerBiterm(LayerEnum.MAIN_FLOW, project.getUcMainflowBitermDirPath(), reqBitermsNumMap, bag);
            readLayerBiterm(LayerEnum.SUB_FLOW, project.getUcSubflowBitermDirPath(), reqBitermsNumMap, bag);
            readLayerBiterm(LayerEnum.ALT_FLOW, project.getUcAlterflowBitermDirPath(), reqBitermsNumMap, bag);
        }
        return reqBitermsNumMap;
    }

    private static void readLayerBiterm(LayerEnum layer, String bitermDirPath,
                                        Map<String, Map<String, Integer>> reqBitermNumMap,
                                        Set<String> bag) {
        File dir = new File(bitermDirPath);
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String req = file.getName().split(".txt")[0];
            Map<String, Integer> bitermNumMap = reqBitermNumMap.get(req);
            Set<String> layerBitermSet = reqLayerBitermsMap.get(req).get(layer);
            Map<String, Integer> layerBitermNumMap = reqLayerBitermNumMap.get(req).get(layer);
            List<String> bitermList = FileIOUtil.readFile2List(file);

            if (bitermList.size() > 0) {
                for (String biterm : bitermList) {
                    String relation = biterm.split(":")[1];
                    biterm = biterm.split(":")[0];

                    int addNum = 1;
                    List<String> list = new LinkedList<>();
                    list.add(biterm.split(" ")[0]);
                    list.add(biterm.split(" ")[1]);
                    String biterm1 = getNewTerm(list);
                    if (bag.contains(biterm1)) {
                        layerBitermSet.add(biterm1);
                        if (layer.getName().equals(LayerEnum.TITLE.getName())
                                || layer.getName().equals(LayerEnum.SUMMARY.getName())) {
                            addNum = 1;
                        }
                        bitermNumMap.put(biterm1, bitermNumMap.getOrDefault(biterm1, 0) + addNum);
                        layerBitermNumMap.put(biterm1, layerBitermNumMap.getOrDefault(biterm1, 0) + addNum);
                    }

                    List<String> list2 = new LinkedList<>();
                    list2.add(biterm.split(" ")[1]);
                    list2.add(biterm.split(" ")[0]);
                    String biterm2 = getNewTerm(list2);
                    if (bag.contains(biterm2)) {
                        layerBitermSet.add(biterm2);
                        if (layer.getName().equals(LayerEnum.TITLE.getName())
                                || layer.getName().equals(LayerEnum.SUMMARY.getName())) {
                            addNum = 1;
                        }
                        bitermNumMap.put(biterm2, bitermNumMap.getOrDefault(biterm2, 0) + addNum);
                        layerBitermNumMap.put(biterm2, layerBitermNumMap.getOrDefault(biterm2, 0) + addNum);
                    }
                }
            } // if
        } // for
    }

    private static Map<LayerEnum, Set<String>> genLayerBitermMap() {
        Map<LayerEnum, Set<String>> map = new HashMap<>();
        map.put(LayerEnum.TITLE, new HashSet<>());
        map.put(LayerEnum.PRE_CON, new HashSet<>());
        map.put(LayerEnum.MAIN_FLOW, new HashSet<>());
        map.put(LayerEnum.SUB_FLOW, new HashSet<>());
        map.put(LayerEnum.ALT_FLOW, new HashSet<>());
        map.put(LayerEnum.UC_TEXT, new HashSet<>());
        map.put(LayerEnum.SUMMARY, new HashSet<>());
        map.put(LayerEnum.DESC, new HashSet<>());
        return map;
    }

    private static Map<LayerEnum, Map<String, Integer>> genLayerBitermNumMap() {
        Map<LayerEnum, Map<String, Integer>> map = new HashMap<>();
        map.put(LayerEnum.TITLE, new HashMap<String, Integer>());
        map.put(LayerEnum.PRE_CON, new HashMap<String, Integer>());
        map.put(LayerEnum.MAIN_FLOW, new HashMap<String, Integer>());
        map.put(LayerEnum.SUB_FLOW, new HashMap<String, Integer>());
        map.put(LayerEnum.ALT_FLOW, new HashMap<String, Integer>());
        map.put(LayerEnum.UC_TEXT, new HashMap<String, Integer>());
        map.put(LayerEnum.SUMMARY, new HashMap<String, Integer>());
        map.put(LayerEnum.DESC, new HashMap<String, Integer>());
        return map;
    }


    private static String getNewTerm(List<String> pair) {
        StringBuilder sb = new StringBuilder();
        sb.append(pair.get(0));
        for (int i = 1; i < pair.size(); i++) {
            char[] chs = pair.get(i).toCharArray();
            chs[0] = (char) (chs[0] - 32);
            sb.append(new String(chs));
        }
        return sb.toString();
    }

    private static List<List<String>> combineTwoTerm(List<String> termList) {
        List<List<String>> result = new ArrayList<>();
        if (termList.size() > 1) {
            for (int i = 0; i < termList.size(); i++) {
                for (int j = i + 1; j < termList.size(); j++) {
                    List<String> list = new LinkedList<String>();
                    if (termList.get(i).equals(termList.get(j)))
                        continue;
                    list.add(termList.get(i));
                    list.add(termList.get(j));
                    result.add(list);
                }
            }
        }
        return result;
    }

    private static Map<String, List<List<String>>> getBitermMap() {
        Map<String, List<List<String>>> res = new HashMap<>();

        File dir = new File(project.getClsNameDirPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            Map<CodeEnum, Map<String, Integer>> m = genCodeLayerBitermNumMap();
            codeLayerBitermsNumMap.put(cls, m);
            Map<CodeEnum, Set<String>> layerBitermMap = genCodeLayerBitermMap();
            codeLayerBiterms.put(cls, layerBitermMap);
        }

        File[] clsNameFiles = new File(project.getClsNameDirPath()).listFiles();
        for (File file : clsNameFiles) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            List<List<String>> list = new ArrayList<>();
            Set<String> layerBitermList = codeLayerBiterms.get(cls).get(CodeEnum.CLASS_NAME);
            Map<String, Integer> bitermNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.CLASS_NAME);
            List<String> lineList = FileIOUtil.readFile2List(file);
            for (String line : lineList) {
                List<String> termList = Arrays.stream(line.split(" ")).collect(Collectors.toList());
                List<List<String>> bitermList = combineTwoTerm(termList);
                list.addAll(bitermList);
                bitermList.stream().forEach(tp -> {
                    String biterm = getNewTerm(tp);
                    layerBitermList.add(biterm);
                    bitermNumMap.put(biterm, bitermNumMap.getOrDefault(biterm, 0) + 2);
                });
            }
            res.put(cls, list);
        }

        File[] funcNameFiles = new File(project.getMethodNameDirPath()).listFiles();
        for (File file : funcNameFiles) {
            if (!file.getName().contains(".txt")) continue;
            String cls = file.getName().split(".txt")[0];
            List<List<String>> list = new ArrayList<>();
            Set<String> layerBitermSet = codeLayerBiterms.get(cls).get(CodeEnum.METHOD_NAME);
            Map<String, Integer> bitermNumMap = codeLayerBitermsNumMap.get(cls).get(CodeEnum.METHOD_NAME);
            List<String> lineList = FileIOUtil.readFile2List(file);
            for (String line : lineList) {
                List<String> termList = Arrays.stream(line.split(" ")).collect(Collectors.toList());
                List<List<String>> bitermList = combineTwoTerm(termList);
                list.addAll(bitermList);
                bitermList.stream().forEach(bl -> {
                    String biterm = getNewTerm(bl);
                    layerBitermSet.add(biterm);
                    bitermNumMap.put(biterm, bitermNumMap.getOrDefault(biterm, 0) + 2);
                });
            }
            res.get(cls).addAll(list);
        }
        res = expandBiterm(res);
        return res;
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

    private static Map<CodeEnum, Set<String>> genCodeLayerBitermMap() {
        Map<CodeEnum, Set<String>> map = new HashMap<>();
        map.put(CodeEnum.CLASS_NAME, new HashSet<>());
        map.put(CodeEnum.COMMENT, new HashSet<>());
        map.put(CodeEnum.INVOKED_METHOD, new HashSet<>());
        map.put(CodeEnum.METHOD_NAME, new HashSet<>());
        map.put(CodeEnum.FIELD_NAME, new HashSet<>());
        map.put(CodeEnum.FIELD_TYPE, new HashSet<>());
        map.put(CodeEnum.PARAM_NAME, new HashSet<>());
        map.put(CodeEnum.PARAM_TYPE, new HashSet<>());
        return map;
    }

    private static Map<String, List<List<String>>> expandBiterm(Map<String, List<List<String>>> map) {
        File[] invokeFiles = new File(project.getInvokeMethodDirPath()).listFiles();
        Map<String, List<String>> clsCallMap = new HashMap<>();
        for (File f : invokeFiles) {
            String cls = f.getName().split("\\.")[0];
            clsCallMap.put(cls, FileIOUtil.readFile2List(f));
        }

        File[] fieldNameFiles = new File(project.getFieldNameDirPath()).listFiles();
        Map<String, List<String>> clazzFieldNameMap = new HashMap<>();
        for (File f : fieldNameFiles) {
            String clazz = f.getName().split("\\.")[0];
            clazzFieldNameMap.put(clazz, FileIOUtil.readFile2List(f));
        }

        File[] fieldTypeFiles = new File(project.getFieldTypeDirPath()).listFiles();
        Map<String, List<String>> clazzFieldTypeMap = new HashMap<>();
        for (File f : fieldTypeFiles) {
            String clazz = f.getName().split("\\.")[0];
            clazzFieldTypeMap.put(clazz, FileIOUtil.readFile2List(f));
        }

        File[] paramNameFiles = new File(project.getParamNameDirPath()).listFiles();
        Map<String, List<String>> clazzParamNameMap = new HashMap<>();
        for (File f : paramNameFiles) {
            String clazz = f.getName().split("\\.")[0];
            clazzParamNameMap.put(clazz, FileIOUtil.readFile2List(f));
        }

        File[] paramTypeFiles = new File(project.getParamTypeDirPath()).listFiles();
        Map<String, List<String>> clazzParamTypeMap = new HashMap<>();
        for (File f : paramTypeFiles) {
            String clazz = f.getName().split("\\.")[0];
            clazzParamTypeMap.put(clazz, FileIOUtil.readFile2List(f));
        }

        Map<String, List<List<String>>> commentBitermMap = new HashMap<>();
        File dir = new File(project.getCommentDirPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String clazz = file.getName().split(".txt")[0];
            List<List<String>> l = new ArrayList<>();
            commentBitermMap.put(clazz, l);
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
        for (String clazz : map.keySet()) {
            if (clazz.contains("jsp")) continue;
            List<List<String>> list = map.get(clazz);

            // extract from invoked method name
            List<String> callList = clsCallMap.get(clazz);
            Map<String, Integer> invokeNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.INVOKED_METHOD);
            Set<String> invokeMethodBitermSet = codeLayerBiterms.get(clazz).get(CodeEnum.INVOKED_METHOD);
            if (callList != null && callList.size() != 0) {
                for (String call : callList) {
                    List<String> termList = Arrays.stream(call.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = combineTwoTerm(termList);
                    list.addAll(bitermList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = getNewTerm(tp);
                        invokeMethodBitermSet.add(biterm);
                        invokeNumMap.put(biterm, invokeNumMap.getOrDefault(biterm, addNum));
                    });
                }
            }

            // extract from field name
            List<String> fieldNameList = clazzFieldNameMap.get(clazz);
            Map<String, Integer> fieldNameNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.FIELD_NAME);
            Set<String> fieldNameBitermSet = codeLayerBiterms.get(clazz).get(CodeEnum.FIELD_NAME);
            if (fieldNameList != null && fieldNameList.size() != 0) {
                for (String fieldName : fieldNameList) {
                    List<String> termList = Arrays.stream(fieldName.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = combineTwoTerm(termList);
                    list.addAll(bitermList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = getNewTerm(tp);
                        fieldNameBitermSet.add(biterm);
                        fieldNameNumMap.put(biterm, fieldNameNumMap.getOrDefault(biterm, addNum));
                    });
                }
            }

            // extract from field type
            List<String> fieldTypeList = clazzFieldTypeMap.get(clazz);
            Map<String, Integer> fieldTypeNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.FIELD_TYPE);
            Set<String> fieldTypeBitermSet = codeLayerBiterms.get(clazz).get(CodeEnum.FIELD_TYPE);
            if (fieldTypeList != null && fieldTypeList.size() != 0) {
                for (String fieldType : fieldTypeList) {
                    List<String> termList = Arrays.stream(fieldType.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = combineTwoTerm(termList);
                    list.addAll(bitermList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = getNewTerm(tp);
                        fieldTypeBitermSet.add(biterm);
                        fieldTypeNumMap.put(biterm, fieldTypeNumMap.getOrDefault(biterm, addNum));
                    });
                }
            }

            // extract from param name
            List<String> paramNameList = clazzParamNameMap.get(clazz);
            Map<String, Integer> paramNameNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.PARAM_NAME);
            Set<String> paramNameTpSet = codeLayerBiterms.get(clazz).get(CodeEnum.PARAM_NAME);
            if (paramNameList != null && paramNameList.size() != 0) {
                for (String paramName : paramNameList) {
                    List<String> termList = Arrays.stream(paramName.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = combineTwoTerm(termList);
                    list.addAll(bitermList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = getNewTerm(tp);
                        paramNameTpSet.add(biterm);
                        paramNameNumMap.put(biterm, paramNameNumMap.getOrDefault(biterm, addNum));
                    });
                }
            }

            // extract from param type
            List<String> paramTypeList = clazzFieldTypeMap.get(clazz);
            Map<String, Integer> paramTypeNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.PARAM_TYPE);
            Set<String> paramTypeBitermSet = codeLayerBiterms.get(clazz).get(CodeEnum.PARAM_TYPE);
            if (paramTypeList != null && paramTypeList.size() != 0) {
                for (String paramType : paramTypeList) {
                    List<String> termList = Arrays.stream(paramType.split(" ")).collect(Collectors.toList());
                    List<List<String>> bitermList = combineTwoTerm(termList);
                    list.addAll(bitermList);
                    bitermList.stream().forEach(tp -> {
                        String biterm = getNewTerm(tp);
                        paramTypeBitermSet.add(biterm);
                        paramTypeNumMap.put(biterm, paramTypeNumMap.getOrDefault(biterm, addNum));
                    });
                }
            }

            // extract from comment
            Map<String, Integer> commentNumMap = codeLayerBitermsNumMap.get(clazz).get(CodeEnum.COMMENT);
            Set<String> commentBitermSet = codeLayerBiterms.get(clazz).get(CodeEnum.COMMENT);
            if (commentBitermMap.get(clazz) != null) {
                List<List<String>> bitermList = commentBitermMap.get(clazz);
                list.addAll(bitermList);
                bitermList.stream().forEach(tp -> {
                    String biterm = getNewTerm(tp);
                    commentBitermSet.add(biterm);
                    commentNumMap.put(biterm, commentNumMap.getOrDefault(biterm, 0) + 1);
                });
            }
        }
        return map;
    }
}
