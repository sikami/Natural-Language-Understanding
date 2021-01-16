import java.io.BufferedReader;
import java.io.FileReader;

public class PasswordReader {

    private String pathName;
    private String mailboxApi;
    private String gmailUsername;
    private String gmailPassword;
    private String ibmApi;
    private String ibmUrl;
    private final String CONFIG = "config";

    public PasswordReader(String pathName) {
        this.pathName = pathName;
        this.mailboxApi = "";
        this.gmailPassword = "";
        this.gmailUsername = "";
        this.ibmApi = "";
        this.ibmUrl = "";
        readFile();
    }

    public String getPathName() {
        return pathName;
    }

    public String getMailboxApi() {
        return mailboxApi;
    }

    private void readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG));
            String input = "";
            String[] key;

            while((input = bufferedReader.readLine()) != null) {
                key = input.split(" ");
                if (input.contains("MAILBOX")) {
                    mailboxApi = key[1];
                } else if (input.contains("USERNAME")) {
                    gmailUsername = key[1];
                } else if (input.contains("PASS")) {
                    gmailPassword = key[1];
                } else if (input.contains("IBM.APIKEY")) {
                    ibmApi = key[1];
                } else if (input.contains("IBM.URL")) {
                    ibmUrl = key[1];
                }
            }

        } catch (Exception e) {
            System.out.println("Config file doesn't exist. Please create one.");
        }
    }

    public boolean isReadable() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG));
            String read = bufferedReader.readLine();
            if (read.length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Config file is not readable");
        }
        return false;
    }

}
