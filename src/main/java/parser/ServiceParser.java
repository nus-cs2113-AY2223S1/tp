package parser;

import command.*;
import command.serviceCommand.AddServiceCommand;
import command.serviceCommand.RemoveServiceCommand;
import command.serviceCommand.ViewServiceCommand;
import exception.DukeException;

public class ServiceParser {
    private int lengthOfSignature;
    private Parser parser;

    public ServiceParser(Parser parser, int lengthOfSignature) {
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseService(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewServiceCommand();
                } else {
                    throw new DukeException();
                }
            }
            String type = input.substring(0,input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddServiceCommand.COMMAND_WORD:
                return prepareAddService(statement);
            case RemoveServiceCommand.COMMAND_WORD:
                return prepareRemoveService(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Sorry, unrecognized service operation.");
            return new EmptyCommand();
        }
    }

    public Command prepareAddService(String input){
        try {
            int startOfD = input.indexOf(" d/");
            if (startOfD == -1) {
                throw new DukeException();
            }
            String description = input.substring(startOfD + lengthOfSignature);
            return new AddServiceCommand(description);
        } catch (DukeException e) {
            System.out.println("Sorry, format of parameters entered for adding a service is invalid");
            return new EmptyCommand();
        }
    }


    public Command prepareRemoveService(String input) {
        try {
            int index = parser.indexOfInput(input);
            return new RemoveServiceCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing a service");
            return new EmptyCommand();
        }
    }
}
