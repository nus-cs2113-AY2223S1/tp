package computercomponentchooser;

public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;

    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split(" ", 2);
        return lineSplit[mode];
    }

    public static boolean checkBack(String line) {
        String back = getParameter(line, COMMAND_PARAMETER).toLowerCase(); //handled cases such as "Back to main mode" being inputted
        return back.equals("back");
    }

    public static void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        switch (command) {
            case "add":
                //method to add computer parts to the build
            case "delete":
                //method to delete computer parts to the build
            case "list":
                //list all the computer parts in the build
            case "check":
                //check the compatibility of components in the build
            case "back":
                //go back to main mode
            default:
                //handle exception cases
                break;
        }
    }
}
