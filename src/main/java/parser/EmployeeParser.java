package parser;

import command.Command;
import command.EmptyCommand;
import command.employeecommand.AddEmployeeCommand;
import command.employeecommand.RemoveEmployeeCommand;
import command.employeecommand.ViewEmployeeCommand;
import command.employeecommand.ViewEmployeeTaskCommand;
import exception.DukeException;

public class EmployeeParser {
    private int lengthOfSignature;
    private Parser parser;

    public EmployeeParser(Parser parser, int lengthOfSignature) {
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
            case ViewEmployeeTaskCommand.COMMAND_WORD:
                return prepareViewTaskEmployee(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Sorry, unrecognized employee operation.");
            return new EmptyCommand();
        }
    }

    public Command prepareAddEmployee(String input) {
        try {
            int startOfN = input.indexOf(parser.nameFlag);

            if (startOfN == -1 || !input.substring(0,startOfN).isEmpty()) {
                throw new DukeException();
            }


            String name = input.substring(startOfN + lengthOfSignature);
            if (name.isEmpty()) {
                throw new DukeException();
            }
            return new AddEmployeeCommand(name);
        } catch (DukeException e) {
            System.out.println("Sorry, format of parameters entered for adding an employee is invalid");
            return new EmptyCommand();
        }
    }

    public Command prepareRemoveEmployee(String input) {
        try {
            int index = parser.indexOfInput(input);
            return new RemoveEmployeeCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing an employee ");
            return new EmptyCommand();
        }
    }

    public Command prepareViewTaskEmployee(String input) {
        try {
            int index = parser.indexOfInput(input);
            return new ViewEmployeeTaskCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing task");
            return new EmptyCommand();
        }
    }
}
