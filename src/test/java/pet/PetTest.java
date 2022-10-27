package pet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTest {
    @Test
    void initPet() {
        Pet newPet = new Pet("Yuhuan", "cat", true);
        assertEquals(newPet.name, "Yuhuan");
    }
}