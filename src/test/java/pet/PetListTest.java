package pet;

import command.petcommand.AddPetCommand;
import command.petcommand.InitPetStatusCommand;
import command.petcommand.RemovePetCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetListTest {

    @Test
    void addAPetTest() {
        int numOfPet = PetList.petList.size();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int numOfPetAfterAdd = PetList.petList.size();
        assertEquals(numOfPetAfterAdd - numOfPet, 1);
    }

    @Test
    void removeAPetTest() {
        Pet newPet = new Pet("cyk", "parrot", false);
        PetList.petList.add(newPet);
        int currNumOfPet = PetList.petList.size();
        RemovePetCommand removePetCommand = new RemovePetCommand(1);
        removePetCommand.execute();
        int numOfPetAfterRemove = PetList.petList.size();
        assertEquals(numOfPetAfterRemove - currNumOfPet, -1);
    }

    @Test
    void initPetStatusTest() {
        boolean find;
        Pet newPet = new Pet("cyk", "parrot", false);
        PetList.petList.add(newPet);
        InitPetStatusCommand initPetStatusCommand = new InitPetStatusCommand(1);
        initPetStatusCommand.execute();
        PetStatus[] petHealhtyStatuses = PetHealthyStatus.values();
        PetStatus[] petUnhealthyStatuses = PetUnhealthyStatus.values();
        PetStatus status = PetList.petList.get(0).status;
        find = Arrays.asList(petHealhtyStatuses).contains(status)
                || Arrays.asList(petUnhealthyStatuses).contains(status);
        assertEquals(find, true);

    }
}