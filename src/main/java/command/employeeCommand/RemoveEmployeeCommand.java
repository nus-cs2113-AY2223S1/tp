package command.employeeCommand;

import command.Command;
import employee.EmployeeList;

public class RemoveEmployeeCommand extends Command {
    public final static String COMMAND_WORD = "remove";
    private int employeeId;

    public RemoveEmployeeCommand(int employeeId){
        this.employeeId = employeeId;
    }
    @Override
    public void execute() {
        EmployeeList.removeEmployee(employeeId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
