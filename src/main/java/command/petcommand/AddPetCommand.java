package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class AddPetCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String name;
    private String species;
    private boolean isHealthy;

    public AddPetCommand(String name, String species, boolean isHealthy) {
        super();

        this.name = name;
        this.species = species;
        this.isHealthy = isHealthy;
    }

    @Override
    public void execute() {
        Pet petToAdd = new Pet(name, species, isHealthy);
        PetList.pets.add(petToAdd);
        printAddPetMessage();
    }

    public void printAddPetMessage() {
        System.out.println(String.format("Pet has been registered: name: %s, species: %s", name, species));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
