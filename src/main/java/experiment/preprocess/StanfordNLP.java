package experiment.preprocess;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class StanfordNLP {

    private static Properties props;
    private static StanfordCoreNLP pipeline;
    private static Set<String> excludeDependSet;

    public StanfordNLP() {
        this.props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");    // Annotators
        this.pipeline = new StanfordCoreNLP(props);

        excludeDependSet = new HashSet<>();
        excludeDependSet.add("punct");
        excludeDependSet.add("aux");
        excludeDependSet.add("det");
        excludeDependSet.add("mark");
    }

    public String getTermPair(String input) {
        StringBuilder sb = new StringBuilder();
        Annotation document = new Annotation(input);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            Map<String, String> termMap = new HashMap<>();
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String term = token.get(CoreAnnotations.TextAnnotation.class);
                if (term.matches("^[a-zA-Z]+$")) {
                    TextPreprocess textPreprocess = new TextPreprocess(term);
                    String processedTerm = textPreprocess.doReqTextProcess().trim();
                    if (!processedTerm.equals(""))
                        termMap.put(term, processedTerm);
                }
            }
            // term dependency graph
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            for (SemanticGraphEdge edge : dependencies.edgeListSorted()) {
                String relation = edge.getRelation().toString().split(":")[0];
//                System.out.println(edge.getSource() + " " + edge.getTarget() + " " + relation);

                if (!excludeDependSet.contains(relation)) {
                    String[] ary1 = edge.getSource().toString().split("/");
                    String[] ary2 = edge.getTarget().toString().split("/");
                    String term1 = ary1[0];
                    String pos1 = ary1[1];
                    String term2 = ary2[0];
                    String pos2 = ary2[1];

                    if (isLegalPOS(pos1, pos2)) {
                        if (termMap.containsKey(term1) && termMap.containsKey(term2)) {
                            if (termMap.get(term1).equals(termMap.get(term2)))
                                continue;

                            String tp = "";
                            tp = termMap.get(term2) + " " + termMap.get(term1) + ":" + relation;
                            sb.append(tp + "\n");

                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    private boolean isLegalPOS(String pos1, String pos2) {
        boolean isLegal = false;
        if (pos1.contains("NN") || pos1.contains("JJ") || pos1.contains("VB")) {
            if (pos2.contains("NN") || pos2.contains("JJ") || pos2.contains("VB")) {
                isLegal = true;
            }
        }
        return isLegal;
    }

    public List<CoreMap> splitSentence(String input) {
        Annotation document = new Annotation(input);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        return sentences;
    }

}
