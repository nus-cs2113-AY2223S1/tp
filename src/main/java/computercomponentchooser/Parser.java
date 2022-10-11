package computercomponentchooser;

import computercomponentchooser.exceptions.UnknownCommandException;
import computercomponentchooser.exceptions.DuplicateBuildException;

import java.util.ArrayList;

import static computercomponentchooser.ComputerComponentChooser.buildManager;

public class Parser {

    static final int COMMAND_PARAMETER = 0;
    static final int NAME_PARAMETER = 1;

    public String editBuild;

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
            case "edit":
                break;
            case "list":
                System.out.println(buildManager.toString());
                break;
            case "add":
                name = getParameter(line, NAME_PARAMETER);
                Build newBuild = new Build(name);
                buildManager.addBuild(newBuild);
                break;
            //case "view":
            case "delete":
                name = getParameter(line, NAME_PARAMETER);
                buildManager.deleteBuild(name);
                break;
            case "back":
                System.out.println("Back to main mode.");
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (DuplicateBuildException e) {
            System.out.println(e.getMessage());
        }
    }
}
