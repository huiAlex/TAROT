package ir;

import algorithm.ALGO;
import document.SimilarityMatrix;
import document.SingleLink;
import document.TextDataset;
import experiment.Result;
import ir.model.IRModel;

public class IR {

    public static Result compute(TextDataset textDataset, IRModel irModel, ALGO algorithm) {
        Result result = null;
        SimilarityMatrix matrix_improve = new SimilarityMatrix();

        try {
            SimilarityMatrix similarityMatrix = irModel.Compute(textDataset.getSourceCollection(),
                    textDataset.getTargetCollection());
            SimilarityMatrix matrix = algorithm.improve(similarityMatrix, textDataset);
            for (SingleLink link : matrix.allLinks()) {
                if (similarityMatrix.isLinkAboveThreshold(link.getSourceArtifactId(), link.getTargetArtifactId())) {
                    matrix_improve.addLink(link.getSourceArtifactId(), link.getTargetArtifactId(), link.getScore());
                } else {
                    matrix_improve.addLink(link.getSourceArtifactId(), link.getTargetArtifactId(), 0.0);
                }
            }
            result = new Result(matrix_improve, textDataset.getRtm());
            result.setAlgorithmName(algorithm.getAlgorithmName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setModel(irModel.toString());
        return result;
    }
}
