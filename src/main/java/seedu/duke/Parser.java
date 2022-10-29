package seedu.duke;

import seedu.duke.commands.*;


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

    private static final String COMMAND_PRINT_VERT = "view";


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
<<<<<<< HEAD
        case COMMAND_PRINT_VERT:
            return CommandPrintTimetableVertical.viewTimetable();
=======
        case COMMAND_ALLOCATE:
            return Timetable.allocateModules();
>>>>>>> v2.0-PRs
        default:
            return "Invalid Command!";
        }
    }
}
