package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.exceptions.InvalidCommentException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {

    @Test
    void getUserCommand_emptyInput_expectException() {
        String input = "";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_missingUniversityParameterForCreateCommand_expectException() {
        String input = "/create";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_missingUniversityParameterForAddCommand_expectException() {
        String input = "/add";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_missingUniversityParameterForDeleteCommand_expectException() {
        String input = "/delete";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_missingParameterForViewCommand_expectException() {
        String input = "/view";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_additionalParametersForCreateCommand_expectException() {
        String input = "/create u/UCLA extra";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_unknownCommandInput_expectException() {
        String input = "/notACommand";

        assertThrows(InvalidUserCommandException.class, () -> CommandParser.getUserCommand(input));
    }

    @Test
    void getUserCommand_addCommand_returnsNormally() throws InvalidUserCommandException,
            ModuleNotFoundException, InvalidModuleException, UniversityNotFoundException, InvalidCommentException {
        String input = "/add u/UCLA m/CS3230";

        Command addCommand = CommandParser.getUserCommand(input);
        assertEquals(addCommand.getCommandType(), CommandType.ADD);
        assertEquals(addCommand.getModuleCode(), "CS3230");
        assertEquals(addCommand.getUniversityName(), "UCLA");
    }

    @Test
    void getUserCommand_deleteCommandUniversity_returnsNormally() throws InvalidUserCommandException,
            ModuleNotFoundException, InvalidModuleException, UniversityNotFoundException, InvalidCommentException {
        String input = "/delete u/UCLA";

        Command deleteCommand = CommandParser.getUserCommand(input);
        assertEquals(deleteCommand.getCommandType(), CommandType.DELETE);
        assertEquals(deleteCommand.getUniversityName(), "UCLA");
    }

    @Test
    void getUserCommand_deleteCommandModule_returnsNormally() throws InvalidUserCommandException,
            ModuleNotFoundException, InvalidModuleException, UniversityNotFoundException, InvalidCommentException {
        String input = "/delete u/UCLA m/CS3230";

        Command deleteCommand = CommandParser.getUserCommand(input);
        assertEquals(deleteCommand.getCommandType(), CommandType.DELETE);
        assertEquals(deleteCommand.getModuleCode(), "CS3230");
        assertEquals(deleteCommand.getUniversityName(), "UCLA");
    }
}
