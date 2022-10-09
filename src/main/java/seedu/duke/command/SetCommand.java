package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.Parser;
import seedu.duke.Ui;

import java.util.Arrays;

public class SetCommand extends Command {

    public static final String INVALID_BIOMETRICS = "Invalid biometrics";

    private Ui ui;
    private Biometrics biometrics;

    private String arguments;

    public SetCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        try {
            setBiometrics(argumentList);
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    private void setBiometrics(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length < 5) {
                throw new IllegalValueException("Insufficient parameters");
            }
            biometrics.setAge(argumentList[1].trim());
            biometrics.setGender(argumentList[2].trim());
            biometrics.setHeight(argumentList[3].trim());
            biometrics.setWeight(argumentList[4].trim());
            biometrics.setFatPercentage(argumentList[5].trim());
            ui.output("biometrics set");
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Biometrics, except for gender, should be numerical");
        }
    }


    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
        this.biometrics = biometrics;
    }
}
