package command.petCommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class ViewPetCommand extends Command {
    private PetList Pets;

    @Override
    public void execute() {
        int petIndex;
        for (int i = 0; i < Pet.numOfPets; i++) {
            petIndex = i + 1;
            Pet currPet = Pets.petList.get(i);
            System.out.println(String.format("%d. %s", petIndex, currPet.toString()));
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
