package Result;

public class Syntax {
    private String word;
    private String partOfSpeech;
    private String lemma;

    public Syntax(String word, String partOfSpeech, String lemma) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.lemma = lemma;
    }

    public String toString() {
        return "Word: " + word + "      | Part Of Speech: " + partOfSpeech + "      | Lemma: " + lemma + "\n\n";
    }
}
