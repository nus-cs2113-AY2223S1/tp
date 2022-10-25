package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GetModuleCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ViewTimetableCommand;
import seedu.duke.command.SelectSlotCommand;
import seedu.duke.command.SelectSemesterCommand;
import seedu.duke.command.SearchModuleCommand;
import seedu.duke.command.ImportCommand;
import seedu.duke.command.ExportCommand;
import seedu.duke.exceptions.YamomException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_searchInput_returnNewSearchCommand() throws YamomException {
        assertTrue(Parser.parse("search /code cs2113") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /code Cs2040c") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /code Cs") instanceof SearchModuleCommand);
        assertTrue(Parser.parse("search /title software engineering") instanceof SearchModuleCommand);
    }

    @Test
    public void parse_getInput_returnNewGetCommand() throws YamomException {
        assertTrue(Parser.parse("get cs2113") instanceof GetModuleCommand);
    }

    @Test
    public void parse_addInput_returnNewAddCommand() throws YamomException {
        assertTrue(Parser.parse("add cs2113") instanceof AddModuleCommand);
    }

    @Test
    public void parse_deleteInput_returnNewDeleteCommand() throws YamomException {
        assertTrue(Parser.parse("delete cs2113") instanceof DeleteModuleCommand);
    }

    @Test
    public void parse_viewInput_returnNewViewCommand() throws Exception {
        assertTrue(Parser.parse("view") instanceof ViewTimetableCommand);
        assertTrue(Parser.parse("view /simple") instanceof ViewTimetableCommand);
        assertTrue(Parser.parse("view /fancy") instanceof ViewTimetableCommand);
    }

    @Test
    public void parse_helpInput_returnNewHelpCommand() throws YamomException {
        assertTrue(Parser.parse("help") instanceof HelpCommand);
    }

    @Test
    public void parse_selectSlotInput_returnNewSelectSlotCommand() throws YamomException {
        assertTrue(Parser.parse("select /module cs2113 /type tutorial /code 4") instanceof SelectSlotCommand);
    }

    @Test
    public void parse_exitInput_returnNewExitCommand() throws YamomException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_selectSemesterInput_returnNewSelectSemesterCommand() throws YamomException {
        assertTrue(Parser.parse("semester 1") instanceof SelectSemesterCommand);
        assertTrue(Parser.parse("semester 3") instanceof SelectSemesterCommand);
        assertTrue(Parser.parse("semester special term 1") instanceof SelectSemesterCommand);
    }

    @Test
    public void parse_exportInput_returnNewExportCommand() throws YamomException {
        assertTrue(Parser.parse("export") instanceof ExportCommand);
    }

    @Test
    public void parse_importInput_returnNewImportCommand() throws YamomException {
        assertTrue(Parser.parse("import " +
                "https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01&CS2113=LEC:1,TUT:4")
                instanceof ImportCommand);
    }

    @Test
    public void parse_unknownInput_throwYamomException() {
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("YAMOM DA BEST"));
        assertEquals("Error! \tCannot process the command", exception.getMessage());

    }

    @Test
    public void parse_incorrectSingleWordInput_throwYamomException() {
        String expectedErrorMessage = "Error! \t0 arguments expected";
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("help me"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("bye bye"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("export to nusmods"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("list everything"));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parse_incorrectModuleRelatedInput_throwYamomException() {
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("add"));
        assertEquals("Error! \tWrong format, should be: add [MODULE_CODE]" + System.lineSeparator()
                + "Your command is incomplete.", exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("delete me"));
        assertEquals("Error! \tWrong format, should be: delete [MODULE_CODE]" + System.lineSeparator()
                + "Module is invalid! Please enter a valid module code." + System.lineSeparator()
                + "Each module of study has a unique module code consisting of a two- " + System.lineSeparator()
                + "or three-letter prefix that generally denotes the discipline," + System.lineSeparator()
                + "and four digits, the first of which indicates the level of the module"
                + System.lineSeparator() + "(e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).",
                exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("add me in"));
        assertEquals("Error! \tWrong format, should be: add [MODULE_CODE]" + System.lineSeparator()
                + "Unknown command, try again.", exception.getMessage());
    }

    @Test
    public void parse_incorrectSelectSemesterInput_throwYamomException() {
        String expectedErrorMessage = "Error! \tWrong format, should be: semester [SEMESTER_SELECTED]"
                + System.lineSeparator() + "Not a valid semester.";
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("semester 5"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("semester special term 3"));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parse_incorrectViewTimetableInput_throwYamomException() {
        String expectedErrorMessage = "Error! \tUnknown command. Maybe you meant \"view\".";
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("view timetable"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> Parser.parse("view fancy"));
        assertEquals("Error! \tUnknown command. Maybe you forgot a \"/\".", exception.getMessage());
    }
}
