package computercomponentchooser;

import computercomponentchooser.exceptions.UnknownCommandException;

import java.util.ArrayList;

public class Parser {

    static final int COMMAND_PARAMETER = 0;
    static final int NAME_PARAMETER = 1;

    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split(" ", 2);
        return lineSplit[mode];
    }

    static boolean checkBye(String line) {
        String checkLine = line.toLowerCase();
        return line.equals("bye");
    }

    static boolean checkEdit(String line) {
        String edit = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        return edit.equals("edit");
    }

    public static void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER);
        String name;
        try {
            switch (command) {
            case "bye":
            case "list":
                // no parsing needed
                break;
            case "add":
            case "edit":
            case "view":
            case "delete":
                name = getParameter(line, NAME_PARAMETER);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
