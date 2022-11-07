package seedu.duke.commands;

import seedu.duke.UI;
import seedu.duke.commands.nusmodsapi.Nusmods;


import java.io.IOException;

/**
 * Command class for managing the process of getting info on a particular module.
 */
public class CommandInfoModule {

    /**
     * Begins the process of getting module code and info on the corresponding module.
     *
     * @return A response string to be printed by UI class to the user.
     */
    public static String findModule() {
        Nusmods mods = new Nusmods();
        String[] found = new String[3];
        String moduleCode = UI.getModuleCodeFromUser();
        try {
            found = mods.getModuleInfo(moduleCode);
        } catch (IOException e) {
            UI.printResponse("Some IO errors has occurred, please try again");
        } catch (InterruptedException e) {
            UI.printResponse("The API request was interrupted, please try again.");
        }
        return formatFoundModules(found);
    }

    /**
     * Returns a string with information found presented in the desired format.
     *
     * @param found Information on the queried module.
     * @return String in the correct format for the user.
     */
    private static String formatFoundModules(String[] found) {
        StringBuilder message = new StringBuilder();
        message.append("Here are some information about the module:\n");
        message.append("Module Code: " + found[0] + "\n");
        message.append("Module Name: " + found[1] + "\n");
        message.append("Module Description: " + found[2] + "\n");
        return message.toString();
    }
}
