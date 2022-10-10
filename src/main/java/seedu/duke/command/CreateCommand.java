package seedu.duke.command;

public class CreateCommand extends Command {
    public CreateCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        this.universityName = parameters[1].substring(2);
    }

}
