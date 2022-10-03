package seedu.duke;

import seedu.duke.commands.CommandAddModule;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "quit";


    public static String parseCommand(Timetable timetable, String command) {
        String response;

        switch (command) {
        case COMMAND_ADD:
            response = CommandAddModule.addModule(timetable);
            break;
        case COMMAND_LIST:
            response = timetable.listModules();
            break;
        case COMMAND_EXIT:
            response = "quit";
            break;
        default:
            response = "Invalid Command!";
            break;
        }
        return response;
    }
}
