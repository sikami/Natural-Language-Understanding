import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    private String filePath;
    private Text textDocument;

    public FileInput(String filePath) {
        this.filePath = filePath;
        this.textDocument = new Text("");
    }

    private void readFiles() {

    }

    public String getPath() {
        return filePath;
    }
}
