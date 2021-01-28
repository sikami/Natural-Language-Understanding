import Result.Result;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.sun.source.tree.AssertTree;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResult {
    private Process process = new Process(new Text("Orange is lovely. It contains alot of vitamin C."), new
            DestinationEmail("listya.tapp@gmail.com"), new KeyPhrase("orange"));
    private AnalysisResults results = process.connectToWatson();
    private Result result = new Result(results);

    @Test
    public void testResultContainsAnalysisResults() {
        process.setAnalyzeOption("emotion");
        assertTrue(!result.getResult().toString().isEmpty());
    }

    @Test
    public void testResultContain1TargetEmotion() {
        process.setAnalyzeOption("emotion");
        assertEquals(1, result.getEmotion().size());
    }

    @Test
    public void testResultContainJoyCorrectly() {
        process.setAnalyzeOption("emotion");
        assertEquals(0.89565, result.getEmotion("orange", "joy"));
    }

    @Test
    public void testResultContain9Result() {
        process.setAnalyzeOption("syntax");
        assertEquals(9, result.getSyntax().size());
    }

}
