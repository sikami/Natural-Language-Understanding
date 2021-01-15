import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * KeyPhrase class.
 * Class to store the keyphrases inputted by user to process.
 * @author listya
 */
public class KeyPhrase {
    private String keywords;
    private List<String> keys;

    public KeyPhrase(String keywords) {
        this.keywords = keywords;
        this.keys = new ArrayList<>();
    }

    public List<String> getKeys() {
        keyphrases();
        return keys;
    }

    private void keyphrases() {
        if (!keywords.contains(" ") && !keywords.contains(",")) {
            keys.add(keywords);
        } else {
            String newKey = keywords.replaceAll(" ", "");
            String[] keysSplitted = newKey.split(",");
            Arrays.stream(keysSplitted).forEach(key -> keys.add(key));
        }
    }

}
