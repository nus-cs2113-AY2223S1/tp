package computercomponentchooser;

import java.util.ArrayList;

public class Parser {

    static final int COMMAND_PARAMETER = 0;
    static final int NAME_PARAMETER = 1;

    private static String getParameters(String line, int mode) {
        String[] lineSplit = line.split(" ");
        return lineSplit[mode];
    }

    static boolean checkBye(String line) {
        return line.equals("bye");
    }

    public static void parse(String line) {
        // buildList is a temporary parameter for testing purposes
        String command = getParameters(line, COMMAND_PARAMETER);
        String name;
        switch (command) {
        case "bye":
        case "list":
            // no parsing needed
            break;
        case "add":
        case "edit":
        case "view":
        case "delete":
            name = getParameters(line, NAME_PARAMETER);
            break;
        default:
            // insert unrecognised command exception
            break;
        }
    }
}
