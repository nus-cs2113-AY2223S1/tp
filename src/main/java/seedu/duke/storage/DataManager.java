package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;

import seedu.duke.Exceptions;
import seedu.duke.timetable.Timetable;

/**
 * Class for data management.
 */
public class DataManager {
    private static String currSemester;
    private static String dataDirectoryPath;

    public static String getCurrentSem() {
        return currSemester;
    }

    public static String getDirPath() {
        return dataDirectoryPath;
    }

    /**
     * Initialises data file.
     *
     * @param semester Semester of the timetable to be initiated.
     * @param dir Directory specifier.
     */
    public static void initDataFile(String semester, String dir) {

        //Handle quit/invalid entry
        if (!semester.equals("1") && !semester.equals("2")) {
            return;
        }

        //Set global variables
        currSemester = semester;
        dataDirectoryPath = "./Sem" + currSemester + dir;

        //Create directory
        File dataDir = new File(dataDirectoryPath);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        //Load data files
        ModuleManager.initModuleDataFile();
        AttendingManager.initAttendingDataFile();
    }

    /*
     * Loads Timetable with info from all data files
     */
    public static void loadTimetableFromDataFile() throws Exceptions.FileLoadException, FileNotFoundException {
        ModuleManager.loadModules();
        AttendingManager.loadAttendingIntoDataList();
        AttendingManager.loadNewAttendingOnStartUp();
    }

    /**
     * Saves the current timetable to textfile.
     */
    public static void makeSave() {
        ModuleManager.saveModules();
        AttendingManager.saveAttendingIntoDataList();
        AttendingManager.saveAttendingData();
    }

    /**
     * Clears and delete all data.
     */
    public static void resetDataFiles() {
        ModuleManager.deleteDataFile(dataDirectoryPath);
        AttendingManager.deleteDataFile(dataDirectoryPath);
        File dataDir = new File(dataDirectoryPath);
        Timetable.clearData();
        //dataDir.delete();
    }
}
