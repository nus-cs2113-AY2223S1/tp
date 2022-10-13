package command;

import pet.Pet;
import pet.PetList;


public class AddPetCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private PetList Pets;
    private String name;
    private String species;
    private boolean ishealthy;
    public AddPetCommand(String name, String species, boolean ishealthy){

        this.Pets = Pets;
        this.name = name;
        this.species = species;
        this.ishealthy = ishealthy;
    }

    @Override
    public void execute() {
        Pet petToAdd = new Pet(name, species, ishealthy);
        Pets.petList.add(petToAdd);
        Pet.numOfPets++;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
