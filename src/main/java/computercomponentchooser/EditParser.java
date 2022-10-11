package computercomponentchooser;

import computercomponentchooser.exceptions.UnknownCommandException;


public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;

    public static String buildName;

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

        String content;
        Build editBuild;
        try {
            switch (command) {
            case "add":
                content = getParameter(line, NAME_PARAMETER);
                editBuild = BuildManager.getBuild(buildName);
                editBuild.addContents(content);
                Ui.printLine();
                System.out.println("You have added " + content);
                Ui.printLine();
                break;
            case "delete":
                content = getParameter(line, NAME_PARAMETER);
                editBuild = BuildManager.getBuild(buildName);
                editBuild.deleteContents(content);
                Ui.printLine();
                System.out.println("You have removed " + content);
                Ui.printLine();
                break;

            case "list":
                editBuild = BuildManager.getBuild(buildName);
                Ui.printLine();
                System.out.println("Computer parts for " + buildName + ":");
                System.out.println(editBuild.toString());
                Ui.printLine();
                break;
            case "edit":
                buildName = getParameter(line, NAME_PARAMETER);
                Ui.printLine();
                System.out.println("You are now editing " + buildName);
                Ui.printLine();
                break;
            case "check":
            case "back":
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}

