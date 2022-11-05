package seedu.duke.user;

import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.TimetableNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.ui.Ui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.timetable.TimetableManager;

/**
 * Class to keep track of all the lists of PU user is interested in.
 */

public class UserUniversityListManager {

    // we store the key as the PU name
    private HashMap<String, UserUniversityList> myManager;

    public TimetableManager ttManager;

    private UserDeletedModules deletedModulesList = new UserDeletedModules();

    private static Logger logger = Logger.getLogger("UniversityListManagerLogger");

    public UserUniversityListManager() {
        this.myManager = new HashMap<String, UserUniversityList>();
        this.ttManager = new TimetableManager();
    }

    private void checkTimetables() {

    }

    private void checkUniversityLists() {

    }

    private boolean checkEmpty(String comment) {
        if (comment.length() == 0) {
            return true;
        }
        for (int i = 0; i < comment.length(); ++i) {
            if (comment.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public void updateComment(String universityName, String moduleCode, String comment)
            throws InvalidUserCommandException, UniversityNotFoundException {
        if (!containsKey(universityName)) {
            throw new UniversityNotFoundException("Error: No list containing such university\n"
            + "Please create university and add relevant module before adding a comment");
        } else {
            if (checkEmpty(comment)) {
                System.out.println("Error: Invalid Comment");
                return;
            } else if (isNotValidComment(comment)) {
                System.out.println("Error: Invalid Comment");
                return;
            }
            getList(universityName).updateComment(moduleCode, comment);
        }
    }

    private boolean isNotValidComment(String comment) {
        return comment.contains("_") || comment.contains("%") || comment.contains("/") || comment.contains(";");
    }

    public void deleteComment(String universityName, String moduleCode) throws InvalidUserCommandException,
            UniversityNotFoundException {
        if (!containsKey(universityName)) {
            throw new UniversityNotFoundException("Error: No list containing such university\n");
        } else {
            getList(universityName).deleteComment(moduleCode);
        }
    }

    /**
     * Method to create a new list for PU.
     * @param input PU name from user input
     */
    public void createList(String input) {
        assert input.length() > 0 : "Input school cannot be empty";
        if (containsKey(input)) {
            System.out.println("Error: PU list already exists");
        } else {
            UserUniversityList newList = new UserUniversityList(input);
            myManager.put(input, newList);
            ttManager.createTimetable(input, false);
            logger.log(Level.FINER, "create new list for " + input);
            System.out.print(Ui.printPuListCreatedAcknowledgement(input));
        }
    }

    /**
     * Method to delete an entire university list.
     * @param inputSchool the PU school name that we will be deleting from.
     */
    public void deleteList(String inputSchool) throws InvalidUserCommandException, TimetableNotFoundException {
        assert inputSchool.length() > 0 : "Input school cannot be empty";
        if (!containsKey(inputSchool)) {
            throw new InvalidUserCommandException("No such university found");
        } else {
            myManager.remove(inputSchool);
            logger.log(Level.FINER, "delete list for " + inputSchool);
            System.out.print(Ui.printPuListDeletedAcknowledgement(inputSchool));
            ttManager.deleteTimetable(inputSchool);
        }
    }

    public void addModule(String inputSchool, UserModuleMapping inputModule) throws InvalidUserCommandException {
        if (containsKey(inputSchool)) {
            getUserUniversityList(inputSchool).addModule(inputModule);
        } else {
            throw new InvalidUserCommandException("No such university found in your university lists. "
                    + "Please create a list for " + inputSchool + " first!");
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
        if (containsKey(inputSchool)) {
            UserModuleMapping deletedModule =
                    getUserUniversityList(inputSchool).getMyModules().getModuleByPuCode(puCode);
            deletedModulesList.addToDeletedModules(deletedModule);
            getUserUniversityList(inputSchool).deleteModuleByPuCode(puCode);
            ttManager.getTimetableByUniversityName(inputSchool).deleteLessonByCode(puCode);
        } else {
            throw new InvalidUserCommandException("No such university found in your university lists. "
                    + "Please create a list for " + inputSchool + " first!");
        }
    }

    public void addFavourite(String input) throws InvalidUserCommandException {
        if (!myManager.containsKey(input)) {
            throw new InvalidUserCommandException("No such university in list currently");
        } else if (myManager.containsKey(input) && myManager.get(input).isFavourite()) {
            throw new InvalidUserCommandException("University already added");
        } else {
            myManager.get(input).setFavourite(true);
            System.out.print(Ui.printFavouriteListAddedAcknowledgement(input));
        }
    }

    public void deleteFavourite(String input) throws InvalidUserCommandException {
        if (!myManager.containsKey(input)) {
            throw new InvalidUserCommandException("No such university in all your lists currently");
        } else if (myManager.containsKey(input) && !myManager.get(input).isFavourite()) {
            throw new InvalidUserCommandException("No such university in favourite list currently");
        } else {
            myManager.get(input).setFavourite(false);
            System.out.print(Ui.printFavouriteListDeletedAcknowledgement(input));
        }
    }

    public HashMap<String, UserUniversityList> getMyFavourites(HashMap<String, UserUniversityList> myManager) {
        HashMap<String, UserUniversityList> favouritesList = new HashMap<>();
        for (Map.Entry<String, UserUniversityList> entry : myManager.entrySet()) {
            UserUniversityList uni = entry.getValue();
            if (uni.isFavourite()) {
                favouritesList.put(uni.getUniversityName(), uni);
            }
        }
        return favouritesList;
    }

    /**
     * Method to print all the favourite lists of the user.
     */
    public void displayFavourites() {
        Ui.printUserFavouriteLists(myManager);
    }

    /**
     * Method to print exactly the modules saved by user for a given university name.
     * @param input The partner university name
     * @throws InvalidUserCommandException If input is not a valid university name
     */
    public void displayUniversity(String input) throws InvalidUserCommandException {
        assert input.length() > 0 : "Input school cannot be empty";
        UserUniversityList myUniversityList = getList(input);
        System.out.println(input);
        myUniversityList.displayModules();
    }

    /**
     * Method to print all the existing university lists that have been created by user.
     * For each university, method first prints the university name
     * Method then prints all the modules user has saved for that particular university
     */
    public void displayAll() {
        if (myManager.isEmpty()) {
            System.out.println("You do not have any university lists at the moment!");
        }
        for (Map.Entry<String, UserUniversityList> set : myManager.entrySet()) {
            String universityName = set.getKey();
            UserUniversityList universityList = set.getValue();
            System.out.println(universityName);
            universityList.displayModules();
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
            throw new InvalidUserCommandException("Error: No university list named " + input + " is found.");
        }
        return myManager.get(input);
    }

    public HashMap<String, UserUniversityList> getMyManager() {
        return myManager;
    }

    public void setMyManager(HashMap<String, UserUniversityList> myManager) {
        this.myManager = myManager;
    }

    public boolean containsKey(String inputSchool) {
        return myManager.containsKey(inputSchool);
    }

    public UserUniversityList getUserUniversityList(String input) {
        return myManager.get(input);
    }

    public TimetableManager getTtManager() {
        return ttManager;
    }

    public void setTtManager(TimetableManager ttManager) {
        this.ttManager = ttManager;
    }
}