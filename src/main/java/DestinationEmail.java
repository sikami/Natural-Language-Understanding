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

    //check if destination email is valid
    private String url() {
        String urlFirstPart = "https://apilayer.net/api/check?access_key=";
        String urlSecondPart = passwordReader.getMailboxApi();
        String urlThirdPart = "&email=" + destinationEmail;
        String url = urlFirstPart + urlSecondPart + urlThirdPart;
        return url;
    }


}
