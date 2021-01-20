import Result.Result;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResult {
    private Process process = new Process(new Text("Orange is lovely. It contains alot of vitamin C. Apple is good for you too. An apple a day keeps doctor away"), new
            DestinationEmail("listya.tapp@gmail.com"), new KeyPhrase("orange, apple"));
    private AnalysisResults results = process.connectToWatson();
    private Result result = new Result(results);

    @Test
    public void testResultContainsAnalysisResults() {
        assertTrue(!result.getResult().toString().isEmpty());
    }

    @Test
    public void testResultContain2TargetEmotion() {
         assertEquals(2, result.getTargetContext());
    }
}
