package pet;

import command.petcommand.AddPetCommand;
import command.petcommand.InitPetStatusCommand;
import command.petcommand.RemovePetCommand;
import employee.EmployeeList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetListTest {

    @Test
    void addAPetTest() {
        int numOfPet = PetList.pets.size();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int numOfPetAfterAdd = PetList.pets.size();
        assertEquals(numOfPetAfterAdd - numOfPet, 1);
    }

    @Test
    void removeAPetTest() {
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int currNumOfPet = PetList.pets.size();
        RemovePetCommand removePetCommand = new RemovePetCommand(Pet.id);
        removePetCommand.execute();
        int numOfPetAfterRemove = PetList.pets.size();
        assertEquals(numOfPetAfterRemove - currNumOfPet, -1);
    }

    @Test
    void initPetStatusTest() {
        boolean find;
        Pet newPet = new Pet("cyk", "parrot", false);
        PetList.pets.add(newPet);
        InitPetStatusCommand initPetStatusCommand = new InitPetStatusCommand(1);
        initPetStatusCommand.execute();
        PetStatus[] petHealhtyStatuses = PetHealthyStatus.values();
        PetStatus[] petUnhealthyStatuses = PetUnhealthyStatus.values();
        PetStatus status = PetList.pets.get(0).status;
        find = Arrays.asList(petHealhtyStatuses).contains(status)
                || Arrays.asList(petUnhealthyStatuses).contains(status);
        assertEquals(find, true);

    }
}