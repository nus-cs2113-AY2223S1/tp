package pet;

import java.util.Random;


public class Pet {

    public static int idCounter = 2000;
    public final int petId;
    public String name;
    public String species;
    // private int id;
    public Boolean isHealthy;
    public PetStatus status;
    public Integer wellness;

    public static final int NUM_OF_STATUS = 5;


    public Pet(String name, String species, boolean isHealthy) {
        this.name = name;
        this.species = species;
        this.petId = ++idCounter;
        this.isHealthy = isHealthy;
        initPetStatus();
    }

    public String toString() {
        String health = isHealthy ? "healthy" : "unhealthy";
        return String.format("I am a cute %s, my name is %s, and I am currently %s and %s",
                species, name, health, status);
    }

    public void initPetStatus() {
        Random random = new Random();
        int randomNumber = random.nextInt(NUM_OF_STATUS) + 1;
        this.wellness = randomNumber;
        if (this.isHealthy) {
            this.wellness += 5;
            setPetStatus(this.wellness);
        } else {
            setPetStatus(this.wellness);
        }
    }

    public void changePetStatus() {
        this.wellness += 5;
        if (this.wellness >= 10) {
            this.wellness = 10;
            this.isHealthy = true;
        }
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
            System.out.println("Sorry, the pet status is invalid");
            break;
        }
    }
}

