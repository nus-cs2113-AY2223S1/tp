package parser;

import command.Command;
import command.EmptyCommand;
import command.petCommand.AddPetCommand;
import command.petCommand.RemovePetCommand;
import command.petCommand.ViewPetCommand;
import command.taskCommand.AddTaskCommand;
import command.taskCommand.ReassignTaskCommand;
import command.taskCommand.RemoveTaskCommand;
import command.taskCommand.ViewTaskCommand;
import exception.DukeException;

import javax.swing.text.View;

public class TaskParser {
    private int lengthOfSignature;
    private Parser parser;

    public TaskParser(Parser parser, int lengthOfSignature) {
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseTask(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewTaskCommand();
                }
                System.out.println("Invalid Input! too little parameters entered for pet operation");
                throw new DukeException();
            }

            String type = input.substring(0, input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddTaskCommand.COMMAND_WORD:
                return prepareAddTask(statement);
            case RemovePetCommand.COMMAND_WORD:
                return prepareRemoveTask(statement);
            case ReassignTaskCommand.COMMAND_WORD:
                return prepareReassignTask(statement);
            default:
                System.out.println("Invalid Input! unrecognized pet operation");
                throw new DukeException();
            }
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }

    public Command prepareAddTask(String input) {
        try {
            int startOfI = input.indexOf(" i/");
            int startOfE = input.indexOf(" e/");
            int startOfD = input.indexOf(" d/");

            if (startOfI > startOfE || startOfE > startOfD || startOfI == -1) {
                System.out.println("Invalid Input! format of parameters entered for adding a pet is invalid");
                throw new DukeException();
            }

            String appointmentIdString = input.substring(startOfI + lengthOfSignature, startOfE).trim();
            String employeeIdString = input.substring(startOfE + lengthOfSignature, startOfD).trim();
            String description = input.substring(startOfD + lengthOfSignature).trim();

            int appointmentIdInt = Integer.parseInt(appointmentIdString);
            int employeeIdint = Integer.parseInt(employeeIdString);

            return new AddTaskCommand(appointmentIdInt, employeeIdint, description);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }

    public Command prepareRemoveTask(String input) {
        try {
            int index = parser.indexOfInput(input);
            return new RemoveTaskCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing an task");
            return new EmptyCommand();
        }
    }

    public Command prepareReassignTask(String input) {
        try {
            int startOfI = input.indexOf(" i/");
            int startOfE = input.indexOf(" e/");
            if (startOfI > startOfE || startOfI == -1) {
                System.out.println("Invalid Input! format of parameters entered for adding a task is invalid");
                throw new DukeException();
            }

            String taskIndexString = input.substring(startOfI + lengthOfSignature, startOfE).trim();
            String employeeIndexString = input.substring(startOfE + lengthOfSignature).trim();
            int taskIndexInt = Integer.parseInt(taskIndexString);
            int employeeIndexInt = Integer.parseInt(employeeIndexString);

            return new ReassignTaskCommand(taskIndexInt, employeeIndexInt);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }
}
