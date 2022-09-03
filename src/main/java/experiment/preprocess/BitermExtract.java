package experiment.preprocess;

import edu.stanford.nlp.util.CoreMap;
import experiment.enums.ProjectEnum;
import experiment.project.Project;
import util.FileIOUtil;

import java.io.File;
import java.util.List;

public class BitermExtract {
    private static Project project;
    private static StanfordNLP stanfordNlpUtil;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ProjectEnum projectEnum = ProjectEnum.MAVEN; // select project
        Class projectClass = Class.forName(projectEnum.getName());
        Project project = (Project) projectClass.newInstance();
        BitermExtract bitermExtract = new BitermExtract(project);

        if (projectEnum.equals(ProjectEnum.ITRUST) || projectEnum.equals(ProjectEnum.GANTT)) {
            bitermExtract.getUcBiterm();
        } else {
            bitermExtract.getIssueBiterm();
        }
    }

    public BitermExtract(Project project) {
        this.project = project;
        this.stanfordNlpUtil = new StanfordNLP();
    }

    private void getIssueBiterm() {
        FileIOUtil.initDirectory(project.getIssueDescripBitermDirPath());
        FileIOUtil.initDirectory(project.getIssueSummBitermDirPath());

        getBiterm(project.getUnProcessedIssueDescripDirPath(), project.getIssueDescripBitermDirPath());
        getBiterm(project.getUnProcessedIssueSummDirPath(), project.getIssueSummBitermDirPath());
    }

    private void getUcBiterm() {
        FileIOUtil.initDirectory(project.getUcTitleBitermDirPath());
        FileIOUtil.initDirectory(project.getUcPreconBitermDirPath());
        FileIOUtil.initDirectory(project.getUcMainflowBitermDirPath());
        FileIOUtil.initDirectory(project.getUcSubflowBitermDirPath());
        FileIOUtil.initDirectory(project.getUcAlterflowBitermDirPath());

        getBiterm(project.getUnProcessedUcTitleDirPath(), project.getUcTitleBitermDirPath());
        getBiterm(project.getUnProcessedUcPreconDirPath(), project.getUcPreconBitermDirPath());
        getBiterm(project.getUnProcessedUcMainflowDirPath(), project.getUcMainflowBitermDirPath());
        getBiterm(project.getUnProcessedUcSubflowDirPath(), project.getUcSubflowBitermDirPath());
        getBiterm(project.getUnProcessedUcAlterflowDirPath(), project.getUcAlterflowBitermDirPath());
    }

    private void getBiterm(String unprocessedPartDir, String outputDir) {
        File unprocessedPart = new File(unprocessedPartDir);

        if (unprocessedPart.isDirectory()) {
            for (File f : unprocessedPart.listFiles()) {
                if (!f.getName().contains(".txt"))
                    continue;
                System.out.println(f.getName());
                List<String> issueTxtLineList = FileIOUtil.readFileByLine(f.getPath());
                for (int i = 0; i < issueTxtLineList.size(); i++) {
                    String currentLine = issueTxtLineList.get(i);
                    if (!currentLine.equals("")) {
                        List<CoreMap> sentenceList = stanfordNlpUtil.splitSentence(currentLine);
                        sentenceList.stream().forEach(s -> {
                            String termPairs = stanfordNlpUtil.getTermPair(s.toString());
                            if (!termPairs.equals(""))
                                FileIOUtil.continueWriteFile(termPairs, outputDir + "/" + f.getName());
                        });
                    }
                }
            }
        }
    }
}
