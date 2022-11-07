package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {

    @Test
    public void deleteCommand_indexOutOfRangeInput_errorMessage() {
        DeleteCommand deleteCommand = new DeleteCommand(0);
        String expected = "Delete recipe index out of bound.";
        String actual = deleteCommand.execute().getMessage();
        assertEquals(expected, actual);
    }
}
