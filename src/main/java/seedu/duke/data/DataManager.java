package seedu.duke.data;

import java.io.File;
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
        AttendingManager.initAttendingDataFile();
        ModuleManager.initModuleDataFile();
        LessonManager.initLessonDataFile();
    }

    /*
     * Removes all relevant data from all data files
     */
    public static void deleteModule(Module module) {
        AttendingManager.deleteAttending(module);
        ModuleManager.deleteModule(module);
        LessonManager.deleteLesson(module);
    }

    /*
     * Loads Timetable with info from all data files
     */
    public static void loadTimetableFromDataFile() {
        LessonManager.loadLessonIntoDataList();
        AttendingManager.loadAttendingIntoDataList();
        ModuleManager.loadModuleIntoTimetable();
        AttendingManager.loadAttendingIntoTimetable();
    }
}
