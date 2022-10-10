package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

import java.util.Arrays;

public class SetCommand extends Command {

    public static final String[] GENDER_OPTIONS = new String[]{"male", "female", "other"};
    public static final String INVALID_BIOMETRICS = "Invalid biometrics";
    public static final String BIOMETRICS_SET = "biometrics set";

    private Ui ui;
    private Biometrics biometrics;

    private String arguments;

    public SetCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        setBiometrics(argumentList);
    }

    private void setBiometrics(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length != 6) {
                throw new IllegalValueException(INVALID_BIOMETRICS);
            }
            int age = Integer.parseInt(argumentList[1]);
            int height = Integer.parseInt(argumentList[3]);
            int weight = Integer.parseInt(argumentList[4]);
            int fatPercentage = Integer.parseInt(argumentList[5]);
            if (age <= 0 || !Arrays.asList(GENDER_OPTIONS).contains(argumentList[2])
                    || height <= 0 || weight <= 0 || fatPercentage <= 0) {
                throw new IllegalValueException(INVALID_BIOMETRICS);
            }
            biometrics.setBiometrics(age, argumentList[2], height, weight, fatPercentage);
            ui.output(BIOMETRICS_SET);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_BIOMETRICS);
        }
    }


    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
        this.biometrics = biometrics;
    }
}
