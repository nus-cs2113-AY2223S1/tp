package command;

import pet.Pet;
import pet.PetList;

public class RemovePetCommand {
    public final static String COMMAND_WORD = "remove";
    private PetList Pets;
    private int index;

    public RemovePetCommand(PetList pets, int index){
        this.Pets = pets;
        this.index = index;
    }

    public void excecute(){
        int deleteIndex = index - 1;
        Pets.petList.remove(deleteIndex);
        Pet.numOfPets--;
    }
}
