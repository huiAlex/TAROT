package util;

import document.SimilarityMatrix;
import document.SingleLink;

public class ReadRtmUtil {
    public static SimilarityMatrix createSimilarityMatrix(String path) {
        SimilarityMatrix sims = new SimilarityMatrix();
        if (!path.endsWith(".txt"))
            throw new IllegalArgumentException("not a txt file");

        String contents = FileIOUtil.readFile(path);
        String[] lines = contents.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
//            SingleLink link = new SingleLink(tokens[0].trim(), tokens[1].trim(), Double.valueOf(tokens[2].trim()));
            SingleLink link = new SingleLink(tokens[0].trim(), tokens[1].trim(), 1.0);

            sims.addLink(link.getSourceArtifactId(), link.getTargetArtifactId(), link.getScore());
        }
        return sims;
    }
}
