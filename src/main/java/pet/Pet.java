package pet;

import java.util.Random;


public class Pet {

    public static int id = 0;
    public final int petId;
    public String name;
    public String species;
    // private int id;
    public Boolean isHealthy;
    public PetStatus status;
    private Integer wellness;

    public static final int NUM_OF_STATUS = 5;


    public Pet(String name, String species, boolean isHealthy) {
        this.name = name;
        this.species = species;
        this.petId = id++;
        this.isHealthy = isHealthy;
        initPetStatus();
    }

    public String toString() {
        return String.format("I am a cute %s, my name is %s, and I am currently %s",
                species, name, status);
    }

    private void initPetStatus() {
        Random random = new Random();
        int randomNumber = random.nextInt(NUM_OF_STATUS) + 1;
        if (this.isHealthy) {
            setPetStatus(randomNumber + 5);
        } else {
            setPetStatus(randomNumber);
        }
    }

    public void changePetStatus(Integer wellnessFloat) {
        Integer wellnessInt = Math.round(wellnessFloat);
        if (wellnessInt >= 10) {
            wellnessInt = 10;
        } else if (wellnessInt <= 1) {
            wellnessInt = 1;
        }
        this.wellness = wellnessInt;
        setPetStatus(this.wellness);
    }

    public void setPetStatus(Integer wellness) {
        switch (wellness) {
        case 1:
            status = PetUnhealthyStatus.dying;
            break;
        case 2:
            status = PetUnhealthyStatus.bleeding;
            break;
        case 3:
            status = PetUnhealthyStatus.injured;
            break;
        case 4:
            status = PetUnhealthyStatus.painful;
            break;
        case 5:
            status = PetUnhealthyStatus.exhausted;
            break;
        case 6:
            status = PetHealthyStatus.sleepy;
            break;
        case 7:
            status = PetHealthyStatus.boring;
            break;
        case 8:
            status = PetHealthyStatus.happy;
            break;
        case 9:
            status = PetHealthyStatus.energetic;
            break;
        case 10:
            status = PetHealthyStatus.fantastic;
            break;
        default:
            break;
        }
    }
}

