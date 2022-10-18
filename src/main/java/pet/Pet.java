package pet;

public class Pet {
    private String name;
    private String species;
    // private int id;
    private Boolean isHealthy;

    public static int numOfPets = 0;
    //public static int idTracker = 1;

    public Pet(String name, String species, Boolean isHealthy) {
        this.name = name;
        this.species = species;
        // this.id = id;
        this.isHealthy = isHealthy;
    }

    public String toString(){
        String isHealthy = this.isHealthy? "healthy" : "unhealthy";
        return String.format("I am a cute %s, my name is %s, and I am currently %s",
                species, name, isHealthy);
    }

    public void changeStatus(boolean isHealthy){
        this.isHealthy = isHealthy;
    }
}
