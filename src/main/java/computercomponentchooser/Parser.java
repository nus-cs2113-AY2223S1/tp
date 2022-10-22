package computercomponentchooser;

import computercomponentchooser.exceptions.BlankStringException;
import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.exceptions.UnknownCommandException;
import computercomponentchooser.exceptions.UnlistedBuildException;
import computercomponentchooser.exceptions.NegativeNumberException;
import computercomponentchooser.export.ExportCsv;
import computercomponentchooser.export.ExportText;

import java.io.IOException;

import static computercomponentchooser.ComputerComponentChooser.storage;

public class Parser {

    static final int COMMAND_PARAMETER = 0;
    static final int NAME_PARAMETER = 1;

    private final BuildManager buildManager;

    public Parser(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split("/", 2);
        assert mode >= 0 && mode < 2;
        return lineSplit[mode];
    }

    static boolean checkBye(String line) {
        String checkLine = line.toLowerCase();
        return checkLine.equals("bye");
    }

    static boolean checkEdit(String line) {
        String edit = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        return edit.equals("edit");
    }

    public void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER);
        try {
            switch (command) {
            case "bye":
                // fallthrough
            case "edit":
                break;
            case "list":
                mainParseList();
                break;
            case "add":
                mainParseAdd(line);
                break;
            case "view":
                mainParseView(line);
                break;
            case "delete":
                mainParseDelete(line);
                break;
            case "back":
                mainParseBack();
                break;
            case "export":
                mainParseExport();
                break;
            case "exportCSV":
                mainParseExportCsv();
                break;
            case "filter":
                mainParseFilter(line);
                break;
            case "find":
                mainParseFind(line);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | DuplicateBuildException | UnlistedBuildException | IOException
                 | NegativeNumberException | BlankStringException e) {
            System.out.println(e.getMessage());
            Ui.printLine();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            Ui.printLine();
        }
    }

    private void mainParseFind(String line) throws BlankStringException {
        String searchTerm = EditParser.getParameter(line, 1);
        Ui.printLine();
        buildManager.findBuilds(searchTerm);
        Ui.printLine();
    }

    private void mainParseFilter(String line) throws NumberFormatException, UnknownCommandException,
            NegativeNumberException {
        String filterType = EditParser.getParameter(line, 1);
        String lowestNumber = "";
        String highestNumber = "";
        Ui.printLine();
        if (!filterType.equals("price") || !filterType.equals("power") || !filterType.equals("compatibility")) {
            throw new UnknownCommandException();
        }// guard clause

        if (!filterType.equals("compatibility")) {
            lowestNumber = EditParser.getParameter(line, 2);
            highestNumber = EditParser.getParameter(line, 3);
        }
        buildManager.filterBuilds(filterType, lowestNumber, highestNumber);
        Ui.printLine();
    }

    private void mainParseAdd(String line) throws DuplicateBuildException, BlankStringException {
        Build newBuild;
        String name;
        name = getParameter(line, NAME_PARAMETER);
        if (name.isBlank()) {
            throw new BlankStringException();
        }

        newBuild = new Build(name);
        buildManager.addBuild(newBuild);
        Ui.printLine();
        System.out.println("You have added " + name);
        Ui.printLine();
        try {
            storage.saveBuild(buildManager);
        } catch (Exception e) {
            System.out.println("Error saving builds");
        }
    }

    private void mainParseView(String line) throws UnlistedBuildException {
        String name;
        name = getParameter(line, NAME_PARAMETER);
        Ui.printLine();
        if (!BuildManager.doesBuildExist(name)) {
            throw new UnlistedBuildException();
        }
        System.out.print(buildManager.getBuild(name).toString());
        Ui.printLine();
    }

    private void mainParseDelete(String line) throws UnlistedBuildException {
        String name;
        Build newBuild;
        name = getParameter(line, NAME_PARAMETER);
        newBuild = new Build(name);
        buildManager.deleteBuild(name, newBuild);
        try {
            storage.deleteBuild(name, buildManager);
        } catch (Exception e) {
            System.out.println("Error saving builds");
        }
        Ui.printLine();
        System.out.println("You have removed " + name);
        Ui.printLine();
    }

    private void mainParseList() {
        Ui.printLine();
        if (buildManager.getBuilds().size() == 0) {
            System.out.println("You have no builds");
        }
        System.out.println("Your current builds:");
        System.out.print(buildManager);
        Ui.printLine();
    }

    private void mainParseBack() {
        Ui.printLine();
        System.out.println("Back to main mode.");
        Ui.printLine();
    }

    private void mainParseExport() throws IOException {
        Ui.printLine();
        System.out.println("Exporting builds...");
        ExportText.exportAllBuildsText(buildManager);
        Ui.printLine();
    }

    private void mainParseExportCsv() throws IOException {
        Ui.printLine();
        System.out.println("Exporting builds...");
        ExportCsv.exportAllBuildsCsv(buildManager);
        Ui.printLine();
    }
}
