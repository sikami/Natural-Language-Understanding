import org.junit.jupiter.api.BeforeEach;

public class TestDestinationEmail {
    private DestinationEmail destinationEmail;
    private PasswordReader passwordReader = new PasswordReader("config");

    @BeforeEach
    public void start() {
        destinationEmail = new DestinationEmail(passwordReader.get)
    }
}
