package experiment.preprocess.biterm;

import edu.stanford.nlp.util.CoreMap;
import experiment.enums.LayerEnum;
import experiment.enums.ProjectEnum;
import experiment.preprocess.StanfordNLP;
import experiment.project.Project;
import util.FileIOUtil;

import java.io.File;
import java.util.*;

public class ExtractReqBiterm {
    private Project project;
    private ProjectEnum projectEnum;
    private StanfordNLP stanfordNlpUtil;

    private Map<String, Map<LayerEnum, Map<String, Integer>>> reqLayerBitermNumMap;
    private Set<String> reqBitermsSet;


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ProjectEnum projectEnum = ProjectEnum.MAVEN; // select project
        Class projectClass = Class.forName(projectEnum.getName());
        Project project = (Project) projectClass.newInstance();
        ExtractReqBiterm extractReqBiterm = new ExtractReqBiterm(project);

        if (projectEnum.equals(ProjectEnum.ITRUST) || projectEnum.equals(ProjectEnum.GANTT)) {
            extractReqBiterm.extractUcBiterm();
        } else {
            extractReqBiterm.extractIssueBiterm();
        }
    }

    public ExtractReqBiterm(Project project) {
        this.project = project;
        this.projectEnum = ProjectEnum.getProject(project.getProjectName());
        this.stanfordNlpUtil = new StanfordNLP();
    }

    private void extractIssueBiterm() {
        FileIOUtil.initDirectory(project.getIssueDescripBitermDirPath());
        FileIOUtil.initDirectory(project.getIssueSummBitermDirPath());

        extractBiterm(project.getUnprocessedIssueDescripDirPath(), project.getIssueDescripBitermDirPath());
        extractBiterm(project.getUnprocessedIssueSummDirPath(), project.getIssueSummBitermDirPath());
    }

    private void extractUcBiterm() {
        FileIOUtil.initDirectory(project.getUcTitleBitermDirPath());
        FileIOUtil.initDirectory(project.getUcPreconBitermDirPath());
        FileIOUtil.initDirectory(project.getUcMainflowBitermDirPath());
        FileIOUtil.initDirectory(project.getUcSubflowBitermDirPath());
        FileIOUtil.initDirectory(project.getUcAlterflowBitermDirPath());

        extractBiterm(project.getUnprocessedUcTitleDirPath(), project.getUcTitleBitermDirPath());
        extractBiterm(project.getUnprocessedUcPreconDirPath(), project.getUcPreconBitermDirPath());
        extractBiterm(project.getUnprocessedUcMainflowDirPath(), project.getUcMainflowBitermDirPath());
        extractBiterm(project.getUnprocessedUcSubflowDirPath(), project.getUcSubflowBitermDirPath());
        extractBiterm(project.getUnprocessedUcAlterflowDirPath(), project.getUcAlterflowBitermDirPath());
    }

    private void extractBiterm(String unprocessedPartDir, String outputDir) {
        File unprocessedPart = new File(unprocessedPartDir);

        if (unprocessedPart.isDirectory()) {
            for (File f : unprocessedPart.listFiles()) {
                if (!f.getName().contains(".txt"))
                    continue;
                System.out.println(f.getName());
                List<String> issueTxtLineList = FileIOUtil.readFileByLine(f.getPath());
                for (int i = 0; i < issueTxtLineList.size(); i++) {
                    String currentLine = issueTxtLineList.get(i);
                    if (!currentLine.equals("")) {
                        List<CoreMap> sentenceList = stanfordNlpUtil.splitSentence(currentLine);
                        sentenceList.stream().forEach(s -> {
                            String termPairs = stanfordNlpUtil.getTermPair(s.toString());
                            if (!termPairs.equals(""))
                                FileIOUtil.continueWriteFile(termPairs, outputDir + "/" + f.getName());
                        });
                    }
                }
            }
        }
    }

    public Map<String, Map<LayerEnum, Map<String, Integer>>> getBitermMap( ) {
        reqBitermsSet = new HashSet<>();
        reqLayerBitermNumMap = new HashMap<>();
        File dir = new File(project.getReqPath());
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String req = file.getName().split(".txt")[0];
            Map<String, Integer> tmp = new HashMap<>();
            Map<LayerEnum, Map<String, Integer>> m = genLayerBitermNumMap();
            reqLayerBitermNumMap.put(req, m);
        }

        if (projectEnum.getName().equals(ProjectEnum.TEST.getName())
                || projectEnum.getName().equals(ProjectEnum.MAVEN.getName())
                || projectEnum.getName().equals(ProjectEnum.SEAM.getName())
                || projectEnum.getName().equals(ProjectEnum.GROOVY.getName())
                || projectEnum.getName().equals(ProjectEnum.PIG.getName())
                || projectEnum.getName().equals(ProjectEnum.DERBY.getName())
                || projectEnum.getName().equals(ProjectEnum.INFINISPAN.getName())
                || projectEnum.getName().equals(ProjectEnum.DROOLS.getName())) {
            readLayerBiterm(LayerEnum.SUMMARY, project.getIssueSummBitermDirPath());
            readLayerBiterm(LayerEnum.DESC, project.getIssueDescripBitermDirPath());
        } else {
            readLayerBiterm(LayerEnum.TITLE, project.getUcTitleBitermDirPath());
            readLayerBiterm(LayerEnum.PRE_CON, project.getUcPreconBitermDirPath());
            readLayerBiterm(LayerEnum.MAIN_FLOW, project.getUcMainflowBitermDirPath());
            readLayerBiterm(LayerEnum.SUB_FLOW, project.getUcSubflowBitermDirPath());
            readLayerBiterm(LayerEnum.ALT_FLOW, project.getUcAlterflowBitermDirPath());
        }
        return reqLayerBitermNumMap;
    }

    private void readLayerBiterm(LayerEnum layer, String bitermDirPath) {
        File dir = new File(bitermDirPath);
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".txt")) continue;
            String req = file.getName().split(".txt")[0];
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
                    String biterm1 = BitermUtil.getNewTerm(list);
                    if (layer.getName().equals(LayerEnum.TITLE.getName())
                            || layer.getName().equals(LayerEnum.SUMMARY.getName())) {
                        addNum = 1;
                    }
                    layerBitermNumMap.put(biterm1, layerBitermNumMap.getOrDefault(biterm1, 0) + addNum);

                    List<String> list2 = new LinkedList<>();
                    list2.add(biterm.split(" ")[1]);
                    list2.add(biterm.split(" ")[0]);
                    String biterm2 = BitermUtil.getNewTerm(list2);
                    if (layer.getName().equals(LayerEnum.TITLE.getName())
                            || layer.getName().equals(LayerEnum.SUMMARY.getName())) {
                        addNum = 1;
                    }
                    layerBitermNumMap.put(biterm2, layerBitermNumMap.getOrDefault(biterm2, 0) + addNum);
                }
            } // if
            reqBitermsSet.addAll(layerBitermNumMap.keySet());
        } // for
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

    public Set<String> getReqBitermsSet() {
        return reqBitermsSet;
    }
}
