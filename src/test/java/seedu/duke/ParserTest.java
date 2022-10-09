package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void getArgumentList_FiveSeparators_SixArguments() {
        String userInput = "arg 1 /arg 2 /arg3 / arg4/  arg 5/arg6";
        String[] argumentList = Parser.getArgumentList(userInput);
        assertEquals("arg 1 ", argumentList[0]);
        assertEquals("arg 2 ", argumentList[1]);
        assertEquals("arg3 ", argumentList[2]);
        assertEquals(" arg4", argumentList[3]);
        assertEquals("  arg 5", argumentList[4]);
        assertEquals("arg6", argumentList[5]);
    }
}
