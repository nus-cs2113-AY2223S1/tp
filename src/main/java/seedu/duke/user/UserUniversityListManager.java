package seedu.duke.user;

import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.ui.Ui;
import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.userstorage.UserStorageParser;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to keep track of all the lists of PU user is interested in.
 */

public class UserUniversityListManager {

    // we store the key as the PU name
    private HashMap<String, UserUniversityList> myManager;
    private HashMap<String, UserUniversityList> myFavourites;

    private UserDeletedModules deletedModulesList = new UserDeletedModules();

    private static Logger logger = Logger.getLogger("UniversityListManagerLogger");

    public UserUniversityListManager() {
        myManager = new HashMap<String, UserUniversityList>();
        myFavourites = new HashMap<String, UserUniversityList>();
    }

    public UserUniversityListManager(String fileContent) {
        try {
            myManager = UserStorageParser.convertFileContentIntoUniversityList(fileContent);
        } catch (InvalidUserStorageFileException e) {
            System.out.println("Invalid file format in data/duke.txt");
            System.out.println("Creating new University List Manager");
            myManager = new HashMap<String, UserUniversityList>();
        }
    }

    public UserDeletedModules getUserDeletedModules() {
        return deletedModulesList;
    }

    /**
     * Method to create a new list for PU.
     * @param input PU name from user input
     */
    public void createList(String input) {
        assert input.length() > 0 : "Input school cannot be empty";
        if (myManager.containsKey(input)) {
            System.out.println("Error: PU list already exists");
        } else {
            UserUniversityList newList = new UserUniversityList(input);
            myManager.put(input, newList);
            logger.log(Level.FINER, "create new list for " + input);
            System.out.print(Ui.printPuListCreatedAcknowledgement(input));
        }
    }

    /**
     * Method to print all the existing university lists that have been created by user.
     * For each university, method first prints the university name
     * Method then prints all the modules user has saved for that particular university
     */
    public void displayAll() {
        for (Map.Entry<String, UserUniversityList> set : myManager.entrySet()) {
            String universityName = set.getKey();
            UserUniversityList universityList = set.getValue();
            System.out.println(universityName);
            universityList.displayModules();
        }
    }

    /**
     * Method to print all the favourite lists of the user.
     * For each university, method first prints the university name
     * Method then prints all the modules user has saved for that particular university
     */
    public void displayFavourites() {
        for (Map.Entry<String, UserUniversityList> set : myFavourites.entrySet()) {
            String universityName = set.getKey();
            UserUniversityList universityList = set.getValue();
            System.out.println(universityName);
            universityList.displayModules();
        }
    }

    /**
     * Method to print exactly the modules saved by user for a given university name.
     * @param input The partner university name
     * @throws InvalidUserCommandException If input is not a valid university name
     */
    public void displayUniversity(String input) throws InvalidUserCommandException {
        assert input.length() > 0 : "Input school cannot be empty";
        System.out.println(input);
        UserUniversityList myUniversityList = getList(input);
        myUniversityList.displayModules();
    }

    public boolean foundKeyAll(String inputSchool) {
        return myManager.containsKey(inputSchool);
    }

    public boolean foundKeyFavourites(String inputSchool) {
        return myFavourites.containsKey(inputSchool);
    }

    public void addModule(String inputSchool, UserModuleMapping inputModule) throws InvalidUserCommandException {
        if (myManager.containsKey(inputSchool)) {
            myManager.get(inputSchool).addModule(inputModule);
        } else {
            throw new InvalidUserCommandException("No such university found");
        }
    }

    /**
     * Method to delete the module based on inputs.
     * @param inputSchool the PU to delete the module from
     * @param puCode the exact PUCode to delete the module from
     */
    public void deleteModule(String inputSchool, String puCode) throws InvalidUserCommandException {
        assert inputSchool.length() > 0 : "Input school cannot be empty";
        assert puCode.length() > 0 : "Deleting PU code cannot be empty";
        if (foundKeyAll(inputSchool)) {
            UserModuleMapping deletedModule = myManager.get(inputSchool).getMyModules().getModuleByPuCode(puCode);
            deletedModulesList.addToDeletedModules(deletedModule);
            myManager.get(inputSchool).deleteModuleByPuCode(puCode);
        } else {
            throw new InvalidUserCommandException("No such university found");
        }
    }

    /**
     * Method to delete an entire university list.
     * @param inputSchool the PU school name that we will be deleting from.
     */
    public void deleteList(String inputSchool) throws InvalidUserCommandException {
        assert inputSchool.length() > 0 : "Input school cannot be empty";
        if (!foundKeyAll(inputSchool)) {
            throw new InvalidUserCommandException("No such university found");
        } else {
            myManager.remove(inputSchool);
            logger.log(Level.FINER, "delete list for " + inputSchool);
            System.out.print(Ui.printPuListDeletedAcknowledgement(inputSchool));
            if (foundKeyFavourites(inputSchool)) {
                myFavourites.remove(inputSchool);
                logger.log(Level.FINER, "delete favourite for " + inputSchool);
            }
        }
    }

    /**
     * Method to return the UserUniversityList search by user.
     * @param input PU name
     * @return the UserUniversityList corresponding to the input PU name
     */
    public UserUniversityList getList(String input) throws InvalidUserCommandException {
        assert input.length() > 0 : "Input school cannot be empty";
        if (!myManager.containsKey(input)) {
            throw new InvalidUserCommandException("HELP!! :: No such universities found");
        }
        return myManager.get(input);
    }

    public HashMap<String, UserUniversityList> getMyManager() {
        return myManager;
    }

    public HashMap<String, UserUniversityList> getMyFavourites() {
        return myFavourites;
    }

    public void setMyManager(HashMap<String, UserUniversityList> myManager) {
        this.myManager = myManager;
    }

    public void addFavourite(String input) throws InvalidUserCommandException {
        if (!myManager.containsKey(input)) {
            throw new InvalidUserCommandException("No such university in list currently");
        } else if (myFavourites.containsKey(input)) {
            throw new InvalidUserCommandException("University already added");
        } else {
            UserUniversityList u = myManager.get(input);
            myFavourites.put(input, u);
        }
    }

    public void deleteFavourite(String input) throws InvalidUserCommandException {
        if (!myManager.containsKey(input)) {
            throw new InvalidUserCommandException("No such university in all your lists currently");
        } else if (!myFavourites.containsKey(input)) {
            throw new InvalidUserCommandException("No such university in favourite currently");
        } else {
            myFavourites.remove(input);
        }
    }

    public void setMyFavourites(HashMap<String, UserUniversityList> myFavourites) {
        this.myFavourites = myFavourites;
    }
}