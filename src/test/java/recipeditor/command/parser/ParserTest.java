package recipeditor.command.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import recipeditor.command.ExitCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.ViewCommand;
import recipeditor.parser.Parser;

public class ParserTest {
    @Test
    void parserShouldReturnList() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("LiST") instanceof ListCommand);
    }

    @Test
    void parserShouldReturnView() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("VIEw 1") instanceof ViewCommand);
    }

    @Test
    void parserShouldReturnExit() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("Exit") instanceof ExitCommand);
    }

}
