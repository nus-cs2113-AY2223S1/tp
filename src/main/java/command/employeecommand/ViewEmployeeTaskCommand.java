package command.employeecommand;

import command.Command;
import employee.EmployeeList;

public class ViewEmployeeTaskCommand extends Command {

    public static final String COMMAND_WORD = "task";
    private int employeeId;

    public ViewEmployeeTaskCommand(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        EmployeeList.viewEmployeeTasks(employeeId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
