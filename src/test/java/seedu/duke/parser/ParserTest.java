package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.RemoveModuleCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.InfoCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.TimetableCommand;
import seedu.duke.command.SelectSlotCommand;
import seedu.duke.command.SelectSemesterCommand;
import seedu.duke.command.SearchModuleCommand;
import seedu.duke.command.ImportCommand;
import seedu.duke.command.ExportCommand;
import seedu.duke.exceptions.YamomException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertTrue(Parser.parse("info cs2113") instanceof InfoCommand);
    }

    @Test
    public void parse_addInput_returnNewAddCommand() throws YamomException {
        assertTrue(Parser.parse("add cs2113") instanceof AddModuleCommand);
    }

    @Test
    public void parse_deleteInput_returnNewDeleteCommand() throws YamomException {
        assertTrue(Parser.parse("remove cs2113") instanceof RemoveModuleCommand);
    }

    @Test
    public void parse_viewInput_returnNewTimetableCommand() throws Exception {
        assertTrue(Parser.parse("timetable") instanceof TimetableCommand);
        assertTrue(Parser.parse("timetable /simple") instanceof TimetableCommand);
        assertTrue(Parser.parse("timetable /fancy") instanceof TimetableCommand);
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
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
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
        assertTrue(Parser.parse("import "
                + "https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01&CS2113=LEC:1,TUT:4")
                instanceof ImportCommand);
    }

    @Test
    public void parse_unknownInput_throwYamomException() {
        YamomException exception = assertThrows(YamomException.class, () -> Parser.parse("YAMOM DA BEST"));
        assertEquals("Error! \tCannot process the command", exception.getMessage());

    }

    @Test
    public void singleWordCommandError_incorrectSingleWordInput_throwYamomException() {
        String expectedErrorMessage = "Error! \t0 arguments expected";
        YamomException exception = assertThrows(YamomException.class,
            () -> Parser.singleWordCommandError("help me".split("\\s+")));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class,
            () -> Parser.singleWordCommandError("bye bye".split("\\s+")));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class,
            () -> Parser.singleWordCommandError("export to nusmods".split("\\s+")));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class,
            () -> Parser.singleWordCommandError("list everything".split("\\s+")));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void moduleRelatedCommandError_incorrectModuleRelatedInput_throwYamomException() {
        YamomException exception = assertThrows(YamomException.class,
            () -> Parser.moduleRelatedCommandError("add".split("\\s+"),
            "Wrong format, should be: add [MODULE_CODE]"));
        assertEquals("Error! \tWrong format, should be: add [MODULE_CODE]" + System.lineSeparator()
                + "Your command is incomplete.", exception.getMessage());

        exception = assertThrows(YamomException.class,
            () -> Parser.moduleRelatedCommandError("delete cs1".split("\\s+"),
            "Wrong format, should be: delete [MODULE_CODE]"));
        assertEquals("Error! \tWrong format, should be: delete [MODULE_CODE]" + System.lineSeparator()
                + "Module is invalid! Please enter a valid module code." + System.lineSeparator()
                + "Each module of study has a unique module code consisting of a two- " + System.lineSeparator()
                + "or three-letter prefix that generally denotes the discipline," + System.lineSeparator()
                + "and four digits, the first of which indicates the level of the module"
                + System.lineSeparator() + "(e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).",
                exception.getMessage());

        exception = assertThrows(YamomException.class,
            () -> Parser.moduleRelatedCommandError("add me in".split("\\s+"),
            "Wrong format, should be: add [MODULE_CODE]"));
        assertEquals("Error! \tWrong format, should be: add [MODULE_CODE]" + System.lineSeparator()
                + "Unknown command, try again.", exception.getMessage());
    }

    @Test
    public void semesterParser_incorrectSelectSemesterInput_correctReturnValue() {
        assertFalse(Parser.isValidSemester(new String[]{"semester", "5"}));
        assertFalse(Parser.isValidSpecialTerm(new String[]{"semester", "5"}));
        assertFalse(Parser.isValidSemester("semester special term 3".split(" ")));
        assertFalse(Parser.isValidSpecialTerm("semester special term 3".split(" ")));
    }

    @Test
    public void semesterParser_correctSelectSemesterInput_correctReturnValue() {
        assertTrue(Parser.isValidSemester(new String[]{"semester", "1"}));
        assertTrue(Parser.isValidSemester(new String[]{"semester", "2"}));
        assertTrue(Parser.isValidSemester(new String[]{"semester", "3"}));
        assertTrue(Parser.isValidSpecialTerm("semester special term 1".split(" ")));
        assertTrue(Parser.isValidSpecialTerm("semester st1".split(" ")));
        assertTrue(Parser.isValidSpecialTerm("semester st2".split(" ")));
    }

    @Test
    public void parseParams_inputContainingBackslash_returnMap() {
        Map<String, String> actualMap = Parser.parseParams("test /key1 value1 /key2 value2");
        assertEquals(2, actualMap.size());
        assertTrue(actualMap.containsKey("key1"));
        assertTrue(actualMap.get("key1").equals("value1"));
        assertTrue(actualMap.containsKey("key2"));
        assertTrue(actualMap.get("key2").equals("value2"));
    }

    @Test
    public void parseParams_inputContainingNoBackslash_returnMap() {
        Map<String, String> actualMap = Parser.parseParams("test ");
        assertTrue(actualMap.isEmpty());
    }
}
