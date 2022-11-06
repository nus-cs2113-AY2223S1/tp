package recipeditor.parser;

import org.junit.jupiter.api.Test;
import recipeditor.exception.ParseFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseFileExceptionTest {
    @Test
    public void parseFileException_success() {
        String message = "this is a test message";
        ParseFileException e = new ParseFileException(message);
        assert e instanceof ParseFileException;
        assertEquals(message, e.getMessage());
    }
}
