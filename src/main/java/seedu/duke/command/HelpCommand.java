package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

public class HelpCommand extends Command {

    private Ui ui;
    private final String arguments;

    public HelpCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        if (Parser.getArgumentsCount(arguments) > 0) {
            throw new IllegalValueException("Invalid help command");
        }
        ui.output("Help will always be given at Hogwarts to those who ask for it" + System.lineSeparator()
                + "Available commands:" + System.lineSeparator()
                + "help - to display the help message" + System.lineSeparator()
                + "exit - to exit the program" + System.lineSeparator()
                + "set biometrics /{age} /{gender} /{height} /{weight} /{fat percentage} /{activity level}"
                + System.lineSeparator()
                + "view {biometrics/food/weight/bmi/maintenance/calories/all} {exercise/strength/cardio /{done}}"
                + System.lineSeparator()
                + "add strength /{description} /{weight} /{sets} /{repetitions} [/{date}]" + System.lineSeparator()
                + "add cardio /{description} /{distance} /{repetitions} [/{date}]" + System.lineSeparator()
                + "add food /{description} /{calories} [/{date}]" + System.lineSeparator()
                + "add weight /{weight} /{fat percentage}" + System.lineSeparator()
                + "remove {food/exercise} /{food index/current exerciseList index}" + System.lineSeparator()
                + "mark {done} /{exercise index} / {time} / {metabolic equivalent}" + System.lineSeparator()
                + "mark {undone} /{exercise index}" + System.lineSeparator()
                + "find {strength/cardio/food/date_f} /{description}" + System.lineSeparator()
                + "find {calories} /{date}");
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
    }
}
