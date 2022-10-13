package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public class EndCommand extends Command{

    public EndCommand(){

    }

    @Override
    public void execute() {
        // end
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
