import PasswordReader.PasswordReader;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

import java.util.List;

/**
 * Process class.
 * Class where all processing is happening. This includes connection to Watson, sending email via Gmail API, etc.
 * @author listya
 */
public class Process {
    private Text texts;
    private DestinationEmail destinationEmail;
    private KeyPhrase keyPhrase;
    private PasswordReader passwordReader;

    public Process(Text texts, DestinationEmail destinationEmail, KeyPhrase keyPhrase) {
        this.texts = texts;
        this.destinationEmail = destinationEmail;
        this.keyPhrase = keyPhrase;
    }

    public Text getTexts() {
        return texts;
    }

    public void connectToWatson() {
        IamAuthenticator authenticator = new IamAuthenticator(passwordReader.getIbmApi());
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2020-18-01", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(passwordReader.getIbmUrl());

        String textArticle = this.texts.getText();

        List<String> targets = keyPhrase.getKeys();

        EmotionOptions emotionOptions = new EmotionOptions.Builder().targets(targets).build();

        Features features = new Features.Builder().emotion(emotionOptions).build();

        AnalyzeOptions parameter = new AnalyzeOptions.Builder().text(textArticle).features(features).build();

        AnalysisResults response = naturalLanguageUnderstanding.analyze(parameter).execute().getResult();
        System.out.println(response);
    }
}
