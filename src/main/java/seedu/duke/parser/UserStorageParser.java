package seedu.duke.parser;

import seedu.duke.command.Database;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.exceptions.InvalidTimingException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidTimeFormatException;
import seedu.duke.exceptions.InvalidLessonDayException;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleMapping;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;
import seedu.duke.userstorage.UserStorage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserStorageParser {
    /**.
     * Method to store information from UserUniversityListManager to text file, for a specific univesity
     * @param uniName Partner university name
     * @param userUniversityListManager Lists of partner universities that user is interested in
     */
    public static void storeInfoToUserStorageByUni(String uniName,
                                                   UserUniversityListManager userUniversityListManager) {
        try {
            String fileContent = convertUniIntoFileContent(uniName, userUniversityListManager);
            UserStorage.saveFile(uniName, fileContent);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**.
     * Method to delete text file given an input university name
     * @param uniName Partner university name
     * @param fromStartUp Boolean variable that indicates if the method is called from program start up
     */
    public static void deleteUserStorageByUni(String uniName, boolean fromStartUp) {
        UserStorage.deleteFile(uniName, fromStartUp);
    }

    /**.
     * Method to convert university information to a string to store into the text file
     * @param uniName Partner university name
     * @param userUniversityListManager Lists of partner universities that user is interested in
     * @return String to be saved in text file
     */
    private static String convertUniIntoFileContent(String uniName,
                                                   UserUniversityListManager userUniversityListManager) {
        String fileContent = "";
        fileContent += addFavouritesToOutputString(userUniversityListManager.getUserUniversityList(uniName));
        fileContent += addModulesToOutputString(userUniversityListManager.getMyManager().get(uniName)
                .getMyModules().getModules());
        fileContent += "#" + addLessonTimingsToOutputString(userUniversityListManager.getTtManager()
                .getTimetableByUniversityName(uniName));
        return fileContent;
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
            output += lesson.getDay() + ";";
            output += lesson.getStartTime() + ";";
            output += lesson.getEndTime() + "%\n";
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
            if (!module.getComment().equals("") && module.getComment() != null) {
                output += module.getComment();
            }
            output += "%\n";
        }
        return output;
    }

    /**.
     * Method to convert saved information in all uncorrupted text files to a single UserUniversityListManager
     * @return UserUniversityListManager, restored with saved information from all uncorrupted text files
     */
    public static UserUniversityListManager getSavedLists() {
        try {
            HashMap<String, String> filePaths = UserStorage.getFilePaths();
            UserUniversityListManager userUniversityListManager = new UserUniversityListManager();
            if (filePaths.isEmpty()) {
                return userUniversityListManager;
            }
            HashMap<String, String> newFilePaths = new HashMap<>();
            for (HashMap.Entry<String, String> entry : filePaths.entrySet()) {
                extractInfoFromTextFile(newFilePaths, filePaths, userUniversityListManager, entry);
            }
            UserStorage.setFilePaths(newFilePaths);
            return userUniversityListManager;
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
        return new UserUniversityListManager();
    }

    /**.
     * Method to extract information from a text file
     * @param newFilePaths New hashmap containing valid files
     * @param filePaths Hashmap containing all files on start up
     * @param userUniversityListManager Lists of partner universities that user is interested in
     * @param entry Hashmap entry, mapping university name to file path
     * @throws IOException if input/output operations fail or are interrupted
     */
    private static void extractInfoFromTextFile(HashMap<String, String> newFilePaths, HashMap<String, String> filePaths,
                                                 UserUniversityListManager userUniversityListManager, 
                                                 Map.Entry<String, String> entry) throws IOException {
        String fileContent = UserStorage.loadFile(entry.getKey());
        String uniName = entry.getKey();
        String[] splitFileContent = fileContent.split("#");
        if (splitFileContent.length > 2) {
            deleteUserStorageByUni(uniName, true);
            System.out.println("Invalid file format\n" + getDeleteMessage(uniName));
            return;
        }
        String fileContentForUniList = splitFileContent[0];
        if (extractUniListInfoFromTextFile(newFilePaths, filePaths, userUniversityListManager, uniName,
                fileContentForUniList)) {
            return;
        }
        if (extractTimetableInfoFromTextFile(newFilePaths, filePaths, userUniversityListManager, uniName,
                splitFileContent)) {
            return;
        }
        handleLessonModuleCodeNotInSavedModules(uniName, splitFileContent);
    }

    /**.
     * Method to handle if lesson module code in text file does not exist in saved modules
     * @param uniName Partner university name
     * @param splitFileContent Array of strings containing text file information
     *                         splitFileContent[0] = modules information
     *                         splitFileContent[1] = timetable information
     */
    private static void handleLessonModuleCodeNotInSavedModules(String uniName, String[] splitFileContent) {
        try {
            lookForLessonModuleCodeInSavedModules(uniName, splitFileContent);
        } catch (InvalidUserStorageFileException e) {
            deleteUserStorageByUni(uniName, true);
            Ui.printExceptionMessage(e);
        }
    }

    /**.
     * Method to throw exception if lesson module code does not exist in saved modules
     * @param uniName Partner university name
     * @param splitFileContent Array of strings containing text file information
     *                         splitFileContent[0] = modules information
     *                         splitFileContent[1] = timetable information
     * @throws InvalidUserStorageFileException if lesson module code not found in saved modules
     */
    private static void lookForLessonModuleCodeInSavedModules(String uniName, String[] splitFileContent)
            throws InvalidUserStorageFileException {
        if (splitFileContent.length == 2) {
            String[] modules = splitFileContent[0].split("%");
            modules = Arrays.copyOfRange(modules, 1, modules.length);
            String[] lessons = splitFileContent[1].split("%");
            isLessonModuleCodeExistInSavedModules(uniName, modules, lessons);
        }
    }

    /**.
     * Method to check if lesson module code exists in saved modules
     * @param uniName Partner university name
     * @param modules String array containing module information
     * @param lessons String array containing lesson information
     * @throws InvalidUserStorageFileException if lesson module code not found in saved modules
     */
    private static void isLessonModuleCodeExistInSavedModules(String uniName, String[] modules, String[] lessons)
            throws InvalidUserStorageFileException {
        for (String lesson: lessons) {
            String lessonModuleCode = lesson.split(";")[0];
            boolean isFound = false;
            for (String module : modules) {
                String moduleCode = module.split(";")[0];
                if (moduleCode.equals(lessonModuleCode)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                throw new InvalidUserStorageFileException("Lesson module code not found in saved modules\n"
                + getDeleteMessage(uniName));
            }
        }
    }

    /**.
     * Method to extract timetable information from a text file given an input partner university
     * @param newFilePaths New hashmap containing valid files
     * @param filePaths Hashmap containing all files on start up
     * @param userUniversityListManager Lists of partner universities that user is interested in
     * @param uniName Partner university name
     * @param splitFileContent Array of strings containing text file information
     *                         splitFileContent[0] = modules information
     *                         splitFileContent[1] = timetable information
     * @return true if InvalidUserStorageException is caught, false otherwise
     */
    private static boolean extractTimetableInfoFromTextFile(HashMap<String, String> newFilePaths,
                                                         HashMap<String, String> filePaths,
                                                         UserUniversityListManager userUniversityListManager,
                                                         String uniName, String[] splitFileContent) {
        if (splitFileContent.length == 2) {
            String fileContentForTimetable = splitFileContent[1];
            try {
                Timetable timetable = convertFileContentIntoTimetable(fileContentForTimetable, uniName);
                userUniversityListManager.getTtManager().getTimetableManager().put(uniName, timetable);
                newFilePaths.put(uniName, filePaths.get(uniName));
            } catch (InvalidUserStorageFileException e) {
                deleteUserStorageByUni(uniName, true);
                userUniversityListManager.getMyManager().remove(uniName);
                Ui.printExceptionMessage(e);
                return true;
            }
        } else {
            userUniversityListManager.getTtManager().getTimetableManager().put(uniName, new Timetable());
        }
        return false;
    }

    /**.
     * Method to extract university list information from text file
     * @param newFilePaths New hashmap containing valid files
     * @param filePaths Hashmap containing all files on start up
     * @param userUniversityListManager Lists of partner universities that user is interested in
     * @param uniName Partner university name
     * @param fileContentForUniList String containing information for saved modules
     * @return true if InvalidUserStorageException is caught, false otherwise
     */
    private static boolean extractUniListInfoFromTextFile(HashMap<String, String> newFilePaths,
                                                          HashMap<String, String> filePaths,
                                                          UserUniversityListManager userUniversityListManager,
                                                          String uniName, String fileContentForUniList) {
        try {
            UserUniversityList uniList = convertFileContentIntoUniList(fileContentForUniList, uniName);
            userUniversityListManager.getMyManager().put(uniName, uniList);
            newFilePaths.put(uniName, filePaths.get(uniName));
        } catch (InvalidUserStorageFileException e) {
            deleteUserStorageByUni(uniName, true);
            Ui.printExceptionMessage(e);
            return true;
        }
        return false;
    }

    /**.
     * Method to convert string into university list
     * @param fileContent String containing information for saved modules
     * @param uniName Partner university name
     * @return UserUnivesityList, containing saved modules information
     * @throws InvalidUserStorageFileException if invalid file format
     */
    private static UserUniversityList convertFileContentIntoUniList(String fileContent, String uniName)
            throws InvalidUserStorageFileException {
        UserUniversityList newUni = new UserUniversityList(uniName);
        String[] items = splitLineInFileContent(fileContent);
        if (!isValidUniFormat(items)) {
            throw new InvalidUserStorageFileException("Invalid file format\n" + getDeleteMessage(uniName));
        }
        String isFavourite = items[0];
        UserModuleMappingList moduleList = new UserModuleMappingList();
        getModuleInfoFromString(items, moduleList, uniName);
        newUni.setMyModules(moduleList);
        setFavourite(newUni, isFavourite);
        return newUni;
    }

    /**.
     * Method to get String containing delete message
     * @param uniName Partner university name
     * @return String containing delete message with corresponding partner university name
     */
    private static String getDeleteMessage(String uniName) {
        return "Deleted university list and timetable for " + uniName + " from storage\n";
    }

    /**.
     * Method to convert string into timetable
     * @param fileContent String containing information for timetable
     * @param uniName Partner university name
     * @return Timetable containing various lessons
     * @throws InvalidUserStorageFileException if invalid file format or if module code not found in database
     */
    private static Timetable convertFileContentIntoTimetable(String fileContent, String uniName)
            throws InvalidUserStorageFileException {
        Timetable timetable = new Timetable();
        String[] lessons = splitLineInFileContent(fileContent);
        for (String lesson : lessons) {
            convertFileContentIntoLesson(uniName, timetable, lesson);
        }
        return timetable;
    }

    /**.
     * Method to convert string into lesson
     * @param uniName Partner university name
     * @param timetable Timetable containing various lessons
     * @param lesson Lesson containing lesson information
     * @throws InvalidUserStorageFileException if invalid file format or if module code not found in database
     */
    private static void convertFileContentIntoLesson(String uniName, Timetable timetable, String lesson)
            throws InvalidUserStorageFileException {
        String[] details = splitModuleInformationInFileContent(lesson);
        if (!isValidTimetableFormat(details)) {
            throw new InvalidUserStorageFileException("Invalid file format\n" + getDeleteMessage(uniName));
        }
        String moduleCode = details[0];
        ModuleMapping moduleMapping;
        try {
            moduleMapping = Database.findPuMapping(moduleCode, uniName);
        } catch (ModuleNotFoundException | UniversityNotFoundException e) {
            throw new InvalidUserStorageFileException("Invalid module code " + moduleCode + " for " + uniName + "\n"
                    + getDeleteMessage(uniName));
        }
        Module puModule = moduleMapping.getPartnerUniversityModule();
        String day = details[1];
        String startTime = details[2];
        String endTime = details[3];
        addLessonIntoTimetable(uniName, timetable, moduleCode, puModule, day, startTime, endTime);
    }

    /**.
     * Method to add lesson using file contents
     * @param uniName Partner university name
     * @param timetable Timetable containing various lessons
     * @param moduleCode Lesson module code
     * @param puModule Partner university module
     * @param day Lesson day
     * @param startTime Lesson start time
     * @param endTime Lesson end time
     * @throws InvalidUserStorageFileException if university, module or lesson is not in valid format
     */
    private static void addLessonIntoTimetable(String uniName, Timetable timetable, String moduleCode,
                                               Module puModule, String day, String startTime, String endTime)
                                                throws InvalidUserStorageFileException {
        Lesson newLesson;
        try {
            University pu = new University(uniName, puModule.getUniversity().getCountry());
            newLesson = new Lesson(moduleCode, puModule.getTitle(), puModule.getCredit(), pu,
                    day, startTime, endTime);
            Timetable.isValidDay(newLesson);
            Timetable.isValidTiming(newLesson);
            Timetable.isValidStartTime(newLesson);
            Timetable.isValidEndTime(newLesson);
        } catch (InvalidUniversityException | InvalidModuleException e) {
            throw new InvalidUserStorageFileException("Invalid file format\n" + getDeleteMessage(uniName));
        } catch (InvalidLessonDayException | InvalidTimingException | InvalidTimeFormatException | ParseException e) {
            throw new InvalidUserStorageFileException("Invalid lesson format\n" + getDeleteMessage(uniName));
        }
        timetable.addLesson(newLesson, true);
    }

    /**.
     * Method to check if lesson is in valid format ie. has exactly 4 fields
     * @param details Array of strings containing lesson information
     * @return true if details is size 4, otherwise false
     */
    private static boolean isValidTimetableFormat(String[] details) {
        return details.length == 4;
    }

    /**.
     * Method to split file content by their lines, indicated by '%'
     * @param fileContent String holding file content
     * @return fileContent split by '%'
     */
    private static String[] splitLineInFileContent(String fileContent) {
        return fileContent.split("%");
    }

    /**.
     * Method to check if university list is in valid format
     * @param items Array of strings containing university list information
     * @return true if items is at least size 1, and first item must be either 'T' or 'F'
     */
    private static boolean isValidUniFormat(String[] items) {
        return items.length >= 1 && (items[0].equals("T") || items[0].equals("F"));
    }

    /**.
     * Method to indicate in UserUniversityList that this particular university is part of
     * user's favourite list previously
     * @param newUni university that is part of user's university list
     * @param isFavourite 'T' if university is part of user's favourite list,
     *                    'F' otherwise
     */
    private static void setFavourite(UserUniversityList newUni, String isFavourite) {
        newUni.setFavourite(isFavourite.equals("T"));
    }

    /**.
     * Method to get module information from string, and store into UserModuleMappingList
     * @param items array of strings, where the first element is the partner university's name,
     *              followed by a list of PU modules that the user is interested in
     * @param moduleList list of PU modules that the user is interested in
     * @throws InvalidUserStorageFileException when the String in data/uni_info.txt is in the wrong format
     */
    private static void getModuleInfoFromString(String[] items, UserModuleMappingList moduleList, String puName)
            throws InvalidUserStorageFileException {
        for (int i = 1; i < items.length; ++i) {
            assert items.length > 1 : "This university has at least one module saved";
            String[] details = splitModuleInformationInFileContent(items[i]);
            if (!isValidModulesFormat(details)) {
                throw new InvalidUserStorageFileException("Invalid file format\n" + getDeleteMessage(puName));
            }
            String moduleCode = details[0];
            ModuleMapping moduleMapping;
            try {
                moduleMapping = Database.findPuMapping(moduleCode, puName);
            } catch (ModuleNotFoundException | UniversityNotFoundException e) {
                throw new InvalidUserStorageFileException("Invalid module code " + moduleCode + " for " + puName  + "\n"
                        + getDeleteMessage(puName));
            }
            Module puModule = moduleMapping.getPartnerUniversityModule();
            Module nusModule = moduleMapping.getNusModule();
            UserModuleMapping userModuleToAdd = new UserModuleMapping(puModule.getCode(),
                    puModule.getTitle(), nusModule.getCode(), nusModule.getTitle(),
                    nusModule.getCredit(), puModule.getCredit(), puModule.getUniversity().getName(),
                    puModule.getUniversity().getCountry());
            if (details.length == 2) {
                userModuleToAdd.setComment(details[1]);
            }
            moduleList.addModule(userModuleToAdd, true);
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
        return (details.length == 1 || details.length == 2) && !details[0].equals("");
    }
}
