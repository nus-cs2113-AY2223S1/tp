package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class ViewPetCommand extends Command {

    @Override
    public void execute() {
        int counter = 0;
        System.out.println("Here are the pets in your pet list:");
        for (Pet pet : PetList.pets) {
            counter += 1;
            System.out.println(String.format("%d.\tID:%d\t %s", counter, (pet.petId), pet));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
