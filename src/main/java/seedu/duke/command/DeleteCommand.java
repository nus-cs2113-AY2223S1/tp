package seedu.duke.command;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] parameters, CommandType commandType, boolean isDeleteModule) {
        super(parameters, commandType);
        this.universityName = parameters[1].substring(2);
        if (isDeleteModule){
            this.moduleCode = parameters[2].substring(2);
        }
    }

}
