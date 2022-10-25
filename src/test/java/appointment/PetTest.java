package appointment;
import org.junit.jupiter.api.Test;
import pet.PetList;
import pet.Pet;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    public void PetUnderTest(){
        PetList petList = new PetList();
        Pet newPet = new Pet("YuHuan", "cat", true);
        petList.petList.add(newPet);
        assertEquals(newPet.isHealthy, true);
    }
}
