package experiment.preprocess;

public class TextPreprocess {
    private String str;
    private String stopwordsPath = "src/main/resources/stopwords.txt";

    public TextPreprocess(String str) {
        this.str = str;
    }

    public String doReqTextProcess() {
        str = CleanUp.chararctorClean(str);
        str = CamelCase.split(str);
        str = CleanUp.lengthFilter(str, 3);
        str = CleanUp.tolowerCase(str);
        str = Stopwords.remover(str, stopwordsPath);
        str = Snowball.stemming(str);
        str = Stopwords.remover(str, stopwordsPath);
        return str;
    }

    public String doCodeTextProcess() {
        str = CleanUp.chararctorClean(str);
        str = CamelCase.split(str);
        str = CleanUp.lengthFilter(str, 3);
        str = CleanUp.tolowerCase(str);
        str = Snowball.stemming(str);
        str = Stopwords.remover(str, stopwordsPath);
        return str;
    }

}
