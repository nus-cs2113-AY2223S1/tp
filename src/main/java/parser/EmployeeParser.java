package parser;

import command.*;
import command.employeeCommand.AddEmployeeCommand;
import command.employeeCommand.RemoveEmployeeCommand;
import command.employeeCommand.ViewEmployeeCommand;
import exception.DukeException;

public class EmployeeParser {
    private int lengthOfSignature;
    private Parser parser;
    public EmployeeParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }


    public Command parseEmployee(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewEmployeeCommand();
                } else {
                    throw new DukeException();
                }
            }
            String type = input.substring(0,input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddEmployeeCommand.COMMAND_WORD:
                return prepareAddEmployee(statement);
            case RemoveEmployeeCommand.COMMAND_WORD:
                return prepareRemoveEmployee(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Sorry, unrecognized employee operation.");
            return new EmptyCommand();
        }
    }

    public Command prepareAddEmployee(String input) {
        int startOfN = input.indexOf(" n/");

        if (startOfN == -1) {
            System.out.println("Sorry, format of parameters entered for adding an employee is invalid");
            return new EmptyCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature);
        return new AddEmployeeCommand(name);
    }

    public Command prepareRemoveEmployee(String input){
        int index = parser.indexOfInput(input);

        if (index == -1) {
            System.out.println("Sorry, index entered invalid for removing an employee ");
            return new EmptyCommand();
        }

        return new RemoveEmployeeCommand(index);
    }
}
