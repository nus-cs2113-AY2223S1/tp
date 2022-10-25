package command.serviceCommand;

import command.Command;
import service.ServiceList;

public class RemoveServiceCommand extends Command {
    public final static String COMMAND_WORD = "remove";

    private int serviceId;

    public RemoveServiceCommand(int serviceId){
        this.serviceId = serviceId;
    }

    @Override
    public void execute() {
        ServiceList.removeService(serviceId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
