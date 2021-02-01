import Result.Result;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testProcessContainsEmotionResult() {
        process.setAnalyzeOption("emotion");
        AnalysisResults result = process.connectToWatson();
        assertNotNull(result);
    }

    @Test
    public void testProcessContainsResultSyntax() {
        process.setAnalyzeOption("syntax");
        AnalysisResults result = process.connectToWatson();
        assertNotNull(result);
    }

    @Test
    public void testEmailCanBeSentForEmotion() {
        process.setAnalyzeOption("emotion");
        assertTrue(process.sendEmail());
    }

    @Test
    public void testEmailCanBeSentForSyntax() {
        process = new Process(text,destinationEmail);
        process.setAnalyzeOption("syntax");
        assertTrue(process.sendEmail());
    }

    @Test
    public void testProcessGivesAnalyzeOption() {
        process.setAnalyzeOption("emotion");
        assertTrue(process.getAnalyzeOption().equals("emotion"));
    }

    @Test
    public void testProcessPrintOutSyntaxInATableAndPrintItOutInAFile() throws IOException {
        process = new Process(text,destinationEmail);
        process.setAnalyzeOption("syntax");
        assertEquals(0, process.printSyntaxInTableFormat());
    }
}
