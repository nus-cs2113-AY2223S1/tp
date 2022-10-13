package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public class ViewServiceCommand extends Command{

    public ViewServiceCommand(){

    }

    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList, ServiceList serviceList) {
        ServiceList.listService();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
