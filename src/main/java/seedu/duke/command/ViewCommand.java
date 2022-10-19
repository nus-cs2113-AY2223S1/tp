package seedu.duke.command;

public class ViewCommand extends Command {
    String viewOption;

    /**
     * Create new view command based on the type of view command.
     * 
     * @param parameters  Information about the new view command
     * @param commandType Type of new view command
     */
    public ViewCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        if (parameters[1].startsWith("u/")) {
            this.universityName = parameters[1].substring(2);
            this.viewOption = "UNIVERSITY";
        } else {
            this.viewOption = parameters[1];
        }
    }

    public String getViewOption() {
        return viewOption;
    }
}
