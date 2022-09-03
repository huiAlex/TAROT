package experiment;

import document.LinksList;
import document.SimilarityMatrix;
import document.SingleLink;

import java.util.*;

public class Result {
    public SimilarityMatrix matrix;
    protected SimilarityMatrix oracle;
    private String algorithmName;
    private String log;
    protected String model;


    public Result(SimilarityMatrix tarot_matrix, SimilarityMatrix rtm) {
        this.matrix = tarot_matrix;
        this.oracle = rtm;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public SimilarityMatrix getMatrix() {
        return matrix;
    }

    public HashMap<String, Double> getAveragePrecisionByQuery() {
        HashMap<String, Double> sourceAveragePrecision = new LinkedHashMap<>();

        for (String sourceID : oracle.sourceArtifactsIds()) {
            double sumOfPrecisions = 0.0;
            int currentLink = 0;
            int correctSoFar = 0;
            LinksList links = matrix.getLinksAboveThresholdForSourceArtifact(sourceID);
            Collections.sort(links, Collections.reverseOrder());
            for (SingleLink link : links) {
                currentLink++;
                if (oracle.isLinkAboveThreshold(sourceID, link.getTargetArtifactId())) {
                    correctSoFar++;
                    sumOfPrecisions += correctSoFar / (double) currentLink;
                }
            }//for
            sourceAveragePrecision.put(sourceID, sumOfPrecisions / oracle.getCountOfLinksAboveThresholdForSourceArtifact(sourceID));
        }
        return sourceAveragePrecision;
    }

    public double getMeanAveragePrecisionByQuery() {
        HashMap<String, Double> sourceAveragePrecision = getAveragePrecisionByQuery();
        double sum = 0.0;
        for (String sourceArtifact : sourceAveragePrecision.keySet()) {
            sum += sourceAveragePrecision.get(sourceArtifact);
        }
        return (sum / sourceAveragePrecision.size());
    }


    public double getAveragePrecisionByRanklist() {
        double sumOfPrecisions = 0.0;
        int currentLink = 0;
        int correctSoFar = 0;
        LinksList links = matrix.allLinks();
        Collections.sort(links, Collections.reverseOrder());
        for (SingleLink link : links) {
            currentLink++;
            if (oracle.isLinkAboveThreshold(link.getSourceArtifactId(), link.getTargetArtifactId())) {
                correctSoFar++;
                sumOfPrecisions += correctSoFar / (double) currentLink;
            }
        }
        double averagePrecision = sumOfPrecisions / oracle.allLinks().size();
        return averagePrecision;
    }


    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }


}


