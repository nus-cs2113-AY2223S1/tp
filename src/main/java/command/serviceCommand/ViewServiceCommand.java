package command.serviceCommand;

import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import service.ServiceList;

public class ViewServiceCommand extends Command {

    public ViewServiceCommand(){

    }

    @Override
    public void execute() {
        ServiceList.listService();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
