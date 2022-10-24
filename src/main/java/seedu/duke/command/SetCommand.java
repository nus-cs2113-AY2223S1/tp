package seedu.duke.command;


import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SetCommand extends Command {


    private static Logger logger = Logger.getLogger("SetCommand");

    private Ui ui;
    private Biometrics biometrics;

    private final String arguments;
    private final boolean toDisplay;

    public SetCommand(String arguments, boolean toDisplay) {
        this.arguments = arguments;
        this.toDisplay = toDisplay;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        try {
            setBiometrics(argumentList);
        } catch (IllegalValueException e) {
            logger.log(Level.WARNING, "exception: " + e.getMessage());
            throw e;
        }
    }

    private void setBiometrics(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length < 6) {
                throw new IllegalValueException("Insufficient parameters");
            }
            int age = Integer.parseInt(argumentList[1]);
            String gender = argumentList[2];
            int height = Integer.parseInt(argumentList[3]);
            int weight = Integer.parseInt(argumentList[4]);
            int fat = Integer.parseInt(argumentList[5]);
            biometrics.setBiometrics(age, gender, height, weight, fat);
            logger.log(Level.FINE, "parameters: "
                    + String.format("%d %s %d %d %d", age, gender, height, weight, fat));
            if (toDisplay) {
                ui.output("Biometrics set:\n" + biometrics.toString());
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Biometrics, except for gender, should be numerical");
        }
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
    }
}
