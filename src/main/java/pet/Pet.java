package pet;

public class Pet {
    public String name;
    public String species;
    // private int id;
    public Boolean isHealthy;
    public PetStatus status;

    public static int numOfPets = 0;
    //public static int idTracker = 1;

    public Pet(String name, String species, boolean isHealthy) {
        this.name = name;
        this.species = species;
        // this.id = id;
        this.isHealthy = isHealthy;
    }

    public String toString() {
        String isHealthy = this.isHealthy ? "healthy" : "unhealthy";
        return String.format("I am a cute %s, my name is %s, and I am currently %s",
                species, name, isHealthy);
    }
}

