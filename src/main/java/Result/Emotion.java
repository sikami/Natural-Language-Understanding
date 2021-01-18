package Result;

public class Emotion extends Result {
    private Double sadness;
    private Double joy;
    private Double fear;
    private Double disgust;
    private Double anger;

    public Emotion(String textTitle) {
        super(textTitle);
        this.sadness = null;
        this.joy = null;
        this.fear = null;
        this.disgust = null;
        this.anger = null;
    }

    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }

    public void setJoy(Double joy) {
        this.joy = joy;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }

    public void setAnger(Double anger) {
        this.anger = anger;
    }
}
