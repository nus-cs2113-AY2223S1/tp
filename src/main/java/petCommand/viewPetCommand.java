package petCommand;

import pet.Pet;
import pet.PetList;

public class viewPetCommand {
    private PetList Pets;

    public viewPetCommand(PetList Pets){
        this.Pets = Pets;
    }

    public void execute() {
        int petIndex;
        for (int i = 0; i < Pet.numOfPets; i++) {
            petIndex = i + 1;
            Pet currPet = Pets.petList.get(i);
            System.out.println(String.format("%d. %s", petIndex, currPet.toString()));
        }
    }
}
