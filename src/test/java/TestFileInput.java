import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileInput {

    private FileInput file;

    @BeforeEach
    public void start() {
        file = new FileInput("file");
    }

    @Test
    public void testIfFileExist() {
        File newFile = new File(file.getPath());
        assertTrue(newFile.exists());
    }
}
