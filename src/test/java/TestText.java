import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestText {

    @Test
    public void textHasContent() {
        Text text = new Text("Hello");
        assertTrue(text.getText().length()>0);
    }

    @Test
    public void textHasNoContent() {
        Text text = new Text("");
        assertFalse(text.getText().length()>0);
    }

}
