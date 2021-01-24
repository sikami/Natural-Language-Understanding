import PasswordReader.PasswordReader;
import Result.Result;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import org.json.simple.parser.ParseException;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
        this.passwordReader = new PasswordReader();
    }

    public Text getTexts() {
        return texts;
    }

    public AnalysisResults connectToWatson() {
        IamAuthenticator authenticator = new IamAuthenticator(passwordReader.getIbmApi());
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2020-08-01", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(passwordReader.getIbmUrl());

        String textArticle = this.texts.getText();

        List<String> targets = keyPhrase.getKeys();

        EmotionOptions emotionOptions = new EmotionOptions.Builder().targets(targets).build();

        Features features = new Features.Builder().emotion(emotionOptions).build();

        AnalyzeOptions parameter = new AnalyzeOptions.Builder().text(textArticle).features(features).build();

        AnalysisResults response = naturalLanguageUnderstanding.analyze(parameter).execute().getResult();
        return response;
    }

    public boolean sendEmail() {
        //set up properties for Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(passwordReader.getGmailUsername(), passwordReader.getGmailPassword());
            }
        });

        //compose email
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            if (destinationEmail.isValid()) {
                mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(destinationEmail.getDestinationEmail())) );
                mimeMessage.setSubject("Your Natural Language Understanding result");
                AnalysisResults results = connectToWatson();

                Result result = new Result(results);
                mimeMessage.setText("Your Text:\n\n" + texts.getText() + "\n\nYour result:\n\n" + result.printEmotion());
                Transport.send(mimeMessage);
                return true;
            }
        } catch (ParseException | IOException | MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
