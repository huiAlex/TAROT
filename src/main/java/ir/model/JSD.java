package ir.model;


import document.*;

import java.util.Collections;
import java.util.List;


public class JSD implements IRModel {
    private TermDocumentMatrix queries;
    private TermDocumentMatrix documents;

    public SimilarityMatrix Compute(ArtifactsCollection source, ArtifactsCollection target) {
        return Compute(new TermDocumentMatrix(source), new TermDocumentMatrix(target));
    }

    private SimilarityMatrix Compute(TermDocumentMatrix source, TermDocumentMatrix target) {
        List<TermDocumentMatrix> matrices = TermDocumentMatrix.Equalize(source, target);
        queries = distributionMatrix(matrices.get(0));
        documents = distributionMatrix(matrices.get(1));
        SimilarityMatrix sims = new SimilarityMatrix();

        for (int i = 0; i < matrices.get(0).NumDocs(); i++) {
            LinksList list = new LinksList();
            for (int j = 0; j < matrices.get(1).NumDocs(); j++) {
                list.add(new SingleLink(queries.getDocumentName(i), documents.getDocumentName(j),
                        documentSimilarity(queries.getDocument(i), documents.getDocument(j))));
            }
            Collections.sort(list, Collections.reverseOrder());
            for (SingleLink link : list) {
                sims.addLink(link.getSourceArtifactId(), link.getTargetArtifactId(), link.getScore());
            }
        }

        sims.setSourceTermMatrix(queries);
        sims.setTargetTermMatrix(documents);
        return sims;
    }

    private double documentSimilarity(double[] document1, double[] document2) {
        double similarity;
        double[] temp;
        temp = sumDocument(document1, document2);
        temp = mulDocument(0.5, temp);
        similarity = entropy(temp);
        similarity = similarity - (entropy(document1) + entropy(document2)) / 2;
        similarity = 1 - similarity;
        return similarity;
    }

    private TermDocumentMatrix distributionMatrix(TermDocumentMatrix matrix) {
        for (int i = 0; i < matrix.getDocIndex().size(); i++) {
            double[] document = matrix.getDocument(i);
            int sum = 0;
            for (int j = 0; j < document.length; j++) {
                sum += document[j];
            }
            for (int j = 0; j < document.length; j++) {
                matrix.getDocument(i)[j] = document[j] / sum;
            }
        }
        return matrix;
    }


    private double entropy(double[] docDistrib) {
        int i;
        double entropia = 0;
        for (i = 0; i < docDistrib.length; i++) {
            if (docDistrib[i] > 0) {
                entropia = entropia - docDistrib[i] * (Math.log(docDistrib[i]) / Math.log(2.0));
            }
        }
        return entropia;
    }

    private double[] sumDocument(double[] document1, double[] document2) {
        double[] sum = new double[document1.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = document1[i] + document2[i];
        }
        return sum;
    }

    private double[] mulDocument(double pScalar, double[] pVector) {
        double[] mul = new double[pVector.length];
        for (int i = 0; i < mul.length; i++) {
            mul[i] = pScalar * pVector[i];
        }
        return mul;
    }

    @Override
    public String getModelName() {
        return "JSD";
    }

    @Override
    public TermDocumentMatrix getTermDocumentMatrixOfQueries() {
        return null;
    }

    @Override
    public TermDocumentMatrix getTermDocumentMatrixOfDocuments() {
        return null;
    }

}
