package command;

import appointment.AppointmentList;
import employee.EmployeeList;

public class ViewStaffCommand extends Command{

    public ViewStaffCommand() {

    }
    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList) {
        employeeList.listEmployee();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
