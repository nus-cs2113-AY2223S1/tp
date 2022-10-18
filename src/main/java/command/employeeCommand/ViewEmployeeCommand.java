package command.employeeCommand;

import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import service.ServiceList;

public class ViewEmployeeCommand extends Command {

    public ViewEmployeeCommand() {

    }
    @Override
    public void execute() {
        EmployeeList.listEmployee();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
