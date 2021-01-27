import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * FileInput Class.
 * Class to store the String file path where the user keep their text file in their local dir.
 * @author listya
 */
public class FileInput {
    private String filePath;
    private Text textDocument;

    public FileInput(String filePath) {
        this.filePath = filePath;
        this.textDocument = readFiles();
    }

    private Text readFiles() {
        if (isExist()) {
            try {
                Scanner scanner = new Scanner(Paths.get(filePath));
                StringBuilder sb = new StringBuilder();
                while(scanner.hasNextLine()) {
                    String words = scanner.nextLine();
                    sb.append(words);
                }
                return new Text(sb.toString());
            } catch (IOException e) {

            }
        }
        return null;
    }

    public boolean isExist() {
        File newFile = new File(filePath);
        if(newFile.exists()) {
            return true;
        }
        return false;
    }

    public String getText() {
        return textDocument.getText();
    }
}
