package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidCommandTest {

    @Test
    public void defaultInvalidCommand_success() {
        String actual = new InvalidCommand(InvalidCommand.INVALID_MESSAGE).execute().getMessage();
        String expected = InvalidCommand.INVALID_MESSAGE;
        assertEquals(actual, expected);
    }
}
