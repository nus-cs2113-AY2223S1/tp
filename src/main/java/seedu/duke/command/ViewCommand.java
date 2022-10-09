package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;

import static seedu.duke.command.AddFoodCommand.foodList;
import static seedu.duke.command.AddExerciseCommand.exerciseList;

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
        case ("food"):
            viewFood();
            break;
        case ("exercise"):
            viewExercise();
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

    private void viewFood() {
        for (int i = 0; i < foodList.size(); i++) {
            ui.output((i + 1) + " " + (foodList.get(i)).toString());
        }
    }

    private void viewExercise() {
        for (int i = 0; i < exerciseList.size(); i++) {
            ui.output((i + 1) + " " + (exerciseList.get(i)).toString());
        }
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
        this.biometrics = biometrics;
    }
}
