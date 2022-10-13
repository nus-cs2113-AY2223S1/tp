package command;

import pet.Pet;
import pet.PetList;

public class AddPetCommand extends Command{
    private PetList Pets;
    private String name;
    private String species;
    private boolean ishealthy;

    public final static String COMMAND_WORD = "add";

    public AddPetCommand(PetList Pets, String name, String species, boolean ishealthy){
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
