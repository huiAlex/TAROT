package ir.util;

import document.TermDocumentMatrix;
import experiment.enums.LayerEnum;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeightUtil {
    public static Double getTheta(boolean isUcType, Map<LayerEnum, Set<String>> reqLayerBiterms,
                                  Set<String> clsBitermSet, TermDocumentMatrix TF, double[] IDF) {
        double theta = 0.0;

        if (isUcType) {
            Set<String> bitermInSf = reqLayerBiterms.get(LayerEnum.SUB_FLOW);
            Set<String> bitermInMf = reqLayerBiterms.get(LayerEnum.MAIN_FLOW);
            Set<String> bitermInAf = reqLayerBiterms.get(LayerEnum.ALT_FLOW);
            Set<String> bitermInTitle = reqLayerBiterms.get(LayerEnum.TITLE);

            Set<String> titleCodeSharedBitermSet = genSetIntersect(bitermInTitle, clsBitermSet);
            Set<String> mfCodeSharedBitermSet = genSetIntersect(bitermInMf, clsBitermSet);
            Set<String> sfCodeSharedBitermSet = genSetIntersect(bitermInSf, clsBitermSet);
            Set<String> afCodeSharedBitermSet = genSetIntersect(bitermInAf, clsBitermSet);

            double theta1 = 0.0, theta2 = 0.0, theta3 = 0.0, theta4 = 0.0;

            if (titleCodeSharedBitermSet.size() > 0) {
                double titleBitermIDF = sumOfIDF(IDF, TF, bitermInTitle);
                double titleCodeBitermIDF = sumOfIDF(IDF, TF, titleCodeSharedBitermSet);
                theta1 = (titleCodeBitermIDF) / (titleBitermIDF);
            }

            if (mfCodeSharedBitermSet.size() > 0) {
                double mfBitermIDF = sumOfIDF(IDF, TF, bitermInMf);
                double mfCodeBitermIDF = sumOfIDF(IDF, TF, mfCodeSharedBitermSet);
                theta2 = (mfCodeBitermIDF) / (mfBitermIDF);
            }

            if (sfCodeSharedBitermSet.size() > 0) {
                double sfBitermIDF = sumOfIDF(IDF, TF, bitermInSf);
                double sfCodeBitermIDF = sumOfIDF(IDF, TF, sfCodeSharedBitermSet);
                theta3 = sfCodeBitermIDF / sfBitermIDF;
            }

            if (afCodeSharedBitermSet.size() > 0) {
                double afBitermIDF = sumOfIDF(IDF, TF, bitermInAf);
                double afCodeBitermIDF = sumOfIDF(IDF, TF, afCodeSharedBitermSet);
                theta4 = afCodeBitermIDF / afBitermIDF;
            }

            theta = theta1 * 0.4
                    + theta2 * 0.3
                    + theta3 * 0.2
                    + theta4 * 0.1
            ;
        } else {
            Set<String> bitermInSumm = reqLayerBiterms.get(LayerEnum.SUMMARY);
            Set<String> bitermInDesc = reqLayerBiterms.get(LayerEnum.DESC);
            Set<String> summCodeSharedBitermSet = genSetIntersect(bitermInSumm, clsBitermSet);
            Set<String> descripCodeSharedBitermSet = genSetIntersect(bitermInDesc, clsBitermSet);

            double theta1 = 0.0, theta2 = 0.0;

            if (summCodeSharedBitermSet.size() > 0) {
                double summBitermIDF = sumOfIDF(IDF, TF, bitermInSumm);
                double summCodeBitermIDF = sumOfIDF(IDF, TF, summCodeSharedBitermSet);
                theta1 = summCodeBitermIDF / summBitermIDF;
            }
            if (descripCodeSharedBitermSet.size() > 0) {
                double descripBitermIDF = sumOfIDF(IDF, TF, bitermInDesc);
                double descripCodeBitermIDF = sumOfIDF(IDF, TF, descripCodeSharedBitermSet);
                theta2 = descripCodeBitermIDF / descripBitermIDF;
            }
            theta += 0.6 * theta1 + 0.4 * theta2;
        }

        if (theta >= 1) theta = 0.9999;

        return theta;
    }

    public static Double getLambda(Set<String> reqBitermSet, Set<String> clsBitermSet,
                                   TermDocumentMatrix TF, double[] IDF) {
        double lambda = 0.0;

        Set<String> sharedTpSet = genSetIntersect(reqBitermSet, clsBitermSet);
        if (sharedTpSet.size() > 0) {
            double sharedBitermIDF = sumOfIDF(IDF, TF, sharedTpSet);
            double reqBitermIDF = sumOfIDF(IDF, TF, reqBitermSet);
            double codeBitermIDF = sumOfIDF(IDF, TF, clsBitermSet);
            lambda = (0.5 * sharedBitermIDF / reqBitermIDF) + (0.5 * sharedBitermIDF / codeBitermIDF);
        }
        return lambda;
    }

    public static Double sumOfIDF(double[] IDF, TermDocumentMatrix TF, Set<String> sharedBiterms) {
        double sum = 0.0;
        for (String biterm : sharedBiterms) {
            int index = TF.getTermIndex(biterm);
            sum += IDF[index];
        }
        return sum;
    }

    public static Set<String> genSetUnion(Set<String> set1, Set<String> set2) {
        Set<String> set = new HashSet<>(set1 == null ? new HashSet<>() : set1);
        set.addAll(set2);
        return set;
    }

    public static Set<String> genSetIntersect(Set<String> set1, Set<String> set2) {
        Set<String> set = new HashSet<>(set1 == null ? new HashSet<>() : set1);
        set.retainAll(set2);
        return set;
    }
}
