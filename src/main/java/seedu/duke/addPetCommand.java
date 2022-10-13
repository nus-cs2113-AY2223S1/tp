package seedu.duke;

public class addPetCommand {
    private PetList Pets;
    private String name;
    private String species;
    private boolean ishealthy;

    public addPetCommand(PetList Pets, String name, String species, boolean ishealthy){
        this.Pets = Pets;
        this.name = name;
        this.species = species;
        this.ishealthy = ishealthy;
    }

    public void excecute(){
        Pet petToAdd = new Pet(name, species, ishealthy);
        Pets.petList.add(petToAdd);
        Pet.numOfPets++;
    }
}
