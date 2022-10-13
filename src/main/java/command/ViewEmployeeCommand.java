package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public class ViewEmployeeCommand extends Command{

    public ViewEmployeeCommand() {

    }
    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList, ServiceList serviceList) {
        employeeList.listEmployee();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
