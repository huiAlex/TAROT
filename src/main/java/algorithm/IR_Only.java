package algorithm;

import document.SimilarityMatrix;
import document.TextDataset;

public class IR_Only implements ALGO {
    @Override
    public SimilarityMatrix improve(SimilarityMatrix matrix, TextDataset textDataset) {
        return matrix;
    }

    @Override
    public String getAlgorithmName() {
        return "IR-ONLY";
    }

}
