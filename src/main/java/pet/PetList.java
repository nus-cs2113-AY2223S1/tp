package pet;

import java.util.ArrayList;
import java.util.Objects;

public class PetList {
    public static ArrayList<Pet> pets = new ArrayList<>();

    // public static addPet
    public static Pet findPet(String name) {
        for (Pet pet : pets) {
            if (Objects.equals(pet.name, name)) {
                return pet;
            }
        }
        return null;
    }
}
