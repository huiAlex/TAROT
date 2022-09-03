package experiment.preprocess;

import org.tartarus.snowball.ext.EnglishStemmer;

import java.util.List;

public class Snowball {
    public static String stemming(String input) {
        StringBuilder sb = new StringBuilder();
        String words[] = input.split(" ");
        EnglishStemmer stemmer = new EnglishStemmer();
        for (String word : words) {
            stemmer.setCurrent(word);
            stemmer.stem();
            sb.append(stemmer.getCurrent());
            sb.append(" ");
        }
        return sb.toString();
    }

}
