package command.servicecommand;

import command.Command;
import exception.DukeException;
import service.ServiceList;

public class RemoveServiceCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    private int serviceId;

    public RemoveServiceCommand(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public void execute() {
        try{
            ServiceList.removeService(serviceId);
        } catch (DukeException e) {
            System.out.println("Sorry, No corresponding service found. Remove service failed.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
