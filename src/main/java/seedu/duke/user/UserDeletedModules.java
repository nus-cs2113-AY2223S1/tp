package seedu.duke.user;

import seedu.duke.ui.Ui;

import java.util.ArrayDeque;

/**.
 * Class to store up to 5 modules (in a queue) that the user has recently deleted
 */
public class UserDeletedModules {
    private static ArrayDeque<UserModuleMapping> deletedModules;

    public UserDeletedModules() {
        deletedModules = new ArrayDeque<>();
    }

    /**.
     * Add a module to the deletedModules list
     * @param deletedModule Module to be added to deletedModules list
     */
    public static void addToDeletedModules(UserModuleMapping deletedModule) {
        if (deletedModules.size() == 5) {
            deletedModules.removeLast();
        }
        assert deletedModules.size() < 5 : "Number of deleted modules is less than 5";
        deletedModules.addFirst(deletedModule);
    }

    /**.
     * Prints out all modules in the deletedModules list
     */
    public static void displayAll() {
        if (deletedModules.size() == 0) {
            System.out.println("No modules in history");
        } else {
            System.out.print(Ui.printDeletedModulesHistory(deletedModules));
        }
    }
}
