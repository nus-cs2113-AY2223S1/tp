package seedu.duke;

public class View extends Command {
    String semester;
    public View(String input) {
        int semIndex = input.indexOf("s/");
        this.semester = input.substring(semIndex + 2);
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {
        modulelist.view(this.semester);
    }
}
