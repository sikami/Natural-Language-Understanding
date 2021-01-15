/**
 * Process class.
 * Class where all processing is happening. This includes connection to Watson, sending email via Gmail API, etc.
 * @author listya
 */
public class Process {
    private Text texts;

    public Process(Text texts) {
        this.texts = texts;
    }

    public Text getTexts() {
        return texts;
    }

}
