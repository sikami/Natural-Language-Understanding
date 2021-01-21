import Result.Result;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResult {
    private Process process = new Process(new Text("Orange is lovely. It contains alot of vitamin C."), new
            DestinationEmail("listya.tapp@gmail.com"), new KeyPhrase("orange"));
    private AnalysisResults results = process.connectToWatson();
    private Result result = new Result(results);

    @Test
    public void testResultContainsAnalysisResults() {
        assertTrue(!result.getResult().toString().isEmpty());
    }

    @Test
    public void testResultContain1TargetEmotion() {

        assertEquals(1, result.getContainer().size());
    }

    @Test
    public void testEmotionReturnsTextCorrectly() {
        assertTrue(result.getEmotion("orange"));

    }
//    @Test
//    public void testResultContainJoyWithCorrectValue() {
//        //"joy": 0.89565
//        assertEquals(0.89565, result.getEmotion("joy"));
//
//
//    }


}
