package Result;

public class Emotion extends Result {
    private Float sadness;
    private Float joy;
    private Float fear;
    private Float disgust;
    private Float anger;

    public Emotion(String textTitle) {
        super(textTitle);
        this.sadness = null;
        this.joy = null;
        this.fear = null;
        this.disgust = null;
        this.anger = null;
    }

    public void setSadness(Float sadness) {
        this.sadness = sadness;
    }

    public void setJoy(Float joy) {
        this.joy = joy;
    }

    public void setFear(Float fear) {
        this.fear = fear;
    }

    public void setDisgust(Float disgust) {
        this.disgust = disgust;
    }

    public void setAnger(Float anger) {
        this.anger = anger;
    }
}
