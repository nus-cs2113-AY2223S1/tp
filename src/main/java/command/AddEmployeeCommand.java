package command;

import appointment.AppointmentList;
import employee.Employee;
import employee.EmployeeList;
import service.ServiceList;

public class AddEmployeeCommand extends Command{

    private final Employee employee;

    public AddEmployeeCommand(String employeeName){
        this.employee = new Employee(employeeName);
    }
    @Override
    public void execute() {
        EmployeeList.addEmployee(employee);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
