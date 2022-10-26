package pet;
import pet.PetList.*;

import java.util.Random;


public class Pet {
    public String name;
    public String species;
    // private int id;
    public Boolean isHealthy;
    public PetStatus status;

    public static final int NUM_OF_STATUS = 5;


    public static int numOfPets = 0;
    //public static int idTracker = 1;

    public Pet(String name, String species, boolean isHealthy) {
        this.name = name;
        this.species = species;
        // this.id = id;
        this.isHealthy = isHealthy;
        initPetStatus();
    }

    public String toString() {
//        String isHealthy = this.isHealthy ? "healthy" : "unhealthy";
        return String.format("I am a cute %s, my name is %s, and I am currently %s",
                species, name, status);
    }

    private void initPetStatus() {
        Random random = new Random();
        int randomNumber = random.nextInt(NUM_OF_STATUS) + 1;
        if (this.isHealthy){
            switch (randomNumber){
            case 1:
                status = PetHealthyStatus.happy;
                break;
            case 2:
                status = PetHealthyStatus.boring;
                break;
            case 3:
                status = PetHealthyStatus.sleepy;
                break;
            case 4:
                status = PetHealthyStatus.energetic;
                break;
            case 5:
                status = PetHealthyStatus.crazy;
                break;
            }
        }
        else if (isHealthy = false){
            switch (randomNumber){
            case 1:
                status = PetUnhealthyStatus.exhausted;
                break;
            case 2:
                status = PetUnhealthyStatus.painful;
                break;
            case 3:
                status = PetUnhealthyStatus.injured;
                break;
            case 4:
                status = PetUnhealthyStatus.bleeding;
                break;
            case 5:
                status = PetUnhealthyStatus.dying;
                break;
            }
        }
    }
}

