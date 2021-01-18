package PasswordReader;

import java.io.BufferedReader;
import java.io.FileReader;

public class PasswordReader {

    private String pathName; //need to be added later
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

    public PasswordReader() {
        this.pathName = "";
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

    public String getGmailUsername() {
        return gmailUsername;
    }

    public String getGmailPassword() {
        return gmailPassword;
    }

    public String getIbmApi() {
        return ibmApi;
    }

    public String getIbmUrl() {
        return ibmUrl;
    }

    private void readFile() {
        BufferedReader bufferedReader;
        try {
            if (pathName.isEmpty()) {
                bufferedReader = new BufferedReader(new FileReader(CONFIG));
            } else {
                bufferedReader = new BufferedReader(new FileReader(pathName));
            }

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
            System.out.println("file doesn't exist. Please create one or check spelling.");
        }
    }

    public boolean isReadable() {
        BufferedReader bufferedReader;
        try {
            if (pathName.isEmpty()) {
                bufferedReader = new BufferedReader(new FileReader(CONFIG));
            } else {
                bufferedReader = new BufferedReader(new FileReader(pathName));
            }

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
