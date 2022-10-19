package seedu.duke.command;

public class ListCommand extends Command {
    String listOption;

    /**
     * Create new list command based on the type of list command.
     * 
     * @param parameters  Information about the new list command
     * @param commandType Type of new list command
     */
    public ListCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        if (parameters[1].equals("UNIVERSITIES")) {
            this.listOption = "UNIVERSITIES";
        } else if (parameters[1].equals("MODULES")) {
            this.listOption = "MODULES";
        } else if (parameters[1].startsWith("m/")) {
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
