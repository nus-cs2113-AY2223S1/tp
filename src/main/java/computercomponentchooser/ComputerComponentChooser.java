package computercomponentchooser;

import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.export.ExportText;
import computercomponentchooser.storage.Storage;

import java.io.FileNotFoundException;

/**
 * The main class of the application.
 */
public class ComputerComponentChooser {

    static BuildManager buildManager;
    static Parser parser;
    static EditParser editParser;

    static Storage storage;

    /**
     * Initializes the application.
     */
    public ComputerComponentChooser() {

        buildManager = new BuildManager();

        parser = new Parser(buildManager);

        editParser = new EditParser(buildManager);

        Ui ui = new Ui(parser, editParser);

        storage = new Storage();

        ui.startSession();
        try {
            storage.loadBuild(buildManager);
            storage.loadComponent(buildManager);
        } catch (DuplicateBuildException e) {
            Ui.printLine();
            System.out.println(e.getMessage());
            Ui.printLine();
        } catch (FileNotFoundException e) {
            Ui.printLine();
            System.out.println("File not found");
            Ui.printLine();
        }
        ui.readLine();

        ui.endSession();
    }

    /**
     * The main method of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        new ComputerComponentChooser();

    }
}
