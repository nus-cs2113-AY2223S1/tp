package command.employeeCommand;

import command.Command;
import employee.EmployeeList;

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
