import PasswordReader.PasswordReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordReader {
    private PasswordReader passwordReader;
    private File file;

    @BeforeEach
    public void start() {
        passwordReader = new PasswordReader();
        file = new File(passwordReader.getPathName());
    }

    @Test
    public void testIfFileExist() {
        assertTrue(file.exists());
    }

    @Test
    public void testIfFileIsReadable() {
        assertTrue(passwordReader.isReadable());
    }
}
