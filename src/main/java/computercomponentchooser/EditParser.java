package computercomponentchooser;

public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;

    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split(" ", 2);
        return lineSplit[mode];
    }

    public static boolean checkBack(String line) {
        String back = getParameter(line, COMMAND_PARAMETER).toLowerCase(); 
        return back.equals("back");
    }

    public static void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        switch (command) {
        case "add":
        case "delete":
        case "list":
        case "check":
        case "back":
        default:
            break;
        }
    }
}

