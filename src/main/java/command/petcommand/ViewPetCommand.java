package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class ViewPetCommand extends Command {

    @Override
    public void execute() {
        System.out.println("Here are the pets in your pet list:");
        for (Pet pet : PetList.pets) {
            System.out.println(String.format("%d. %s", (pet.petId + 1), pet));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
