package parser;

import command.Command;
import command.EmptyCommand;
import command.servicecommand.AddServiceCommand;
import command.servicecommand.RemoveServiceCommand;
import command.servicecommand.ViewServiceCommand;
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
            if (!input.contains(" ") && !input.equals("view")) {
                throw new DukeException();
            }
            if (!input.contains(" ") && input.equals("view")) {
                return new ViewServiceCommand();
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

    public Command prepareAddService(String input) {
        try {
            int startOfD = input.indexOf(parser.descriptionFlag);
            if (startOfD == -1 || !input.substring(0, startOfD).isEmpty()) {
                throw new DukeException();
            }
            String description = input.substring(startOfD + lengthOfSignature);
            if (description.isEmpty()) {
                throw new DukeException();
            }

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
