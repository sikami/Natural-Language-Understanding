import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKeyphrase {
    private KeyPhrase keyPhrase;

    @BeforeEach
    public void start() {
        keyPhrase = new KeyPhrase("Hello");
    }

    @Test
    public void testIfKeyphraseNotNull() {
        assertEquals(1, keyPhrase.getKeys().size());
    }

    @Test
    public void testIfKeyphraseMoreThanOneSeparatedByComma() {
        keyPhrase = new KeyPhrase("Hello, you");
        assertEquals(2,keyPhrase.getKeys().size());
    }

}
