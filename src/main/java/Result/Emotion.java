package Result;

public class Emotion extends Result {
    private String sadness;
    private String joy;
    private String fear;
    private String disgust;
    private String anger;

    public Emotion(String textTitle) {
        super(textTitle);
        this.sadness = "";
        this.joy = "";
        this.fear = "";
        this.disgust = "";
        this.anger = "";
    }

}
