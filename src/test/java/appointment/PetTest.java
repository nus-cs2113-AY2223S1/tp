package appointment;

import pet.PetList;
import pet.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PetTest {
    public void petUnderTest() {
        PetList petList = new PetList();
        Pet newPet = new Pet("YuHuan", "cat", true);
        petList.pets.add(newPet);
    }
}
