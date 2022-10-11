package seedu.duke.user;


/**
 * Class to store the interested modules that a user is for a particular university.
 */

public class UserUniversityList {
    private UserModuleMappingList myModules;
    private String universityName;

    public UserUniversityList(String universityName) {
        this.universityName = universityName;
        this.myModules = new UserModuleMappingList();
    }

    public void addModule(UserModuleMapping input) {
        myModules.addModule(input);
    }

    /**
     * Method to delete module based on puCode.
     * @param puCode input puCode
     */
    public void deleteModuleByPuCode(String puCode) {
        assert puCode.length() > 0 : "Deleting PU code cannot be empty";
        myModules.deleteModuleByPuCode(puCode);
    }

    public void deleteModule(int index) {
        myModules.deleteModule(index);
    }

    public void displayModules() {
        myModules.displayAll();
    }

    public UserModuleMappingList getMyModules() {
        return myModules;
    }

    public void setMyModules(UserModuleMappingList myModules) {
        this.myModules = myModules;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
