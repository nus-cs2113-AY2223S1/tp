package seedu.duke;

import seedu.duke.commands.CommandAddModule;
import seedu.duke.commands.CommandInfoModule;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "quit";
    private static final String COMMAND_INFO = "info";


    public static String parseCommand(Timetable timetable, String command, String currentSemester) {
        String response;

        switch (command) {
        case COMMAND_ADD:
            response = CommandAddModule.addModule(timetable,currentSemester);
            break;
        case COMMAND_LIST:
            response = timetable.listModules();
            break;
        case COMMAND_EXIT:
            response = "quit";
            break;
        case COMMAND_INFO:
            response = CommandInfoModule.findModule(timetable);
            break;
        default:
            response = "Invalid Command!";
            break;
        }
        return response;
    }
}
