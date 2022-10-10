package seedu.duke;

public class Exit extends Command {
    public Exit(String input) {
        super();
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {

    }

    @Override
    public boolean checkExit() {
        return true;
    }
}
