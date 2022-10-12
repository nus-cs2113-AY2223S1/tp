package seedu.duke;

public class Delete extends Command {
    private String modCode;
    public Delete(String input) {
        int modIndex = input.indexOf("m/");
        this.modCode = input.substring(modIndex + 2);
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {
        modulelist.delete(this.modCode);
    }
}
