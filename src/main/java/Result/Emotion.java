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

    public String getText() {
        return text;
    }

    public double getSadness() {
        return sadness;
    }

    public double getJoy() {
        return joy;
    }

    public double getFear() {
        return fear;
    }

    public double getDisgust() {
        return disgust;
    }

    public double getAnger() {
        return anger;
    }

    public String toString() {
        return "Emotions to be collected from the keyword: " + this.text +
                "\n\nJoy: " + joy + "\nSadness: " + sadness + "\nFear: " + fear
                + "\nDisgust: " + disgust + "\nAnger: " + anger +"\n\n";
    }

}
