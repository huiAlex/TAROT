package algorithm;

import document.SimilarityMatrix;
import document.TextDataset;

public interface ALGO {

    public SimilarityMatrix improve(SimilarityMatrix matrix, TextDataset textDataset);

    public String getAlgorithmName();

}
