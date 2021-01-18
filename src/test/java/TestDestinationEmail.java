import PasswordReader.PasswordReader;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDestinationEmail {
    private DestinationEmail destinationEmail;
    private PasswordReader passwordReader = new PasswordReader("config");

    @BeforeEach
    public void start() {
        destinationEmail = new DestinationEmail(passwordReader.getGmailUsername());
    }

    @Test
    public void testIfEmailIsValid() throws IOException, ParseException {
        assertTrue(destinationEmail.isValid());
    }

    @Test
    public void testIfEmailHasCorrectSyntax() throws IOException, ParseException {
        destinationEmail = new DestinationEmail("he+mai.com");
        assertFalse(destinationEmail.isValid());
    }

    @Test
    public void testIfEmailIsInvalidWhenGivenWrongEmail() throws IOException, ParseException {
        destinationEmail = new DestinationEmail("totototomimiinjonositi@gmail.com");
        assertFalse(destinationEmail.isValid());
    }
}
