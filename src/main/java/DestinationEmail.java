import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * DestinationEmail class.
 * Class to store the user email that is inputted from the GUI.
 * @author listya
 */
public class DestinationEmail {
    private String destinationEmail;
    private PasswordReader passwordReader;

    public DestinationEmail(String destinationEmail) {
        this.passwordReader = new PasswordReader("config");
        this.destinationEmail = destinationEmail;
    }

    private String url() {
        String urlFirstPart = "https://apilayer.net/api/check?access_key=";
        String urlSecondPart = passwordReader.getMailboxApi();
        String urlThirdPart = "&email=" + destinationEmail;
        String url = urlFirstPart + urlSecondPart + urlThirdPart;
        return url;
    }

    //read JSON object from URL




}
