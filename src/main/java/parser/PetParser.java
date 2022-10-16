package parser;

import command.AddPetCommand;
import command.Command;
import command.EndCommand;
import command.ViewPetCommand;

public class PetParser {
    private final int lengthOfSignature = 3;

    public Command parsePet(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewPetCommand();
            }
            System.out.println("Error: too little parameters entered for pet operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddPetCommand.COMMAND_WORD:
            return prepareAddPet(statement);
        default:
            System.out.println("Error: unrecognized pet operation");
            return new EndCommand();
        }
    }

    public Command prepareAddPet(String input){
        int startOfN = input.indexOf("n/");
        int startOfS = input.indexOf(" s/");

        if(startOfN > startOfS || startOfN == -1|| startOfS == -1){
            System.out.println("Error: format of parameters entered for adding a pet is invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN -1 + lengthOfSignature, startOfS);
        String status = input.substring(startOfS + lengthOfSignature);
        return new AddPetCommand(name, status, true);
    }

    public Command prepareRemovePet(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemovePetCommand(index);
    }
}
