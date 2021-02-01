import PasswordReader.PasswordReader;
import Result.*;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import org.json.simple.parser.ParseException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
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
    private String analyzeOption;

    public Process(Text texts, DestinationEmail destinationEmail, KeyPhrase keyPhrase) {
        this.texts = texts;
        this.destinationEmail = destinationEmail;
        this.keyPhrase = keyPhrase;
        this.passwordReader = new PasswordReader();
        this.analyzeOption = "";
    }

    public Process(Text texts, DestinationEmail destinationEmail) {
        this.texts = texts;
        this.destinationEmail = destinationEmail;
        this.passwordReader = new PasswordReader();
        this.analyzeOption = "";
    }

    public void setAnalyzeOption(String analyzeOption) {
        this.analyzeOption = analyzeOption;
    }

    public String getAnalyzeOption() {
        return analyzeOption;
    }

    public Text getTexts() {
        return texts;
    }

    public AnalysisResults connectToWatson() {
        IamAuthenticator authenticator = new IamAuthenticator(passwordReader.getIbmApi());
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2020-08-01", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(passwordReader.getIbmUrl());
        AnalysisResults response = null;
        Features features;
        String textArticle = this.texts.getText();

        //if emotion then do this
        if (analyzeOption.equals("emotion")) {
            List<String> targets = keyPhrase.getKeys();

            EmotionOptions emotionOptions = new EmotionOptions.Builder().targets(targets).build();

            features = new Features.Builder().emotion(emotionOptions).build();

            AnalyzeOptions parameter = new AnalyzeOptions.Builder().text(textArticle).features(features).build();

            response = naturalLanguageUnderstanding.analyze(parameter).execute().getResult();
        } else if (analyzeOption.equals("syntax")) {
            SyntaxOptionsTokens syntaxOptionsTokens = new SyntaxOptionsTokens.Builder().partOfSpeech(true).lemma(true)
                    .build();

            SyntaxOptions syntaxOptions = new SyntaxOptions.Builder().tokens(syntaxOptionsTokens).sentences(true).build();
            features = new Features.Builder().syntax(syntaxOptions).build();
            AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(textArticle).features(features)
                    .build();
            response = naturalLanguageUnderstanding.analyze(parameters).execute().getResult();

        }


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
                if (analyzeOption.equals("emotion")) {
                    int resultsEmotion = result.getEmotion();
                    if (resultsEmotion > 0) {
                        mimeMessage.setText("Your Text:\n\n" + texts.getText() + "\n\nYour result:\n\n" + result.printEmotion());
                        Transport.send(mimeMessage);
                        return true;
                    }

                } else if (analyzeOption.equals("syntax")) {
                    List<Syntax> resultSyntax = result.getSyntax();
                    if (resultSyntax.size() > 0) {
                        String resultString = printSyntaxInTableFormat(resultSyntax);
                        mimeMessage.setText("Your Text:\n\n" + texts.getText() + "\n\nYour result:\n\n" + resultString);
                        Transport.send(mimeMessage);
                        return true;
                    }
                }
            }
        } catch (ParseException | IOException | MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String printSyntaxInTableFormat(List<Syntax> result) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===============================================================\n");
        stringBuilder.append(String.format("%20s %20s %20s \n", "WORD", "PART OF SPEECH", "LEMMA"));
        stringBuilder.append("===============================================================\n\n");
        for (Syntax syntax : result) {
            stringBuilder.append(String.format("%20s %30s %30s \n", syntax.getWord(), syntax.getPartOfSpeech(), syntax.getLemma()));
            stringBuilder.append("-------------------------------------------------------------------------------------------------------------------\n");
        }
        return stringBuilder.toString();
    }

    //this function is for Testing purposes only
    public int printSyntaxInTableFormat() throws IOException {
        AnalysisResults results = connectToWatson();
        Result result = new Result(results);
        List<Syntax> resultSyntax = result.getSyntax();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===============================================================\n");
        stringBuilder.append(String.format("%20s %20s %20s \n", "WORD", "PART OF SPEECH", "LEMMA"));
        stringBuilder.append("===============================================================\n\n");
        for (Syntax syntax : resultSyntax) {
            stringBuilder.append(String.format("%20s %20s %20s \n", syntax.getWord(), syntax.getPartOfSpeech(), syntax.getLemma()));
            stringBuilder.append("---------------------------------------------------------------\n");
        }
        createFile(stringBuilder.toString());
        return 0;
    }

    //this function is for Testing purposes only
    private void createFile(String result) throws IOException {
        File test = new File("syntaxResult.txt");
        FileWriter writer = new FileWriter(test);
        writer.write(result);
        writer.close();
    }
}
