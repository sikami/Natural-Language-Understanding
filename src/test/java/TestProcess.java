import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProcess {
    private Process process;
    private Text text;
    private KeyPhrase keyPhrase;
    private DestinationEmail destinationEmail;


    @BeforeEach
    public void start() {
        keyPhrase = new KeyPhrase("orange");
        text = new Text("I love orange, orange is good for your health and is antiseptic.");
        destinationEmail = new DestinationEmail("listya.tapp@gmail.com");
        process = new Process(text,destinationEmail,keyPhrase);

    }
    @Test
    public void testProcessContainsResult() {
        AnalysisResults result = process.connectToWatson();
        assertNotNull(result);
    }

    @Test
    public void testEmailCanBeSent() {
        assertTrue(process.sendEmail());
    }
}
