import java.io.File;

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
        this.textDocument = new Text("");
    }

    private void readFiles() {

    }

    public boolean isExist() {
        File newFile = new File(filePath);
        if(newFile.exists()) {
            return true;
        }
        return false;
    }
}
