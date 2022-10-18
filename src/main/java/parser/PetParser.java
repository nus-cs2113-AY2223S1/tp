package parser;

import command.*;
import command.petCommand.AddPetCommand;
import command.petCommand.RemovePetCommand;
import command.petCommand.ViewPetCommand;

public class PetParser {
    private int lengthOfSignature;
    private Parser parser;

    public PetParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }


    public Command parsePet(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewPetCommand();
            }
            System.out.println("Error: too little parameters entered for pet operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" "));
        switch(type) {
        case AddPetCommand.COMMAND_WORD:
            return prepareAddPet(statement);
        case RemovePetCommand.COMMAND_WORD:
            return prepareRemovePet(statement);
        default:
            System.out.println("Error: unrecognized pet operation");
            return new EndCommand();
        }
    }

    public Command prepareAddPet(String input){
        int startOfN = input.indexOf(" n/");
        int startOfS = input.indexOf(" s/");
        int startOfH = input.indexOf(" h/");

        if(startOfN > startOfS || startOfS > startOfH|| startOfN == -1){
            System.out.println("Error: format of parameters entered for adding a pet is invalid");
            return new EndCommand();
        }


        String name = input.substring(startOfN + lengthOfSignature, startOfS);
        String species = input.substring(startOfS + lengthOfSignature, startOfH);
        String status = input.substring(startOfH);

        int statusInt = parser.isHealthy(status);

        if(statusInt != 1 && statusInt != 0){
            System.out.println("Error: health entered invalid for adding a pet");
            return new EndCommand();
        }

        if(statusInt == 1){
            return new AddPetCommand(name, species, true);
        }

        return new AddPetCommand(name, species, false);
    }


    public Command prepareRemovePet(String input){
        int index = parser.indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemovePetCommand(index);
    }

}
