package pet;

import exception.DukeException;

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

    public static String getPetNameById(int id) {

        for (Pet pet : PetList.pets) {
            if (pet.petId == id) {
                return pet.name;
            }
        }
        return "Invalid Id";
    }

    public static Pet findPetById(int petId) {
        for (Pet pet : pets) {
            if (pet.petId == petId) {
                return pet;
            }
        }
        return null;
    }

}
