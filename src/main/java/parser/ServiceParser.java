package parser;

import command.*;

public class ServiceParser {
    private int lengthOfSignature;
    private Parser parser;

    public ServiceParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseService(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewServiceCommand();
            }
            System.out.println("Error: too little parameters entered for service operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddServiceCommand.COMMAND_WORD:
            return prepareAddService(statement);
        case RemoveServiceCommand.COMMAND_WORD:
            return prepareRemoveService(statement);
        default:
            System.out.println("Error: unrecognized service operation");
            return new EndCommand();
        }
    }

    public Command prepareAddService(String input){
        int d = input.indexOf("d/");

        if(d == -1){
            System.out.println("Error: no description entered");
            return new EndCommand();
        }

        String description = input.substring(d + lengthOfSignature);

        return new AddServiceCommand(description);

    }


    public Command prepareRemoveService(String input){
        int index = parser.indexOfRemove(input);
        if(index == -1){
            System.out.println("Error: index entered invalid for removing a service");
            return new EndCommand();
        }

        return new RemoveServiceCommand(index);
    }
}
