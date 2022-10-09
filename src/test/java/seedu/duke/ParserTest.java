package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_ExitInput_ExitCommand() {
        String userInput = "exit";
        Command command = Parser.parse(userInput);
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void parse_RandomInput_InvalidCommand() {
        String userInput = "ding6 Dong66";
        Command command = Parser.parse(userInput);
        assertTrue(command instanceof InvalidCommand);
    }

}
