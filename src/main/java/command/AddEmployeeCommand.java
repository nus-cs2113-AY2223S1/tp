package command;

import appointment.Appointment;
import appointment.AppointmentList;
import employee.Employee;
import employee.EmployeeList;
import service.Service;

import java.time.LocalDateTime;

public class AddEmployeeCommand extends Command{

    private final Employee employee;

    public AddEmployeeCommand(String employeeName){
        this.employee = new Employee(employeeName);
    }
    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList) {
        EmployeeList.addEmployee(employee);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
