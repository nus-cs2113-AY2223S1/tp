package pet;

import java.util.ArrayList;

public class PetList {
    public static ArrayList<Pet> pets = new ArrayList<>();

    // public static addPet
    public static Pet findPet(String name) {
        for (Pet pet : pets) {
            if (pet.name == name) {
                return pet;
            }
        }
        return null;
    }
}
