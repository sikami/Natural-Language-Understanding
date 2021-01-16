import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordReader {
    private PasswordReader passwordReader;
    private File file;

    @BeforeEach
    public void start() {
        passwordReader = new PasswordReader("config");
        file = new File(passwordReader.getUrl());
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
