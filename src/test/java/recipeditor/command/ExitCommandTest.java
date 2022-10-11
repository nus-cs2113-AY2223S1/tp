package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {

    @Test
    public void isExitShouldWork() {
        Command command = new ViewCommand(0);
        assertFalse(ExitCommand.isExit(command));
    }
}
