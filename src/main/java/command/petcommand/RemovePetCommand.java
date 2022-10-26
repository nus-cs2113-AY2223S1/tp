package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;

public class RemovePetCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    private int index;

    public RemovePetCommand(int index) {
        this.index = index;
    }

    public void execute() {
        int deleteIndex = index - 1;
        PetList.pets.remove(deleteIndex);
        printPetRemoveMessage();
    }

    public void printPetRemoveMessage() {
        System.out.println("Pet is removed");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
