package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;
import service.Service;

public class RemovePetCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    private int index;

    public RemovePetCommand(int index) {
        this.index = index;
    }

    public void execute() {
        Pet pet = PetList.findPetById(index);
        if (pet != null) {
            System.out.println("Noted. I've removed this pet:");
            System.out.println(pet.name);
            System.out.println("Now you have " + (PetList.pets.size() - 1) + " pets in the pet list.");
            PetList.pets.remove(pet);
        } else if (pet == null) {
            System.out.println("Pet not found! Please enter a valid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
