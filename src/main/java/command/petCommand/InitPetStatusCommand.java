package command.petCommand;

import pet.Pet;
import pet.PetHealthyStatus;
import pet.PetList;
import pet.PetUnhealthyStatus;

import java.util.Random;

public class InitPetStatusCommand {
    public static final int NUM_OF_STATUS = 5;
    private int index;

    public InitPetStatusCommand(int index){
        this.index = index;
    }

    public void excecute() {
        Random random = new Random();
        int randomNumber = random.nextInt(NUM_OF_STATUS) + 1;
        int changeIndex = index - 1;
        Pet currPet = PetList.petList.get(changeIndex);
        if (currPet.isHealthy == true){
            switch (randomNumber){
            case 1:
                currPet.status = PetHealthyStatus.happy;
                break;
            case 2:
                currPet.status = PetHealthyStatus.boring;
                break;
            case 3:
                currPet.status = PetHealthyStatus.sleepy;
                break;
            case 4:
                currPet.status = PetHealthyStatus.energetic;
                break;
            case 5:
                currPet.status = PetHealthyStatus.crazy;
                break;
            }
        }
        else if (currPet.isHealthy = false){
            switch (randomNumber){
            case 1:
                currPet.status = PetUnhealthyStatus.exhausted;
                break;
            case 2:
                currPet.status = PetUnhealthyStatus.painful;
                break;
            case 3:
                currPet.status = PetUnhealthyStatus.injured;
                break;
            case 4:
                currPet.status = PetUnhealthyStatus.bleeding;
                break;
            case 5:
                currPet.status = PetUnhealthyStatus.dying;
                break;
            }
        }
    }
}
