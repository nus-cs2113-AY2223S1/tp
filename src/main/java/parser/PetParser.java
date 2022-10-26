package parser;

import command.Command;
import command.EmptyCommand;
import command.petCommand.AddPetCommand;
import command.petCommand.RemovePetCommand;
import command.petCommand.ViewPetCommand;
import exception.DukeException;
import pet.Pet;
import seedu.duke.Duke;

public class PetParser {
    private int lengthOfSignature;
    private Parser parser;

    public PetParser(Parser parser, int lengthOfSignature) {
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }


    public Command parsePet(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewPetCommand();
                }
                System.out.println("Invalid Input! too little parameters entered for pet operation");
                throw new DukeException();
            }

            String type = input.substring(0, input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddPetCommand.COMMAND_WORD:
                return prepareAddPet(statement);
            case RemovePetCommand.COMMAND_WORD:
                return prepareRemovePet(statement);
            default:
                System.out.println("Invalid Input! unrecognized pet operation");
                throw new DukeException();
            }
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }

    public Command prepareAddPet(String input) {
        try {
            int startOfN = input.indexOf(" n/");
            int startOfS = input.indexOf(" s/");
            int startOfH = input.indexOf(" h/");

            if (startOfN > startOfS || startOfS > startOfH || startOfN == -1) {
                System.out.println("Invalid Input! format of parameters entered for adding a pet is invalid");
                throw new DukeException();
            }


            String name = input.substring(startOfN + lengthOfSignature, startOfS);
            String species = input.substring(startOfS + lengthOfSignature, startOfH);
            String status = input.substring(startOfH);

            // need to deal with input isHealthy not a number
            int statusInt = parser.isHealthy(status);
            if (statusInt != 1 && statusInt != 0) {
                System.out.println("Invalid Input! health entered invalid for adding a pet");
                throw new DukeException();
            }
            boolean isHealthy = statusInt == 0 ? false : true;

            return new AddPetCommand(name, species, isHealthy);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }


    public Command prepareRemovePet(String input) {
        try {
            int index = parser.indexOfInput(input);
            if (index <= 0 || index > Pet.numOfPets) {
                System.out.println("Invalid Input! please enter a valid id");
                throw new DukeException();
            }
            return new RemovePetCommand(index);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }

}
