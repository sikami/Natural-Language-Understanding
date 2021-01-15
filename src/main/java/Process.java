/**
 * Process class.
 * Class where all processing is happening. This includes connection to Watson, sending email via Gmail API, etc.
 * @author listya
 */
public class Process {
    private Text texts;
    private DestinationEmail destinationEmail;
    private KeyPhrase keyPhrase;

    public Process(Text texts, DestinationEmail destinationEmail, KeyPhrase keyPhrase) {
        this.texts = texts;
        this.destinationEmail = destinationEmail;
        this.keyPhrase = keyPhrase;
    }

    public Text getTexts() {
        return texts;
    }

}
