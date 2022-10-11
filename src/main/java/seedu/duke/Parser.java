package seedu.duke;

import seedu.duke.commands.CommandAddModule;
import seedu.duke.commands.CommandDeleteModule;
import seedu.duke.commands.CommandInfoModule;
import seedu.duke.commands.CommandSetLesson;


public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "quit";
    private static final String COMMAND_INFO = "info";
    private static final String COMMAND_SET = "set";


    public static String parseCommand(Timetable timetable, String command, String currentSemester) {

        String response;

        switch (command) {
        case COMMAND_ADD:
            response = CommandAddModule.addModule(timetable,currentSemester);
            break;
        case COMMAND_LIST:
            response = timetable.listModules();
            break;
        case COMMAND_DELETE:
            response = CommandDeleteModule.deleteModule(timetable);
            break;
        case COMMAND_EXIT:
            response = "quit";
            break;
        case COMMAND_INFO:
            response = CommandInfoModule.findModule(timetable);
            break;
        case COMMAND_SET:
            response = CommandSetLesson.setLesson(timetable);
            break;
        default:
            response = "Invalid Command!";
            break;
        }
        assert !response.equals(null) : "response to user should exist and not null";
        return response;
    }
}
