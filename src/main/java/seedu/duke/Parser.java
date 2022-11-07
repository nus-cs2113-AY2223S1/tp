package seedu.duke;

import seedu.duke.commands.CommandPrintTimetable;
import seedu.duke.commands.CommandDeleteModule;
import seedu.duke.commands.CommandInfoModule;
import seedu.duke.commands.CommandAddModule;
import seedu.duke.commands.CommandSetLesson;
import seedu.duke.timetable.Timetable;

/**
 * Class for parsing commands to start any feature operations.
 */
public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "quit";
    private static final String COMMAND_INFO = "info";
    private static final String COMMAND_SET = "set";
    private static final String COMMAND_PRINT = "print";
    private static final String COMMAND_ALLOCATE = "allocate";


    /**
     * Parses the command and initiates the command if the entry is valid and recognised.
     *
     * @param command Input from user as a command on what the program should do.
     * @param currentSemester The semester of the timetable indicated by the user at program start up.
     * @return A response string for the success or failure of the operation.
     */
    public static String parseCommand(String command, String currentSemester) {
        switch (command) {
        case COMMAND_ADD:
            return CommandAddModule.addModule(currentSemester);
        case COMMAND_LIST:
            return Timetable.listModules();
        case COMMAND_DELETE:
            return CommandDeleteModule.deleteModule();
        case COMMAND_EXIT:
            return "quit";
        case COMMAND_INFO:
            return CommandInfoModule.findModule();
        case COMMAND_SET:
            return CommandSetLesson.setLesson();
        case COMMAND_PRINT:
            // Timetable.timetableDict.print();
            return CommandPrintTimetable.viewTimetable();
        case COMMAND_ALLOCATE:
            return Timetable.allocateModules();
        default:
            return "Invalid Command!";
        }
    }
}
