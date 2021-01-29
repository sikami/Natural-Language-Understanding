import Result.*;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestResult {
    private Process process = new Process(new Text("Orange is lovely. It contains alot of vitamin C."), new
            DestinationEmail("listya.tapp@gmail.com"), new KeyPhrase("orange"));
    private AnalysisResults results = process.connectToWatson();
    private Result result = new Result(results);

    @Test
    public void testResultContainsAnalysisResults() {
        process.setAnalyzeOption("emotion");
        results = process.connectToWatson();
        result = new Result(results);
        assertTrue(!result.getResult().toString().isEmpty());
    }

    @Test
    public void testResultContain1TargetEmotion() {
        process.setAnalyzeOption("emotion");
        results = process.connectToWatson();
        result = new Result(results);
        assertEquals(1, result.getEmotion());
    }

    @Test
    public void testResultContainJoyCorrectly() {
        process.setAnalyzeOption("emotion");
        results = process.connectToWatson();
        result = new Result(results);
        assertEquals(0.89565, result.getEmotion("orange", "joy"));
    }

    @Test
    public void testResultContainSyntaxes() {
        Process process = new Process(new Text("with great power comes great responsibility"), new
                DestinationEmail("listya.tapp@gmail.com"));
        process.setAnalyzeOption("syntax");
        results = process.connectToWatson();
        assertNotNull(results.toString());
    }

    @Test
    public void testResultContain6Syntaxes() {
        Process process = new Process(new Text("with great power comes great responsibility"), new
                DestinationEmail("listya.tapp@gmail.com"));
        process.setAnalyzeOption("syntax");
        results = process.connectToWatson();
        Result result = new Result(results);
        List<Syntax> syntaxes = result.getSyntax();
        System.out.println(syntaxes.size());

    }

}
