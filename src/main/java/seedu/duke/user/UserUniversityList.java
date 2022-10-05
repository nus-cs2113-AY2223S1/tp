package seedu.duke.user;

import seedu.duke.module.Module;
import seedu.duke.university.University;

import java.util.ArrayList;

/**
 * Class to keep track of a list of partner universities user is interested in
 */

public class UserUniversityList {
    private ArrayList<University> universities;

    public UserUniversityList() {
        this.universities = new ArrayList<>();
    }

    public ArrayList getUniversities() {
        return universities;
    }

    /**
     * Method to find matching university user stored by name to prevent duplicates
     * @param input input code of module
     * @return true if module is found, false otherwise
     */
    public boolean findUniversityByName(String input) {
        for (University university : universities) {
            if (university.getName().equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add multiple universities at once
     * @param input ArrayList of universities user wants to add
     */
    public void addUniversities(ArrayList<University> input) {
        for (University university : input) {
            addUniversity(university);
        }
    }

    public void addUniversity(University input) {
        if (findUniversityByName(input.getName())) {
            System.out.println("Error " + input + "  already added");
        } else {
            universities.add(input);
        }
    }

    public void displayAll() {
        if (universities.size() == 0) {
            System.out.println("No current modules saved");
        } else {
            for (int i = 0; i < universities.size(); i++) {
                System.out.print(i+1);
                System.out.print(" : ");
                System.out.println(universities.get(i).toString());
            }
        }
    }

    public void deleteUniversity(int index) {
        index--;
        try {
            System.out.println("Deleting universities:");
            System.out.println(universities.get(index).toString());
            universities.remove(index);
            System.out.println("Universities left are: ");
            displayAll();
        } catch (IndexOutOfBoundsException e){
            System.out.println("index not within range");
            System.out.println("use displayAll function to list all universities & indexes");
        }
    }
}
