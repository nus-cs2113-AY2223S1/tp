package seedu.duke.parser;

import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;
import seedu.duke.userstorage.UserStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserStorageParser {
    private static Logger logger = Logger.getLogger("UserStorageParser");

    /**.
     * Method to convert UserUniversityListManager to a String
     * to be stored in a text file
     * '/' splits universities
     * '%' splits lines
     * ';' splits module details
     * @param uniList user's list of interested universities
     * @return a String that contains all universities and modules information
     */
    public static String convertUniversityListIntoFileContent(UserUniversityListManager uniList) {
        logger.log(Level.INFO, "Start converting UserUniversityListManager to String");
        if (isUserUniversityListManagerEmpty(uniList)) {
            return "";
        }
        String output = addUniversitiesToOutputString(uniList);
        output = removeFirstBackSlash(output);
        logger.log(Level.INFO, "End of conversion to String from UserUniversityListManager");
        return output;
    }

    /**.
     * Method to convert saved file content in data/myinfo.txt into a HashMap,
     * which is to be used as a constructor for UserUniversityListManager
     * so that user can load the saved information into a usable format when using the app
     * @param fileContent String with previously saved information of
     *                    user's saved list of universities and modules
     * @return HashMap, with Key = Partner University Name
     * and Value = UserUniversityList, which stores modules information corresponding to the school
     * @throws InvalidUserStorageFileException when the String in data/myinfo.txt is in the wrong format
     * ie. file corrupted between now and last saved
     */
    public static HashMap<String, UserUniversityList> convertFileContentIntoUniversityList(String fileContent)
            throws InvalidUserStorageFileException {
        logger.log(Level.INFO, "Start converting String to UserUniversityListManager");
        HashMap<String, UserUniversityList> myManager = new HashMap<String, UserUniversityList>();
        if (isFileContentEmpty(fileContent)) {
            return myManager;
        }
        String[] unis = splitUniversitiesInFileContent(fileContent);
        assert unis.length >= 1 : "at least one university exists in the file content";
        getUniversityInfoFromString(unis, myManager);
        logger.log(Level.INFO, "End of conversion to UserUniversityListManager from String");
        return myManager;
    }

    /**.
     * Method to remove first backslash from output string
     * as backslash is meant to separate universities
     * @param output, starting with backslash
     * @return output with first backslash removed, to be stored into text file
     */
    private static String removeFirstBackSlash(String output) {
        return output.substring(1);
    }

    /**.
     * Method to check if UserUniversityListManager is empty
     * (ie. no universities saved)
     * @param uniList user's saved list of universities and modules
     * @return true if the list is empty
     */
    private static boolean isUserUniversityListManagerEmpty(UserUniversityListManager uniList) {
        return uniList.getMyManager().size() == 0;
    }

    /**.
     * Method to add universities and their respective modules to output string
     * @param uniList user's saved list of universities and modules
     * @return String with all information of user's list of universities and modules
     * where universities are separated by "/",
     * module information is separated by ";"
     * and each line is separated by "%"
     */
    private static String addUniversitiesToOutputString(UserUniversityListManager uniList) {
        String output = "";
        for (Map.Entry<String, UserUniversityList> pair : uniList.getMyManager().entrySet()) {
            UserUniversityList uni = pair.getValue();
            output += "/" + uni.getUniversityName() + "%" + "\n";
            ArrayList<UserModuleMapping> modules = uni.getMyModules().getModules();
            output += addModulesToOutputString(modules);
        }
        return output;
    }

    /**.
     * Method to add modules to output string, for a particular university
     * @param modules user's saved list of modules for a particular university
     * @return String with all information of user's list of modules for the particular university
     * where module information is separated by ";"
     * and each line is separated by "%"
     */
    private static String addModulesToOutputString(ArrayList<UserModuleMapping> modules) {
        String output = "";
        for (UserModuleMapping module : modules) {
            assert modules.size() > 0 : "at least one module in this university";
            output += module.getPuCode() + ";";
            output += module.getPuTitle() + ";";
            output += module.getPuCredit() + ";";
            output += module.getNusCode() + ";";
            output += module.getNusTitle() + ";";
            output += module.getNusCredit() + "%" + "\n";
        }
        return output;
    }

    /**.
     * Method to check if file content in data/myinfo.txt is empty
     * @param fileContent string from data/myinfo.txt
     * @return true if file content is empty
     */
    private static boolean isFileContentEmpty(String fileContent) {
        return fileContent.equals("");
    }

    /**.
     * Method to split universities in file content, using regex "/"
     * @param fileContent string from data/myinfo.txt
     * @return array of strings, holding information from different universities
     */
    private static String[] splitUniversitiesInFileContent(String fileContent) {
        return fileContent.split("/");
    }

    /**.
     * Method to get university information from string, and store into HashMap
     * @param unis array of strings, holding information from different universities
     * @param myManager HashMap to store university name and modules information,
     *                  to be used to construct UserUniversityListManager
     * @throws InvalidUserStorageFileException when the String in data/myinfo.txt is in the wrong format
     */
    private static void getUniversityInfoFromString(String[] unis, HashMap<String, UserUniversityList> myManager)
            throws InvalidUserStorageFileException {
        for (String uni: unis) {
            String[] items = splitLineInFileContent(uni);
            String uniName = items[0];
            UserUniversityList uniList = new UserUniversityList(uniName);
            UserModuleMappingList moduleList = new UserModuleMappingList();
            getModuleInfoFromString(items, moduleList);
            uniList.setMyModules(moduleList);
            myManager.put(uniName, uniList);
        }
    }

    /**.
     * Method to split file content by line, using regex "%"
     * @param uni string containing PU information, separated by "%"
     * @return array of strings, holding PU information ie. PU name and modules
     */
    private static String[] splitLineInFileContent(String uni) {
        return uni.split("%");
    }

    /**.
     * Method to get module information from string, and store into UserModuleMappingList
     * @param items array of strings, where the first element is the partner university's name,
     *              followed by a list of PU modules that the user is interested in
     * @param moduleList list of PU modules that the user is interested in
     * @throws InvalidUserStorageFileException when the String in data/myinfo.txt is in the wrong format
     */
    private static void getModuleInfoFromString(String[] items, UserModuleMappingList moduleList)
            throws InvalidUserStorageFileException {
        for (int i = 1; i < items.length; ++i) {
            assert items.length > 1 : "This university has at least one module saved";
            String[] details = splitModuleInformationInFileContent(items[i]);
            if (!isValidFormat(details)) {
                throw new InvalidUserStorageFileException("Invalid file format");
            }
            UserModuleMapping userModule = new UserModuleMapping(details[0], details[1],
                    details[3], details[4], details[5], details[2], items[0], "test");
            moduleList.addModule(userModule, true);
        }
    }

    /**.
     * Method to split module information, using regex ";"
     * @param moduleInfo string containing 6 fields of module information, separated by ";"
     * @return array of strings, holding module information ie. PU module code, PU module name etc.
     */
    private static String[] splitModuleInformationInFileContent(String moduleInfo) {
        return moduleInfo.split(";");
    }

    /**.
     * Method to check if module information is saved in a valid format in data/myinfo.txt
     * ie. must have 6 fields of information, corresponding to each module
     * @param details array of strings, holding module information
     * @return true if it is a valid format
     */
    private static boolean isValidFormat(String[] details) {
        return details.length == 6;
    }

    /**.
     * Method to get user's saved lists of universities and modules from data/myinfo.txt
     * and convert it into usable format in UserUniversityListManager
     * @return new UserUniversityListManager object, with saved universities and modules information
     * or empty new UserUniversityListManager object, if error has occurred while loading file
     */
    public static UserUniversityListManager getSavedLists() {
        try {
            String fileContent = UserStorage.loadFile();
            return new UserUniversityListManager(fileContent);
        } catch (IOException e) {
            System.out.println("Error, IOException has occurred");
            System.out.println("Restarting with empty University List Manager");
        }
        return new UserUniversityListManager();
    }

    /**.
     * Method to store user's list of universities and modules into data/myinfo.txt
     * @param userUniversityListManager user's list of interested universities and modules
     */
    public static void storeCreatedLists(UserUniversityListManager userUniversityListManager) {
        try {
            String fileContent = convertUniversityListIntoFileContent(userUniversityListManager);
            UserStorage.saveFile(fileContent);
        } catch (IOException e) {
            System.out.println("Error, IOException has occurred");
        }
    }
}
