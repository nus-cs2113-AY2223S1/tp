package seedu.duke.user;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class to keep track of all the lists of PU user is interested in.
 */

public class UserUniversityListManager {

    // we store the key as the PU name
    private HashMap<String, UserUniversityList> myManager;

    public UserUniversityListManager() {
        myManager = new HashMap <String, UserUniversityList>();
    }

    /**
     * Function to create a new list for PU
     * @param input PU name from user input
     */
    public void createList(String input) {
        if (myManager.containsKey(input)) {
            System.out.println("Error: PU list already exists");;
        } else {
            UserUniversityList newList = new UserUniversityList(input);
            myManager.put(input, newList);
        }
    }

    /**
     * Function to return the UserUniversityList search by user
     * @param input PU name
     * @return the UserUniversityList corresponding to the input PU name
     */
    public UserUniversityList getList(String input) {
        if (!myManager.containsKey(input)) {
            System.out.println("No such universities found");
            throw new NoSuchElementException();
        }
        return myManager.get(input);
    }

    public HashMap<String, UserUniversityList> getMyManager() {
        return myManager;
    }

    public void setMyManager(HashMap<String, UserUniversityList> myManager) {
        this.myManager = myManager;
    }
}
