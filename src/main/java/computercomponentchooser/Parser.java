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

/**
 * Handles the parsing of user input in the main mode.
 */
public class Parser {

    static final int COMMAND_PARAMETER = 0;
    static final int NAME_PARAMETER = 1;

    private final BuildManager buildManager;

    /**
     * Initializes a new Parser object.
     *
     * @param buildManager The buildManager to be used.
     */
    public Parser(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    /**
     * Retrieves the desired text from a position of the user input delimited by /.
     *
     * @param line The user input.
     * @param mode The desired position of the input to be retrieved.
     * @return The desired text at the desired position.
     */
    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split("/", 2);
        assert mode >= 0 && mode < 2;
        return lineSplit[mode];
    }

    /**
     * Checks if the user input is bye.
     *
     * @param line The user input.
     * @return A boolean value indicating whether the user input is bye.
     */
    static boolean checkBye(String line) {
        String checkLine = line.toLowerCase();
        return checkLine.equals("bye");
    }

    /**
     * Checks if the user input is edit.
     *
     * @param line The user input.
     * @return A boolean value indicating whether the user input is edit.
     */
    static boolean checkEdit(String line) {
        String edit = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        return edit.equals("edit");
    }

    /**
     * Parses the user input and executes the desired command.
     *
     * @param line The user input.
     */
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
            Ui.printLine();
            System.out.println(e.getMessage());
            Ui.printLine();
        } catch (NumberFormatException e) {
            Ui.printLine();
            System.out.println("Please enter a valid number.");
            Ui.printLine();
        }
    }

    /**
     * Parses the user input and executes the find command by finding a build depending on the search term.
     *
     * @param line The user input.
     * @throws BlankStringException If the search term is blank.
     */
    private void mainParseFind(String line) throws BlankStringException {
        String searchTerm = EditParser.getParameter(line, 1);
        Ui.printLine();
        buildManager.findBuilds(searchTerm);
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the filter command by listing the filtered builds depending on the
     * filter term and provided parameters.
     *
     * @param line The user input.
     * @throws NumberFormatException If the provided parameters are not numbers.
     * @throws UnknownCommandException If the filter term is not valid.
     * @throws NegativeNumberException If the provided parameters are negative.
     */
    private void mainParseFilter(String line) throws NumberFormatException, UnknownCommandException,
            NegativeNumberException {
        String filterType = EditParser.getParameter(line, 1);
        String lowestNumber = ""; // initialise as compatibility filter does not need this range
        String highestNumber = ""; // initialise as compatibility filter does not need this range
        if (!filterType.equals("compatibility")) {
            if (!filterType.equals("price") && !filterType.equals("power")) {
                throw new UnknownCommandException();
            } // guard clause, check if filterType is valid
            lowestNumber = EditParser.getParameter(line, 2);
            highestNumber = EditParser.getParameter(line, 3);
        }
        Ui.printLine();
        buildManager.filterBuilds(filterType, lowestNumber, highestNumber);
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the add command by adding a build depending on the build name.
     *
     * @param line The user input.
     * @throws DuplicateBuildException If the build name already exists.
     * @throws BlankStringException If the build name is blank.
     */
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

    /**
     * Parses the user input and executes the view command by viewing a build depending on the build name.
     *
     * @param line The user input.
     * @throws UnlistedBuildException If the build name does not exist.
     */
    private void mainParseView(String line) throws UnlistedBuildException {
        String name;
        name = getParameter(line, NAME_PARAMETER);
        Ui.printLine();
        if (!buildManager.doesBuildExist(name)) {
            throw new UnlistedBuildException();
        }
        System.out.print(buildManager.getBuild(name).toString());
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the delete command by deleting a build depending on the build name.
     *
     * @param line The user input.
     * @throws UnlistedBuildException If the build name does not exist.
     */
    private void mainParseDelete(String line) throws UnlistedBuildException {
        String name;
        name = getParameter(line, NAME_PARAMETER);
        Ui.printLine();
        buildManager.deleteBuild(name);
        try {
            storage.deleteBuild(name, buildManager);
        } catch (Exception e) {
            System.out.println("Error saving builds");
        }
        System.out.println("You have removed " + name);
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the list command by listing all builds.
     */
    private void mainParseList() {
        Ui.printLine();
        if (buildManager.getBuilds().size() == 0) {
            System.out.println("You have no builds");
        }
        System.out.println("Your current builds:");
        System.out.print(buildManager);
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the back command by going back to the main mode. This method is
     * called when the user is in the edit mode.
     */
    private void mainParseBack() {
        Ui.printLine();
        System.out.println("Back to main mode.");
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the export command by exporting all builds to a text file.
     *
     * @throws IOException If there is an error exporting the builds.
     */
    private void mainParseExport() throws IOException {
        Ui.printLine();
        System.out.println("Exporting builds...");
        ExportText.exportAllBuildsText(buildManager);
        Ui.printLine();
    }

    /**
     * Parses the user input and executes the exportCSV command by exporting all builds to a CSV file.
     *
     * @throws IOException If there is an error exporting the builds.
     */
    private void mainParseExportCsv() throws IOException {
        Ui.printLine();
        System.out.println("Exporting builds...");
        ExportCsv.exportAllBuildsCsv(buildManager);
        Ui.printLine();
    }
}
