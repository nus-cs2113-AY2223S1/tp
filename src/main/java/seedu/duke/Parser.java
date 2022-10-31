package seedu.duke;

import seedu.duke.commands.CommandPrintTimetable;
import seedu.duke.commands.CommandSetLesson;
import seedu.duke.commands.CommandAddModule;
import seedu.duke.commands.CommandDeleteModule;
import seedu.duke.commands.CommandInfoModule;


public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "quit";
    private static final String COMMAND_INFO = "info";
    private static final String COMMAND_SET = "set";
    private static final String COMMAND_PRINT = "print";
    //For now
    private static final String COMMAND_ALLOCATE = "allocate";


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
            return CommandPrintTimetable.printTimetable();
        case COMMAND_ALLOCATE:
            return Timetable.allocateModules();
        default:
            return "Invalid Command!";
        }
    }
}
