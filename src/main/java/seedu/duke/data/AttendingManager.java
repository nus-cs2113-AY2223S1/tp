package seedu.duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import seedu.duke.Timetable;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

/*
 * ### Public Functions: ###
 * initAttendingDataFile()
 * addAttending(Lesson lesson, String moduleCode)
 * deleteAttending(Module module)
 * setAttending(Lesson lessonToSet, String moduleCode)
 * attendingExists(Lesson lesson, String moduleCode)
 * loadAttendingIntoTimetable()
 * loadAttendingIntoDataList()
 * 
 * ### Helper (private) Functions: ###
 * rewriteAttendingData()
 * removeModuleFromDataList(Module module)
 */

public class AttendingManager {
    public static ArrayList<String> attendingDataList = new ArrayList<String>();
    static String currentSemester;
    static String dataDirectoryPath;

    /*
     * Creates AttendingData.txt if doesnt exist
     */
    public static void initAttendingDataFile() {
        currentSemester = DataManager.getCurrentSem();
        dataDirectoryPath = DataManager.getDirPath();

        try {
            File attendingLessonDataFile = new File(dataDirectoryPath + "/AttendingData.txt");
            if (!attendingLessonDataFile.exists()) {
                attendingLessonDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating AttendingData.txt");
        }
    }

    /*
     * Erases data in attendingDataFile and writes data from attendingDataList into attendingDataFile
     */
    private static void rewriteAttendingData() {
        try {
            // Erase Data
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/AttendingData.txt");
            myWriter.write("");
            myWriter.close();

            myWriter = new FileWriter(dataDirectoryPath + "/AttendingData.txt", true);
            for (String line : attendingDataList) {
                myWriter.write(line + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to erase AttendingData.txt");
        }
    }

    /*
     * Removes all attendingLesson for a given module from AttendingDataList
     */
    private static void removeModuleFromDataList(Module module) {
        String moduleCode = module.getModuleCode();
        String[] currLine;
        int index = 0;

        while (index < attendingDataList.size()) {
            currLine = attendingDataList.get(index).split("\\|");
            if (currLine[0].equals(moduleCode)) {
                attendingDataList.remove(index);
            } else {
                index++;
            }
        }
    }

    /*
     * Removes all instances of attendingLessons from attendingDataList and attendingDataFile
     */
    public static void deleteAttending(Module module) {
        removeModuleFromDataList(module);
        rewriteAttendingData();
    }

    /*
     * Checks if there is an existing AttendingLesson in attendingDataList for a given module and lesson
     */
    public static boolean attendingExists(Lesson lesson, String moduleCode) {
        for (int i = 0; i < attendingDataList.size(); i++) {
            String[] currAttendingLessonLineList = attendingDataList.get(i).split("\\|");
            if (currAttendingLessonLineList[0].equals(moduleCode)
                    && currAttendingLessonLineList[1].equals(lesson.getLessonType())) {
                return true;
            }
        }
        return false;
    }

    /*
    * Adds an attendingLesson into AttendingData.txt in the following format:
    * <ModuleCode>|<lessonType>|<day>|<start>|<end>\n
    */
    public static void addAttending(Lesson lesson, Module module) {
        String moduleCode = module.getModuleCode();
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/AttendingData.txt", true);
            String line = moduleCode + "|" + lesson.getLessonType() + "|" + lesson.getDay()
                    + "|" + lesson.getStartTime() + "|" + lesson.getEndTime()
                    + "|" + lesson.getClassNumber();
            myWriter.write(line + "\n");
            attendingDataList.add(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to add module to AttendingData.txt");
        }
    }

    /*
     * Replaces a current attendingLesson in AttendingDataFile with a new attendingLesson
     */
    public static void setAttending(Lesson lessonToSet, String moduleCode) {

        String currLine;

        for (int i = 0; i < attendingDataList.size(); i++) {
            currLine = attendingDataList.get(i);
            String[] currLineList = currLine.split("\\|");
            if (currLineList[0].equals(moduleCode) && currLineList[1].equals(lessonToSet.getLessonType())) {
                String newLine = moduleCode + "|" + lessonToSet.getLessonType() + "|" + lessonToSet.getDay()
                        + "|" + lessonToSet.getStartTime() + "|" + lessonToSet.getEndTime()
                        + "|" + lessonToSet.getClassNumber();
                attendingDataList.set(i, newLine);
                break;
            }
        }
        rewriteAttendingData();
    }

    /*
     * Loads attendingLessons from attendingDataList into Modules in Timetable
     */
    public static void loadAttendingIntoTimetable() {

        List<Module> moduleList = Timetable.getListOfModules();

        String[] currLine;

        Module currModule;
        String moduleCode;
        int moduleIndex = 0;

        for (int i = 0; i < attendingDataList.size(); i++) {
            currLine = attendingDataList.get(i).split("\\|");

            currModule = moduleList.get(moduleIndex);
            moduleCode = currModule.getModuleCode();

            String lessonType = currLine[1];
            String lessonDay = currLine[2];
            String lessonStart = currLine[3];
            String lessonEnd = currLine[4];
            String classNumber = currLine[5];

            if (!currLine[0].equals(moduleCode)) {
                moduleIndex++;
                if (moduleIndex >= moduleList.size()) {
                    break;
                }
                currModule = moduleList.get(moduleIndex);
                moduleCode = currModule.getModuleCode();
            }
            Lesson newLesson = new Lesson(lessonDay, lessonStart, lessonEnd, lessonType, classNumber);
            currModule.replaceAttending(newLesson, i);
        }
    }

    /*
     * Loads attendingLessons from attendingDataFile into attendingDataList
     */
    public static void loadAttendingIntoDataList() {
        // Initiate readers
        FileReader attendingLessonFileReader;
        BufferedReader attendingLessonBufferedReader;

        // Get dataFile
        File attendingLessonDataFile = new File(dataDirectoryPath + "/AttendingData.txt");

        // Variables for iterating through data files
        String currLine;

        //Loading
        try {
            attendingLessonFileReader = new FileReader(attendingLessonDataFile);
            attendingLessonBufferedReader = new BufferedReader(attendingLessonFileReader);
            
            //Load attendingLessonData into attendingDataList
            while ((currLine = attendingLessonBufferedReader.readLine()) != null) {
                attendingDataList.add(currLine);
            }

            //close readers
            attendingLessonFileReader.close();
            attendingLessonBufferedReader.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to load attendingLessons from AttendingData.txt");
        }
    }
}
