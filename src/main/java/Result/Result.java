package Result;

import com.google.gson.Gson;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Result Class
 * Class to process the result given by AnalysisResults from Watson to text, and extract the content.
 */
public class Result {
    private AnalysisResults results;
    private List<String> container;
    private List<Emotion> emotions;

    public Result(AnalysisResults results) {
        this.results = results;
        this.emotions = new ArrayList<>();
        this.container = new ArrayList<>();
        getTargetContext();
    }

    public AnalysisResults getResults() {
        return results;
    }

    public List<String> getContainer() {
        return container;
    }


    public AnalysisResults getResult() {
        return this.results;
    }

    private void getTargetContext() {
        String text = results.getEmotion().getTargets().toString();
        String[] textSplitted = text.split("},");
        container = Arrays.asList(textSplitted);
        fillUpEmotionContainerWithResult();
    }


    private void fillUpEmotionContainerWithResult() {
        Double joy, fear, sadness, disgust, anger;

        for (String json : container) {
            String newJson = json.replace("[", "").replace("]", "");
            JSONObject fullJsonString = new Gson().fromJson(newJson, JSONObject.class);
            String text = fullJsonString.get("text").toString();
            JSONObject emotionJsonObject = new Gson().fromJson(fullJsonString.get("emotion").toString(), JSONObject.class);
            joy = Double.parseDouble(emotionJsonObject.get("joy").toString());
            fear = Double.parseDouble(emotionJsonObject.get("fear").toString());
            sadness = Double.parseDouble(emotionJsonObject.get("sadness").toString());
            disgust = Double.parseDouble(emotionJsonObject.get("disgust").toString());
            anger = Double.parseDouble(emotionJsonObject.get("anger").toString());
            emotions.add(new Emotion(text, sadness, joy,fear,disgust,anger));
        }
    }

    public boolean getEmotion(String name) {
        for (Emotion emotion : emotions) {
            if (emotion.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public double getEmotionScore(String name) {
        double result = 0;
        for (Emotion emotion : emotions) {
            result = defineEmotion(emotion, name);
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
}
