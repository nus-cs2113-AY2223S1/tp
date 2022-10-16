package seedu.duke.command;

public class ListCommand extends Command {
    String listOption;

    public ListCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        if (parameters[1].startsWith("m/")) {
            this.moduleCode = parameters[1].substring(2);
            this.listOption = "module";
        } else if (parameters[1].startsWith("u/")) {
            this.universityName = parameters[1].substring(2).replace("_", " ");
            this.listOption = "university";
        }
    }

    public String getListOption() {
        return listOption;
    }
}
