package algorithm;

import document.SimilarityMatrix;
import document.TextDataset;
import javafx.util.Pair;

import java.util.List;

public interface ALGO {

    public SimilarityMatrix improve(SimilarityMatrix matrix, TextDataset textDataset);

    public String getAlgorithmName();

}
