package command.petCommand;

import pet.Pet;
import pet.PetList;

import java.util.Random;

public class ChangePetHealthStatusCommand {
    private int index;
    public boolean isHealthy;

    public ChangePetHealthStatusCommand(int index, boolean isHealthy){
        this.index = index;
        this.isHealthy = isHealthy;
    }

    public void excecute() {
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        int changeIndex = index - 1;
        Pet currPet = PetList.petList.get(changeIndex);
        currPet.isHealthy = isHealthy;
    }
}
