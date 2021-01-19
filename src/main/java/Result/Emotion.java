package Result;

public class Emotion {
    private String text;
    private double sadness;
    private double joy;
    private double fear;
    private double disgust;
    private double anger;

    public Emotion(String text, double sadness, double joy, double fear, double disgust, double anger) {
        this.text = text;
        this.sadness = sadness;
        this.joy = joy;
        this.fear = fear;
        this.disgust = disgust;
        this.anger = anger;
    }
}
