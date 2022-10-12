package seedu.duke;

public class Mcs extends Command {
    String semester;
    public Mcs(String input) {
        int semIndex = input.indexOf("s/");
        this.semester = input.substring(semIndex + 2);
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {
        modulelist.mc(this.semester);
    }
}
