package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.SearchModulesCommand;
import seedu.duke.command.ViewTimetableCommand;
import seedu.duke.command.UnknownCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_searchInput_returnNewSearchCommand() {
        assertTrue(Parser.parse("search cs2113") instanceof SearchModulesCommand);
    }

    @Test
    public void parse_addInput_returnNewAddCommand() {
        assertTrue(Parser.parse("add cs2113") instanceof AddModuleCommand);
    }

    @Test
    public void parse_deleteInput_returnNewDeleteCommand() {
        assertTrue(Parser.parse("delete cs2113") instanceof DeleteModuleCommand);
    }

    @Test
    public void parse_helpInput_returnNewHelpCommand() {
        assertTrue(Parser.parse("help") instanceof HelpCommand);
    }

    @Test
    public void parse_viewInput_returnNewViewCommand() {
        assertTrue(Parser.parse("view") instanceof ViewTimetableCommand);
    }

    @Test
    public void parse_unknownInput_returnNewUnknownCommand() {
        assertTrue(Parser.parse("I love cs2113") instanceof UnknownCommand);
    }
}
