package command.petCommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class AddPetCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String name;
    private String species;
    private boolean ishealthy;
    public AddPetCommand(String name, String species, boolean ishealthy){

        this.name = name;
        this.species = species;
        this.ishealthy = ishealthy;
    }

    @Override
    public void execute() {
        Pet petToAdd = new Pet(name, species, ishealthy);
        PetList.petList.add(petToAdd);
        Pet.numOfPets++;
        printAddPetMessage();
    }

    public void printAddPetMessage(){
        System.out.println(String.format("Pet has been registered: name: %s, species: %s", name, species));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
