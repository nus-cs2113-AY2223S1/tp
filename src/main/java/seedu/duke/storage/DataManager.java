package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;

import seedu.duke.Exceptions;
import seedu.duke.timetable.Timetable;

public class DataManager {
    protected static String currSemester;
    protected static String dataDirectoryPath;

    public static String getCurrentSem() {
        return currSemester;
    }

    public static String getDirPath() {
        return dataDirectoryPath;
    }

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

    public static void makeSave() {
        ModuleManager.saveModules();
        AttendingManager.saveAttendingIntoDataList();
        AttendingManager.saveAttendingData();
    }

    public static void resetDataFiles() {
        ModuleManager.deleteDataFile(dataDirectoryPath);
        AttendingManager.deleteDataFile(dataDirectoryPath);
        File dataDir = new File(dataDirectoryPath);
        Timetable.clearData();
        dataDir.delete();
    }
}
