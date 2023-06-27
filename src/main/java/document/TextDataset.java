package document;


import util.FileIOUtil;
import util.ReadRtmUtil;

public class TextDataset {
    private ArtifactsCollection sourceCollection;
    private ArtifactsCollection targetCollection;

    private SimilarityMatrix rtm;

    /**
     * @param sourceDirPath: uc files' directory path
     * @param targetDirPath: class code files' directory path
     */
    public TextDataset(String sourceDirPath, String targetDirPath, String rtmPath) {
        System.out.println(targetDirPath);
        this.setSourceCollection(FileIOUtil.getCollections(sourceDirPath, ".txt"));
        this.setTargetCollection(FileIOUtil.getCollections(targetDirPath, ".txt"));
        this.setRtm(ReadRtmUtil.createSimilarityMatrix(rtmPath));
    }

    public TextDataset(ArtifactsCollection sourceCollection, ArtifactsCollection targetCollection, SimilarityMatrix rtm) {
        this.setSourceCollection(sourceCollection);
        this.setTargetCollection(targetCollection);
        this.setRtm(rtm);
    }

    public ArtifactsCollection getSourceCollection() {
        return sourceCollection;
    }

    public void setSourceCollection(ArtifactsCollection sourceCollection) {
        this.sourceCollection = sourceCollection;
    }

    public ArtifactsCollection getTargetCollection() {
        return targetCollection;
    }

    public void setTargetCollection(ArtifactsCollection targetCollection) {
        this.targetCollection = targetCollection;
    }

    public SimilarityMatrix getRtm() {
        return rtm;
    }

    public void setRtm(SimilarityMatrix rtm) {
        this.rtm = rtm;
    }
}
