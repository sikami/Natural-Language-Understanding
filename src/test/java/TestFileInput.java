import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileInput {

    private FileInput file;

    @BeforeEach
    public void start() {
        file = new FileInput("file");
    }

    @Test
    public void testIfFileExist() {
        assertTrue(file.isExist());
    }

    @Test
    public void testIfFileCanBeParsed() {
        assertEquals(96, file.getText().length());
    }
}
