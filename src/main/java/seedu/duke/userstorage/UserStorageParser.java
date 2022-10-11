package seedu.duke.userstorage;

import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.user.UserModule;
import seedu.duke.user.UserModuleList;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserStorageParser {
    private static Logger logger = Logger.getLogger("UserStorageParser");

    /**.
     * Function to convert UserUniversityListManager to a String
     * to be stored in a text file
     * '/' splits universities
     * '%' splits lines
     * ';' splits module details
     * @param uniList user's list of interested universities
     * @return a String that contains all universities and modules information
     */
    public static String convertUniversityListIntoFileContent(UserUniversityListManager uniList) {
        logger.log(Level.INFO, "Start converting UserUniversityListManager to String");
        String output = "";
        for (Map.Entry<String, UserUniversityList> pair : uniList.getMyManager().entrySet()) {
            UserUniversityList uni = pair.getValue();
            output += "/" + uni.getUniversityName() + "%" + "\n";
            ArrayList<UserModule> modules = uni.getMyModules().getModules();
            for (UserModule module : modules) {
                output += module.getPuCode() + ";";
                output += module.getPuTitle() + ";";
                output += module.getNusCode() + ";";
                output += module.getNusTitle() + ";";
                output += module.getNusCredit() + "%" + "\n";
            }
        }
        output = output.substring(1);   //remove first backslash
        logger.log(Level.INFO, "End of conversion to String from UserUniversityListManager");
        return output;
    }

    public static HashMap<String, UserUniversityList> convertFileContentIntoUniversityList(String fileContent)
            throws InvalidUserStorageFileException {
        logger.log(Level.INFO, "Start converting String to UserUniversityListManager");
        HashMap<String, UserUniversityList> myManager = new HashMap<String, UserUniversityList>();
        String[] unis = fileContent.split("/");
        for (String uni: unis) {
            String[] items = uni.split("%");
            String uniName = items[0];
            UserUniversityList uniList = new UserUniversityList(uniName);
            UserModuleList moduleList = new UserModuleList();
            for (int i = 1; i < items.length; ++i) {
                String[] details = items[i].split(";");
                if (details.length != 5) {
                    throw new InvalidUserStorageFileException("Invalid file format");
                }
                UserModule userModule = new UserModule(details[0], details[1], details[2], details[3], details[4]);
                moduleList.addModule(userModule);
            }
            uniList.setMyModules(moduleList);
            myManager.put(uniName, uniList);
        }
        logger.log(Level.INFO, "End of conversion to UserUniversityListManager from String");
        return myManager;
    }
}
