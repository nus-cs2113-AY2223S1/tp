package command;

import appointment.AppointmentList;
import employee.Employee;
import employee.EmployeeList;
import service.ServiceList;

public class AddEmployeeCommand extends Command{
    public final static String COMMAND_WORD = "add";
    private final Employee employee;

    public AddEmployeeCommand(String employeeName){
        this.employee = new Employee(employeeName);
    }
    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList, ServiceList serviceList) {
        EmployeeList.addEmployee(employee);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
