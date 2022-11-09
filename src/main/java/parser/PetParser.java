package parser;

import command.Command;
import command.EmptyCommand;
import command.petcommand.AddPetCommand;
import command.petcommand.RemovePetCommand;
import command.petcommand.ViewPetCommand;
import exception.DukeException;

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
            int startOfN = input.indexOf(parser.nameFlag);
            int startOfS = input.indexOf(parser.speciesFlag);
            int startOfH = input.indexOf(parser.healthFlag);
            if (startOfN == -1 || startOfS == -1 || startOfH == -1) {
                System.out.println("Invalid Input! format of parameters entered for adding a pet is invalid");
                throw new DukeException();
            }

            if (startOfN > startOfS || startOfS > startOfH || startOfN == -1) {
                System.out.println("Invalid Input! format of parameters entered for adding a pet is invalid");
                throw new DukeException();
            }


            String name = input.substring(startOfN + lengthOfSignature, startOfS).trim();
            String species = input.substring(startOfS + lengthOfSignature, startOfH).trim();
            String status = input.substring(startOfH);

            if (name.isEmpty()) {
                System.out.println("Please provide the name for pet");
                throw new DukeException();
            }

            if (species.isEmpty()) {
                System.out.println("Please provide the species for pet");
                throw new DukeException();
            }

            int statusInt = parser.isHealthy(status);
            if (statusInt != 1 && statusInt != 0) {
                System.out.println("Invalid Input! health entered invalid for adding a pet");
                throw new DukeException();
            }

            boolean isHealthy = statusInt == 0 ? false : true;
            assert name.length() > 0;
            assert species.length() > 0;
            return new AddPetCommand(name, species, isHealthy);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }


    public Command prepareRemovePet(String input) {
        try {
            int index = parser.indexOfInput(input);
            if (index <= 2000 || index >= 3000) {
                System.out.println("Invalid Input! a valid pet id is in the format of 2xxx");
                throw new DukeException();
            }
            assert index > 0;
            return new RemovePetCommand(index);
        } catch (DukeException e) {
            return new EmptyCommand();
        }
    }

}
