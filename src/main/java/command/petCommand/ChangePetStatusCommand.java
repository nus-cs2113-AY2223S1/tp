package command.petCommand;

import pet.Pet;
import pet.PetList;

public class ChangePetStatusCommand {
    private PetList Pets;
    private boolean isHealthy;
    private int index;

    public ChangePetStatusCommand(PetList Pets, boolean isHealthy, int index){
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
