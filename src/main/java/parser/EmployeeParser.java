package parser;

import command.*;

public class EmployeeParser {
    private int lengthOfSignature;
    private Parser parser;
    public EmployeeParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }



    public Command parseEmployee(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewEmployeeCommand();
            }
            System.out.println("Error: too little parameters entered for employee operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddEmployeeCommand.COMMAND_WORD:
            return prepareAddEmployee(statement);
        case RemoveEmployeeCommand.COMMAND_WORD:
            return prepareRemoveEmployee(statement);
        default:
            System.out.println("Error: unrecognized employee operation");
            return new EndCommand();
        }
    }

    public Command prepareAddEmployee(String input){
        int startOfN = input.indexOf("n/");

        if(startOfN == -1){
            System.out.println("Error: format of parameters entered for adding an employee is invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature - 1);
        return new AddEmployeeCommand(name);
    }


    public Command prepareRemoveEmployee(String input){
        int index = parser.indexOfRemove(input);

        if(index == -1){
            System.out.println("Error: index entered invalid for removing an employee ");
            return new EndCommand();
        }

        return new RemoveEmployeeCommand(index);
    }
}
