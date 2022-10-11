package seedu.duke.command;

public class AddCommand extends Command {
    public AddCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        this.universityName = parameters[1].substring(2);
        this.moduleCode = parameters[2].substring(2);
    }


}
