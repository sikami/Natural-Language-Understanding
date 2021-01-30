package Result;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Result Class
 * Class to process the result given by AnalysisResults from Watson to text, and extract the content.
 */
public class Result {
    private AnalysisResults results;
    private List<Emotion> emotions;
    private List<Syntax> syntaxes;

    public Result(AnalysisResults results) {
        this.results = results;
        this.emotions = new ArrayList<>();
        this.syntaxes = new ArrayList<>();
    }

    public AnalysisResults getResult() {
        return this.results;
    }


    private void fillUpEmotionsWithResult() {
        Double joy, fear, sadness, disgust, anger;
        // need to iterate JSON array
        String json = results.getEmotion().toString();
        JsonObject jsonObject =  new Gson().fromJson(json, JsonObject.class);
        JsonArray jsonElements = jsonObject.getAsJsonArray("targets");
        for (int i = 0; i < jsonElements.size(); i++) {
            JsonObject obj = (JsonObject) jsonElements.get(i);
            String text = obj.get("text").toString();
            joy = obj.get("emotion").getAsJsonObject().get("joy").getAsDouble();
            fear = obj.get("emotion").getAsJsonObject().get("fear").getAsDouble();
            sadness = obj.get("emotion").getAsJsonObject().get("sadness").getAsDouble();
            disgust = obj.get("emotion").getAsJsonObject().get("disgust").getAsDouble();
            anger = obj.get("emotion").getAsJsonObject().get("anger").getAsDouble();
            emotions.add(new Emotion(text, sadness,joy,fear,disgust,anger));
        }
    }

    public List<Syntax> getSyntax() {
        fillUpSyntaxesWithResult();
        return syntaxes;
    }

    private void fillUpSyntaxesWithResult() {
        String word, partOfSpeech, lemma = "";
        JsonObject jsonObject = new Gson().fromJson(results.toString(), JsonObject.class);
        JsonObject json = (JsonObject) jsonObject.get("syntax");
        JsonElement jsonElement = json.get("tokens");
        JsonArray jsonA = jsonElement.getAsJsonArray();
        for (JsonElement jsonElements : jsonA) {
            word = jsonElements.getAsJsonObject().get("text").toString();
            partOfSpeech = jsonElements.getAsJsonObject().get("part_of_speech").toString();
            try {
                lemma = jsonElements.getAsJsonObject().get("lemma").toString();
            } catch (NullPointerException e) {
                lemma = "n/a";
            }
            syntaxes.add(new Syntax(word,partOfSpeech,lemma));
        }
    }
    
    public int getEmotion() {
        fillUpEmotionsWithResult();
        return emotions.size();
    }

    public double getEmotion(String text, String emotionToSearch) {
        fillUpEmotionsWithResult();
        double result = 0;
        for (Emotion emotion : emotions) {
            if (emotion.getText().contains(text)) {
                result = defineEmotion(emotion, emotionToSearch);
            }
        }
        return result;
    }

    private double defineEmotion(Emotion emotion, String name) {
        switch (name) {
            case "joy":
                return emotion.getJoy();
            case "anger":
                return emotion.getAnger();
            case "fear":
                return emotion.getFear();
            case "sadness":
                return emotion.getSadness();
            case "disgust":
                return emotion.getDisgust();
        }
        return 0;
    }

    public String printEmotion() {
        StringBuilder stringBuilder = new StringBuilder();

        emotions.forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    public String printSyntax() {
        StringBuilder stringBuilder = new StringBuilder();
        syntaxes.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    
}
