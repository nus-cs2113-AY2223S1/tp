package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;

public class ViewCommand extends Command {

    private Ui ui;
    private  Biometrics biometrics;
    private String arguments;

    public ViewCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        String viewType = argumentList[0];
        switch (viewType) {
        case ("biometrics"):
            viewBiometrics();
            break;
        default:
            handleInvalidViewType();
        }
    }

    private void handleInvalidViewType() {
        ui.output("Invalid Command");
    }

    private void viewBiometrics() {
        ui.output(biometrics.toString());
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
       this.ui = ui;
       this.biometrics = biometrics;
    }
}
