package document;

import java.io.Serializable;

public class Artifact implements Serializable {
    public String id;
    public String text;

    public Artifact(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
