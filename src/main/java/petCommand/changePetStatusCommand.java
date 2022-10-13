package petCommand;

import pet.Pet;
import pet.PetList;

public class changePetStatusCommand {
    private PetList Pets;
    private boolean isHealthy;
    private int index;

    public changePetStatusCommand(PetList Pets, boolean isHealthy, int index){
        this.Pets = Pets;
        this.isHealthy = isHealthy;
        this.index = index;
    }

    public void excecute() {
        int changeIndex = index - 1;
        Pet currPet = Pets.petList.get(changeIndex);
        currPet.changeStatus(isHealthy);
    }
}
