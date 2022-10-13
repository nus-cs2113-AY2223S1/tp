package petCommand;

import pet.Pet;
import pet.PetList;

public class removePetCommand {
    private PetList Pets;
    private int index;

    public removePetCommand(PetList pets, int index){
        this.Pets = pets;
        this.index = index;
    }

    public void excecute(){
        int deleteIndex = index - 1;
        Pets.petList.remove(deleteIndex);
        Pet.numOfPets--;
    }
}
