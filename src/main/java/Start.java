import algorithm.IR_Only;
import document.TextDataset;
import experiment.enums.IREnum;
import experiment.enums.ProjectEnum;
import experiment.Result;
import experiment.project.Project;
import ir.IR;
import ir.IR_TAROT;
import ir.model.IRModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Start {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        long startTime = System.currentTimeMillis();

        Class projectClass = Class.forName(ProjectEnum.ITRUST.getName()); // select project
        Class irModelClass = Class.forName(IREnum.JSD.getModel()); // select IR model
        boolean lambdaFlag = true; // use global weight lambda
        boolean thetaFlag = true; // use local weighr theta

        Project project = (Project) projectClass.newInstance();
        System.setProperty("project", project.getProjectName());
        IRModel irModel = (IRModel) irModelClass.newInstance();

        TextDataset textDataset = new TextDataset(project.getReqPath(), project.getClassDirPath(), project.getRtmClassPath());

        Map<String, Result> resultMap = new HashMap<>();

        // IR-ONLY
        IR ir = new IR();
        Result result_ir = ir.compute(textDataset, irModel, new IR_Only());
        resultMap.put(result_ir.getAlgorithmName(), result_ir);

        // IR-ONLY_TAROT
        IR_TAROT tarot = new IR_TAROT();
        Result result_tarot = tarot.compute(project, textDataset, irModel, new IR_Only(), lambdaFlag, thetaFlag);
        result_tarot.setAlgorithmName("IR-ONLY_TAROT");
        resultMap.put(result_tarot.getAlgorithmName(), result_tarot);

        for (String resultName : resultMap.keySet()) {
            Result result = resultMap.get(resultName);
            double ApValue = result.getAveragePrecisionByRanklist();
            String AP = String.format("%.2f", ApValue * 100);
            double MapValue = result.getMeanAveragePrecisionByQuery();
            String MAP = String.format("%.2f", MapValue * 100);

            System.out.println(project.getProjectName()
                    + "   " + irModel.getModelName()
                    + "   " + resultName
                    + "   " + "AP:" + AP
                    + "   " + "MAP:" + MAP);

            List<String> apAndMapList = new ArrayList<>();
            apAndMapList.add(AP);
            apAndMapList.add(MAP);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("time cost:" + (endTime - startTime) * 1.0 / 1000 / 60);
    }
}
