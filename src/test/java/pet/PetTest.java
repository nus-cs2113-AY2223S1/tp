package pet;

import command.petcommand.InitPetStatusCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTest {
    @Test
    void initPet() {
        Pet newPet = new Pet("Yuhuan", "cat", true);
        assertEquals(newPet.name, "Yuhuan");
    }

    @Test
    void setHealthyPetStatusTest() {
        Pet newPet = new Pet("jinwen", "parrot", true);
        newPet.setPetStatus(10);
        assertEquals(PetHealthyStatus.fantastic, newPet.status);
        newPet.setPetStatus(9);
        assertEquals(PetHealthyStatus.energetic, newPet.status);
    }

    @Test
    void setUnhealthyPetStatusTest() {
        Pet newPet = new Pet("olivia", "parrot", true);
        newPet.setPetStatus(1);
        assertEquals(PetUnhealthyStatus.dying, newPet.status);
        newPet.setPetStatus(5);
        assertEquals(PetUnhealthyStatus.exhausted, newPet.status);
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

    @Test
    void initHealthyPetStatusTest() {
        Pet newPet3 = new Pet("yongjie", "parrot", true);
        newPet3.initPetStatus();
        boolean match = newPet3.wellness > 5 && newPet3.wellness <= 10;
        assertEquals(true, match);

    }

    @Test
    void initUnhealthyPetStatusTest() {
        Pet newPet3 = new Pet("yongjie", "parrot", false);
        newPet3.initPetStatus();
        boolean match = newPet3.wellness > 0 && newPet3.wellness <= 5;
        assertEquals(true, match);

    }



    @Test
    void changePetStatusTest() {
        Pet newPet2 = new Pet("yogurt", "parrot", true);
        newPet2.initPetStatus();
        int wellness = newPet2.wellness;
        if (wellness <= 5) {
            newPet2.changePetStatus();
            assertEquals(5, newPet2.wellness - wellness);
        } else if (wellness > 5) {
            newPet2.changePetStatus();
            assertEquals(10, newPet2.wellness);
        }
    }

    @Test
    void toStringTest() {
        Pet newPet5 = new Pet("neo", "dog", false);
        newPet5.setPetStatus(1);
        String expectedOutPut = "I am a cute dog, my name is neo, and I am currently unhealthy and dying";
        assertEquals(expectedOutPut, newPet5.toString());
    }

}