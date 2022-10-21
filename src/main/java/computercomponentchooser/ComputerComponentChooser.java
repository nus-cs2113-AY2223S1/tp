package computercomponentchooser;

import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.export.ExportText;
import computercomponentchooser.storage.Storage;

import java.io.FileNotFoundException;

public class ComputerComponentChooser {

    static BuildManager buildManager;
    static Parser parser;
    static EditParser editParser;

    static Storage storage;

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

    public static void main(String[] args) {

        new ComputerComponentChooser();

    }
}
