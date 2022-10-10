package seedu.duke;

public class Add extends Command {
    public Add(String input) {
        super();
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {
        System.out.println("Added something");
    }
}
