package seedu.duke.userstorage;

import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import java.io.IOException;
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
            ArrayList<UserModuleMapping> modules = uni.getMyModules().getModules();
            for (UserModuleMapping module : modules) {
                assert modules.size() > 0 : "at least one module in this university";
                output += module.getPuCode() + ";";
                output += module.getPuTitle() + ";";
                output += module.getPuCredit() + ";";
                output += module.getNusCode() + ";";
                output += module.getNusTitle() + ";";
                output += module.getNusCredit() + "%" + "\n";
            }
        }
        if (output.equals("")) {
            return output;
        }
        assert output.length() > 0 : "output must have at least one uni";
        output = output.substring(1);   //remove first backslash
        logger.log(Level.INFO, "End of conversion to String from UserUniversityListManager");
        return output;
    }

    public static HashMap<String, UserUniversityList> convertFileContentIntoUniversityList(String fileContent)
            throws InvalidUserStorageFileException {
        logger.log(Level.INFO, "Start converting String to UserUniversityListManager");
        HashMap<String, UserUniversityList> myManager = new HashMap<String, UserUniversityList>();
        String[] unis = fileContent.split("/");
        if (unis.length == 1 && unis[0].equals("")) {
            return myManager;
        }

        assert unis.length >= 1 : "at least one university exists in the file content";
        for (String uni: unis) {
            String[] items = uni.split("%");
            String uniName = items[0];
            UserUniversityList uniList = new UserUniversityList(uniName);
            UserModuleMappingList moduleList = new UserModuleMappingList();
            for (int i = 1; i < items.length; ++i) {
                assert items.length > 1 : "This university has at least one module saved";
                String[] details = items[i].split(";");
                if (details.length != 6) {
                    throw new InvalidUserStorageFileException("Invalid file format");
                }
                UserModuleMapping userModule = new UserModuleMapping(details[0], details[1],
                        details[3], details[4], details[5], details[2], uniName, "test");
                moduleList.addModule(userModule, true);
            }
            uniList.setMyModules(moduleList);
            myManager.put(uniName, uniList);
        }
        logger.log(Level.INFO, "End of conversion to UserUniversityListManager from String");
        return myManager;
    }

    public static UserUniversityListManager getSavedLists() {
        try {
            String fileContent = UserStorage.loadFile();
            return new UserUniversityListManager(fileContent);
        } catch (IOException e) {
            System.out.println("Error, IOException has occurred");
        }
        return new UserUniversityListManager();
    }

    public static void storeCreatedLists(UserUniversityListManager userUniversityListManager) {
        try {
            String fileContent = convertUniversityListIntoFileContent(userUniversityListManager);
            UserStorage.saveFile(fileContent);
        } catch (IOException e) {
            System.out.println("IOException has occurred");
        }
    }
}
