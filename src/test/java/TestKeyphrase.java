import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKeyphrase {
    @Test
    public void testIfKeyphraseNotNull() {
        KeyPhrase keyPhrase = new KeyPhrase("hello");
        assertTrue(keyPhrase.getKeyphrase().length()>0);
    }

}
