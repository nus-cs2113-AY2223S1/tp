package seedu.duke.parser;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.timetable.TimetableManager;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;
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
    private static boolean isUniStorage;

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
     * Method to convert saved file content in data/uni_info.txt into a HashMap,
     * which is to be used as a constructor for UserUniversityListManager
     * so that user can load the saved information into a usable format when using the app
     * @param fileContent String with previously saved information of
     *                    user's saved list of universities and modules
     * @return HashMap, with Key = Partner University Name
     *              and Value = UserUniversityList, which stores modules information corresponding to the school
     * @throws InvalidUserStorageFileException when the String in data/uni_info.txt is in the wrong format
     *              ie. file corrupted between now and last saved
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
     * @param output starting with backslash
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
     *              where universities are separated by "/",
     *              module information is separated by ";"
     *              and each line is separated by "%"
     */
    private static String addUniversitiesToOutputString(UserUniversityListManager uniList) {
        String output = "";
        for (Map.Entry<String, UserUniversityList> pair : uniList.getMyManager().entrySet()) {
            UserUniversityList uni = pair.getValue();
            output += "/" + uni.getUniversityName() + "%" + "\n";
            output += uni.getUniversityCountry()  + "%" + "\n";
            output += addFavouritesToOutputString(uni);
            ArrayList<UserModuleMapping> modules = uni.getMyModules().getModules();
            output += addModulesToOutputString(modules);
        }
        return output;
    }

    /**.
     * Method to add 'T' if university is part of user's favourite list
     * and 'F' if university is not part of user's favourite list
     * @param uni university in user's university list
     * @return string to add to output string, indicating if the university is part of user's favourite list
     */
    private static String addFavouritesToOutputString(UserUniversityList uni) {
        String output = "";
        output += uni.isFavourite() ? 'T' : 'F';
        output += "%" + "\n";
        return output;
    }

    /**.
     * Method to add modules to output string, for a particular university
     * @param modules user's saved list of modules for a particular university
     * @return String with all information of user's list of modules for the particular university
     *              where module information is separated by ";"
     *              and each line is separated by "%"
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
     * Method to check if file content in data/uni_info.txt is empty
     * @param fileContent string from data/uni_info.txt
     * @return true if file content is empty
     */
    private static boolean isFileContentEmpty(String fileContent) {
        return fileContent.equals("");
    }

    /**.
     * Method to split universities in file content, using regex "/"
     * @param fileContent string from data/uni_info.txt
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
     * @throws InvalidUserStorageFileException when the String in data/uni_info.txt is in the wrong format
     */
    private static void getUniversityInfoFromString(String[] unis, HashMap<String, UserUniversityList> myManager)
            throws InvalidUserStorageFileException {
        for (String uni: unis) {
            String[] items = splitLineInFileContent(uni);
            if (!isValidUniFormat(items)) {
                throw new InvalidUserStorageFileException("Invalid file format");
            }
            String puName = items[0];
            String puCountry = items[1];
            String isFavourite = items[2];
            UserUniversityList newUni = new UserUniversityList(puName);
            UserModuleMappingList moduleList = new UserModuleMappingList();
            getModuleInfoFromString(items, moduleList, puCountry);
            newUni.setMyModules(moduleList);
            setFavourite(newUni, isFavourite);
            myManager.put(puName, newUni);
        }
    }

    private static boolean isValidUniFormat(String[] items) {
        return items.length >= 3 && (items[2].equals("T") || items[2].equals("F"));
    }

    /**.
     * Method to indicate in UserUniversityList that this particular university is part of
     * user's favourite list previously
     * @param newUni university that is part of user's university list
     * @param isFavourite 'T' if university is part of user's favourite list,
     *                    'F' otherwise
     */
    private static void setFavourite(UserUniversityList newUni, String isFavourite) {
        if (isFavourite.equals("T")) {
            newUni.setFavourite(true);
        } else {
            newUni.setFavourite(false);
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
     * @throws InvalidUserStorageFileException when the String in data/uni_info.txt is in the wrong format
     */
    private static void getModuleInfoFromString(String[] items, UserModuleMappingList moduleList, String puCountry)
            throws InvalidUserStorageFileException {
        for (int i = 3; i < items.length; ++i) {
            assert items.length > 1 : "This university has at least one module saved";
            String[] details = splitModuleInformationInFileContent(items[i]);
            if (!isValidModulesFormat(details)) {
                throw new InvalidUserStorageFileException("Invalid file format");
            }
            UserModuleMapping userModule = new UserModuleMapping(details[0], details[1],
                    details[3], details[4], details[5], details[2], items[0], puCountry);
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
     * Method to check if module information is saved in a valid format in data/uni_info.txt
     * ie. must have 6 fields of information, corresponding to each module
     * @param details array of strings, holding module information
     * @return true if it is a valid format
     */
    private static boolean isValidModulesFormat(String[] details) {
        return details.length == 6;
    }

    /**.
     * Method to get user's saved lists of universities and modules from data/uni_info.txt
     * and convert it into usable format in UserUniversityListManager
     * @return new UserUniversityListManager object, with saved universities and modules information
     *              or empty new UserUniversityListManager object, if error has occurred while loading file
     */
    public static UserUniversityListManager getSavedLists() {
        try {
            isUniStorage = true;
            String fileContent = UserStorage.loadFile(isUniStorage);
            return new UserUniversityListManager(fileContent);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
            System.out.println("Restarting with empty University List Manager");
        }
        return new UserUniversityListManager();
    }

    /**.
     * Method to store user's list of universities and modules into data/uni_info.txt
     * @param userUniversityListManager user's list of interested universities and modules
     */
    public static void storeCreatedLists(UserUniversityListManager userUniversityListManager) {
        try {
            String fileContent = convertUniversityListIntoFileContent(userUniversityListManager);
            isUniStorage = true;
            UserStorage.saveFile(fileContent, isUniStorage);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**.
     * Method to convert timetable information into String to save to file
     * Each university's timetable information is separated by "/"
     * Each line is separated by "%"
     * Each module information is separated by ";"
     * @param timetableManager stores all timetables information
     * @return output String to save to file
     */
    private static String convertTimetableIntoFileContent(TimetableManager timetableManager) {
        logger.log(Level.INFO, "Start converting TimetableManager to String");
        if (isTimetableManagerEmpty(timetableManager)) {
            return "";
        }
        String output = addTimetableToOutputString(timetableManager);
        output = removeFirstBackSlash(output);
        logger.log(Level.INFO, "End of conversion to String from Timetable Manager");
        return output;
    }

    /**.
     * Method to add Timetable information to output String
     * @param timetableManager stores all timetables information
     * @return output String with all timetables information
     */
    private static String addTimetableToOutputString(TimetableManager timetableManager) {
        String output = "";
        for (Map.Entry<String, Timetable> pair : timetableManager.getTimetableManager().entrySet()) {
            Timetable timetable = pair.getValue();
            String uniName = pair.getKey();
            output += "/" + uniName + "%" + "\n";
            output += addLessonTimingsToOutputString(timetable);
        }
        return output;
    }

    /**.
     * Method to add all lesson timings for the same school to output String
     * @param timetable stores timetable information
     * @return output String with all timetable information for one school
     */
    private static String addLessonTimingsToOutputString(Timetable timetable) {
        String output = "";
        for (Map.Entry<String, ArrayList<Lesson>> entry : timetable.getTimetable().entrySet()) {
            ArrayList<Lesson> listOfLessons = entry.getValue();
            output += addSingleLessonTimingToOutputString(listOfLessons);
        }
        return output;
    }

    /**.
     * Method to add all timing details for the same day to output String
     * @param listOfLessons stores all lessons information for the same school on the same day
     * @return output String with all timetable information for the same day
     */
    private static String addSingleLessonTimingToOutputString(ArrayList<Lesson> listOfLessons) {
        String output = "";
        for (Lesson lesson : listOfLessons) {
            output += lesson.getCode() + ";";
            output += lesson.getTitle() + ";";
            output += lesson.getCredit() + ";";
            output += lesson.getUniversity().getCountry() + ";";
            output += lesson.getDay() + ";";
            output += lesson.getStartTime() + ";";
            output += lesson.getEndTime() + "%\n";
        }
        return output;
    }

    /**.
     * Method to check if timetableManager is empty
     * @param timetableManager stores all timestables information
     * @return true if timetableManager is empty, false otherwise
     */
    private static boolean isTimetableManagerEmpty(TimetableManager timetableManager) {
        return timetableManager.getTimetableManager().size() == 0;
    }

    /**.
     * Method to convert file content in data/timetable_info.txt into TimetableManager
     * so that user can load the saved information into a usable format when using the app
     * @param fileContent String with previously saved information of user's saved list of timetable information
     * @return TimetableManager which stores all timetables information
     * @throws InvalidUserStorageFileException When the string in data/timetable_info.txt is in the wrong format
     *              ie. file corrupted between now and last saved
     */
    public static TimetableManager convertFileContentIntoTimetable(String fileContent)
            throws InvalidUserStorageFileException {
        logger.log(Level.INFO, "Start converting String to Timetable Manager");
        TimetableManager timetableManager = new TimetableManager();
        if (isFileContentEmpty(fileContent)) {
            return timetableManager;
        }
        String[] unis = splitUniversitiesInFileContent(fileContent);
        assert unis.length >= 1 : "at least one university exists in the file content";
        getTimetableInfoFromString(unis, timetableManager);
        logger.log(Level.INFO, "End of conversion to TimetableManager from String");
        return timetableManager;
    }

    /**.
     * Method to get timetable information from file content
     * @param unis array of Strings, each representing timetable information for a particular school
     * @param timetableManager stores all timetables information
     * @throws InvalidUserStorageFileException When the string in data/timetable_info.txt is in the wrong format
     *              ie. file corrupted between now and last saved
     */
    private static void getTimetableInfoFromString(String[] unis, TimetableManager timetableManager)
            throws InvalidUserStorageFileException {
        for (String uni: unis) {
            String[] items = splitLineInFileContent(uni);
            String puName = items[0];
            timetableManager.createTimetable(puName, true);
            getLessonTimingsInfoFromString(items, puName, timetableManager);
        }
    }

    /**.
     * Method to get lesson timings information from file content
     * @param items array of Strings, each representing module information for a particular lesson
     * @param puName Partner University's name
     * @param timetableManager stores all timetables information
     * @throws InvalidUserStorageFileException When the string in data/timetable_info.txt is in the wrong format
     *              ie. file corrupted between now and last saved
     */
    private static void getLessonTimingsInfoFromString(String[] items, String puName, TimetableManager timetableManager)
            throws InvalidUserStorageFileException {
        for (int i = 1; i < items.length; ++i) {
            String[] details = splitModuleInformationInFileContent(items[i]);
            if (!isValidTimetableFormat(details)) {
                throw new InvalidUserStorageFileException("Invalid file format");
            }
            String moduleCode = details[0];
            String moduleTitle = details[1];
            String moduleCredit = details[2];
            String puCountry = details[3];
            String day = details[4];
            String startTime = details[5];
            String endTime = details[6];
            try {
                University pu = new University(puName, puCountry);
                Lesson lesson = new Lesson(moduleCode, moduleTitle, moduleCredit, pu, day, startTime, endTime);
                timetableManager.addLesson(lesson, true);
            } catch (InvalidModuleException | InvalidUniversityException e) {
                Ui.printExceptionMessage(e);
            }
        }
    }

    private static boolean isValidTimetableFormat(String[] details) {
        return details.length == 7;
    }

    /**.
     * Method to get user's saved lists of timetables from uni/timetable_info.txt
     * and convert it into usable format in TimetableManager
     * @return new TimetableManager object, with saved timetable information
     *              or empty new TimetableManager object, if error has occurred while loading file
     */
    public static TimetableManager getSavedTimetables() {
        try {
            isUniStorage = false;
            String fileContent = UserStorage.loadFile(isUniStorage);
            return convertFileContentIntoTimetable(fileContent);
        } catch (IOException | InvalidUserStorageFileException e) {
            Ui.printExceptionMessage(e);
            System.out.println("Restarting with empty Timetable Manager");
        }
        return new TimetableManager();
    }

    /**.
     * Method to store user's list of timetables into data/timetable_info.txt
     * @param timetableManager user's list of timetables
     */
    public static void storeTimetable(TimetableManager timetableManager) {
        try {
            String fileContent = convertTimetableIntoFileContent(timetableManager);
            isUniStorage = false;
            UserStorage.saveFile(fileContent, isUniStorage);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
