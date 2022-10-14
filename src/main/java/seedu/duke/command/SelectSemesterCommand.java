package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class SelectSemesterCommand extends Command {
    public static final String COMMAND_WORD = "semester";
    private static int updatedSemester;

    public SelectSemesterCommand(String[] input) {
        super(input);
        this.updatedSemester = Integer.parseInt(input[1]);

    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        state.setSemester(updatedSemester);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "You are now planning for semester " + updatedSemester;
    }


}
