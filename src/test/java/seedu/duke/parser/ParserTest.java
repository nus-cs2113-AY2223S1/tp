package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.SearchModuleCommand;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.ViewTimetableCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_searchInput_returnNewSearchCommand() throws Exception {
        assertTrue(Parser.parse("search /code cs2113") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /code Cs2040c") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /code Cs") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /title software engineering") instanceof SearchModuleCommand);
    }

    @Test
    public void parse_addInput_returnNewAddCommand() throws Exception {
        assertTrue(Parser.parse("add cs2113") instanceof AddModuleCommand);
    }

    @Test
    public void parse_deleteInput_returnNewDeleteCommand() throws Exception {
        assertTrue(Parser.parse("delete cs2113") instanceof DeleteModuleCommand);
    }

    @Test
    public void parse_helpInput_returnNewHelpCommand() throws Exception {
        assertTrue(Parser.parse("help") instanceof HelpCommand);
    }

    /* TODO: catch exception instead of checking for instance
    @Test
    public void parse_wrongHelpInput_returnNewUnknownCommand() {
        assertTrue(Parser.parse("help me") instanceof UnknownCommand);
    }
    */

    @Test
    public void parse_viewInput_returnNewViewCommand() throws Exception {
        assertTrue(Parser.parse("view") instanceof ViewTimetableCommand);
    }

    @Test
    public void parse_exitInput_returnNewExitCommand() throws Exception {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    /* TODO: catch exception instead of checking for instance
    @Test
    public void parse_unknownInput_returnNewUnknownCommand() {
        assertTrue(Parser.parse("") instanceof UnknownCommand);
        assertTrue(Parser.parse("I love cs2113") instanceof UnknownCommand);
    }
    */

}
