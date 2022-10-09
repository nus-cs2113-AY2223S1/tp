package seedu.duke.user;


/**
 * Class to store the interested modules that a user is for a particular university.
 */

public class UserUniversityList {
    private UserModuleList myModules;
    private String universityName;

    public UserUniversityList(String universityName) {
        this.universityName = universityName;
    }

    public void addModule(UserModule input) {
        myModules.addModule((input));
    }

    /**
     * Method to delete module based on puCode.
     * @param puCode input puCode
     */
    public void deleteModule(String puCode) {
        myModules.deleteModuleByPUCode(puCode);
    }

    public void deleteModule(int index) {
        myModules.deleteModule(index);
    }

    public void displayModules() {
        myModules.displayAll();
    }

    public UserModuleList getMyModules() {
        return myModules;
    }

    public void setMyModules(UserModuleList myModules) {
        this.myModules = myModules;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
