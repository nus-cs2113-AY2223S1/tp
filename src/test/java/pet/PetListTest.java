package pet;

import command.Command;
import command.petcommand.AddPetCommand;
import command.petcommand.InitPetStatusCommand;
import command.petcommand.RemovePetCommand;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.PetParser;

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
    void addAPetWithInvalidHealthStatusTest() {
        Parser parser = new Parser();
        PetParser petParser = new PetParser(parser, 3);
        int numOfPet = PetList.pets.size();
        Command addPetCommand = petParser.prepareAddPet("n/Taro s/cat h/1");
        addPetCommand.execute();
        int numOfPetAfterAdd = PetList.pets.size();
        assertEquals(numOfPetAfterAdd - numOfPet, 0);
    }

    @Test
    void addAPetWithNoNameTest() {
        Parser parser = new Parser();
        PetParser petParser = new PetParser(parser, 3);
        int numOfPet = PetList.pets.size();
        Command addPetCommand = petParser.prepareAddPet("n/ s/cat h/1");
        addPetCommand.execute();
        int numOfPetAfterAdd = PetList.pets.size();
        assertEquals(numOfPetAfterAdd - numOfPet, 0);
    }

    @Test
    void removeAPetTest() {
        AddPetCommand addPetCommand = new AddPetCommand("Taro", "cat", true);
        addPetCommand.execute();
        int currNumOfPet = PetList.pets.size();
        RemovePetCommand removePetCommand = new RemovePetCommand(Pet.idCounter);
        removePetCommand.execute();
        int numOfPetAfterRemove = PetList.pets.size();
        assertEquals(numOfPetAfterRemove - currNumOfPet, -1);
    }

    @Test
    void findByNameTest() {
        AddPetCommand addPetCommand = new AddPetCommand("fiona", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        String petName = currPet.name;
        Pet petfound1 = PetList.findPet(petName);
        assertEquals(currPet, petfound1);
    }

    @Test
    void findPetByInvalidNameTest() {
        AddPetCommand addPetCommand = new AddPetCommand("kate", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        String petName = currPet.name;
        Pet petfound1 = PetList.findPet("petName");
        assertEquals(null, petfound1);
    }

    @Test
    void findByInvalidIdTest() {
        AddPetCommand addPetCommand = new AddPetCommand("kate", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        int id = currPet.petId;
        Pet petFound = PetList.findPetById(-1);
        assertEquals(null, petFound);
    }

    @Test
    void findByIdTest() {
        AddPetCommand addPetCommand = new AddPetCommand("fiona", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        int id = currPet.petId;
        Pet petFound = PetList.findPetById(id);
        assertEquals(currPet, petFound);
    }

    @Test
    void getPetNameByIdTest() {
        AddPetCommand addPetCommand = new AddPetCommand("feliks", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        int id = currPet.petId;
        String name = PetList.getPetNameById(id);
        assertEquals("feliks", name);
    }

    @Test
    void getPetNmeByInvalidIdTest() {
        AddPetCommand addPetCommand = new AddPetCommand("william", "cat", true);
        addPetCommand.execute();
        Pet currPet = PetList.pets.get(PetList.pets.size() - 1);
        String name = PetList.getPetNameById(-1);
        assertEquals("Invalid Id", name);
    }


}