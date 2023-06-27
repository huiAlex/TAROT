package experiment.preprocess.biterm;

import document.Artifact;
import document.ArtifactsCollection;
import document.TextDataset;
import experiment.enums.CodeEnum;
import experiment.enums.LayerEnum;
import experiment.enums.ProjectEnum;
import experiment.project.Project;

import java.util.*;

public class ConsensualBiterm {
    private ProjectEnum projectEnum;
    private Project project;

    private Map<String, Map<LayerEnum, Map<String, Integer>>> reqLayerBitermNumMap;

    private Map<String, Map<CodeEnum, Map<String, Integer>>> codeLayerBitermsNumMap;

    private Map<String, Map<String, Integer>> reqConsensualBitermsMap;
    private Map<String, Map<String, Integer>> codeConsensualBitermsMap;

    private ExtractCodeBiterm extractCodeBiterm;
    private ExtractReqBiterm extractReqBiterm;

    public ConsensualBiterm(Project project) {
        this.project = project;
        this.projectEnum = ProjectEnum.getProject(project.getProjectName());
        this.extractCodeBiterm = new ExtractCodeBiterm(project);
        this.extractReqBiterm = new ExtractReqBiterm(project);

        this.codeLayerBitermsNumMap = new HashMap<>();
        this.reqLayerBitermNumMap = new HashMap<>();
        this.reqConsensualBitermsMap = new HashMap<>();
        this.codeConsensualBitermsMap = new HashMap<>();

        extractBiterms();
    }

    /**
     * step1: extract biterms from code
     * step2: get biterms from req
     * step3: filter to get consensual biterms
     */

    private void extractBiterms() {
        codeLayerBitermsNumMap = extractCodeBiterm.getCodeLayerBitermsNumMap();
        reqLayerBitermNumMap = extractReqBiterm.getBitermMap();
        reqConsensualBitermsMap = getConsensualReqBitermMap(reqLayerBitermNumMap, extractCodeBiterm.getClsBitermsSet()); // step2
        codeConsensualBitermsMap = getConsensualClassBitermMap(codeLayerBitermsNumMap, extractReqBiterm.getReqBitermsSet()); // step3
    }

    private Map<String, Map<String, Integer>> getConsensualReqBitermMap(Map<String, Map<LayerEnum, Map<String, Integer>>> reqLayerBitermNumMap,
                                                                               Set<String> clsBitermSet) {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for (String req : reqLayerBitermNumMap.keySet()) {
            Map<String, Integer> map = new HashMap<>();
            Map<LayerEnum, Map<String, Integer>> layerBitermMap = reqLayerBitermNumMap.get(req);
            for (LayerEnum layer : layerBitermMap.keySet()) {
                Map<String, Integer> bitermNumMap = layerBitermMap.get(layer);
                Iterator<String> iterator = bitermNumMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String biterm = iterator.next();
                    if (!clsBitermSet.contains(biterm)) {
                        iterator.remove();
                    } else {
                        map.put(biterm, map.getOrDefault(biterm, 0) + bitermNumMap.get(biterm));
                    }
                }
            }
            result.put(req, map);
        }
        return result;
    }


    private Map<String, Map<String, Integer>> getConsensualClassBitermMap(
            Map<String, Map<CodeEnum, Map<String, Integer>>> codeLayerBitermNumMap,
            Set<String> reqBitermSet) {

        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (String cls : codeLayerBitermNumMap.keySet()) {
            Map<String, Integer> map = new HashMap<>();
            Map<CodeEnum, Map<String, Integer>> layerBitermMap = codeLayerBitermNumMap.get(cls);
            for (CodeEnum layer : layerBitermMap.keySet()) {
                Map<String, Integer> bitermNumMap = layerBitermMap.get(layer);
                Iterator<String> iterator = bitermNumMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String biterm = iterator.next();
                    if (!reqBitermSet.contains(biterm)) {
                        iterator.remove();
                    } else {
                        map.put(biterm, map.getOrDefault(biterm, 0) + bitermNumMap.get(biterm));
                    }
                }
            }
            result.put(cls, map);
        }

        return result;
    }

    public TextDataset updateTextDataSet(TextDataset textDataset, Map<String, Map<String, Integer>> reqBitermMap,
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


    private Map<String, String> getAddString(Map<String, Map<String, Integer>> map) {
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

    private Map<String, String> getAddString_code(Map<String, Map<String, Integer>> map) {
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


    public Map<String, Map<String, Integer>> getReqBitermsMap() {
        return reqConsensualBitermsMap;
    }

    public Map<String, Map<String, Integer>> getCodeBitermsMap() {
        return codeConsensualBitermsMap;
    }

    public Map<String, Map<LayerEnum, Map<String, Integer>>> getReqLayerBitermNumMap() {
        return reqLayerBitermNumMap;
    }

    public Map<String, Map<CodeEnum, Map<String, Integer>>> getCodeLayerBitermNumMap() {
        return codeLayerBitermsNumMap;
    }
}
