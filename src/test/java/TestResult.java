import Result.Result;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResult {
    private Process process = new Process(new Text("Orange is lovely. It contains alot of vitamin C."), new
            DestinationEmail("listya.tapp@gmail.com"), new KeyPhrase("orange"));
    private AnalysisResults results = process.connectToWatson();

    @Test
    public void testResultContainsAnalysisResults() {
        Result result = new Result(results);
        assertTrue(!result.getResult().toString().isEmpty());

    }
}
