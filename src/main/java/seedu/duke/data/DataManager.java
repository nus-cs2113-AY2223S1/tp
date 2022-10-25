package seedu.duke.data;

import java.io.File;
import java.io.FileNotFoundException;

import seedu.duke.Exceptions;
import seedu.duke.module.Module;

public class DataManager {
    protected static String currSemester;
    protected static String dataDirectoryPath;

    public static String getCurrentSem() {
        return currSemester;
    }

    public static String getDirPath() {
        return dataDirectoryPath;
    }

    public static void initDataFile(String semester) {

        //Handle quit/invalid entry
        if (!semester.equals("1") && !semester.equals("2")) {
            return;
        }

        //Set global variables
        currSemester = semester;
        dataDirectoryPath = "./Sem" + currSemester + "DataDirectory";

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
        //AttendingManager.loadAttendingIntoTimetable();
        AttendingManager.loadNewAttendingOnStartUp();
    }

    public static void makeSave() {
        ModuleManager.saveModules();
        AttendingManager.saveAttendingIntoDataList();
        AttendingManager.saveAttendingData();
    }
}
