package command;

import pet.Pet;
import pet.PetList;

public class addPetCommand extends {
    private PetList Pets;
    private String name;
    private String species;
    private boolean ishealthy;
    public final String COMMAND_WORD = "add";

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
