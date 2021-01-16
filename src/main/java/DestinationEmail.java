import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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

    public boolean isValid() throws IOException, ParseException {
        String line = "";
        Scanner scanner = new Scanner(new URL(url()).openStream());
        while(scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();

        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(line);

        if (data.get("format_valid").equals(true) && data.get("smtp_check").equals(true)) {
            return true;
        }
        return false;
    }
}
