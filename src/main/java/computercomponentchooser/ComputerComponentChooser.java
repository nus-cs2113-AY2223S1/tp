package computercomponentchooser;

public class ComputerComponentChooser {

    static BuildManager buildManager;
    static Parser parser;
    static EditParser editParser;

    public ComputerComponentChooser() {

        buildManager = new BuildManager();

        parser = new Parser(buildManager);

        editParser = new EditParser(buildManager);

        Ui ui = new Ui(parser, editParser);

        ui.startSession();

        ui.readLine();

        ui.endSession();
    }

    public static void main(String[] args) {

        new ComputerComponentChooser();

    }
}
