package seedu.duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
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
    public static ArrayList<String> attendingDataList = new ArrayList<>();
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
    public static void saveAttendingData() {
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/AttendingData.txt");

            for (String line : attendingDataList) {
                myWriter.write(line);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save to AttendingData.txt");
        }
    }

    /*
    * Adds an attendingLesson into AttendingData.txt in the following format:
    * <ModuleCode>|<lessonType>|<day>|<start>|<end>|<classNo>\n
    */
    private static void addAttending(Lesson lesson, String moduleCode) {
        String line = moduleCode + "|" + lesson.getLessonType() + "|" + lesson.getDay()
                + "|" + lesson.getStartTime() + "|" + lesson.getEndTime()
                + "|" + lesson.getClassNumber() + "\n";
        attendingDataList.add(line);
    }

    public static void saveAttendingIntoDataList() {
        for (Module module : Timetable.listOfModules) {
            for (Lesson lesson : module.getAllAttending()) {
                addAttending(lesson, module.getModuleCode());
            }
        }
    }

    private static void addLessonsIntoMap(LinkedHashMap<String, LinkedHashMap<String,
            ArrayList<Lesson>>> newLessons, Lesson newLesson) {

        String currLessonType = newLesson.getLessonType();
        String currClassNo = newLesson.getClassNumber();
        if (!newLessons.containsKey(currLessonType)) {
            newLessons.put(currLessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
            newLessons.get(currLessonType).put(currClassNo, new ArrayList<Lesson>());
            newLessons.get(currLessonType).get(currClassNo).add(newLesson);
        } else {
            if (!newLessons.get(currLessonType).containsKey(currClassNo)) { //checks existence of current classNo
                newLessons.get(currLessonType).put(currClassNo, new ArrayList<Lesson>());
                newLessons.get(currLessonType).get(currClassNo).add(newLesson);
            } else {
                newLessons.get(currLessonType).get(currClassNo).add(newLesson);
            }
        }
    }
    public static void loadNewAttendingOnStartUp() {
        int currModuleIndex = 0;
        List<Module> moduleList = Timetable.getListOfModules();
        if (moduleList.isEmpty()) {
            return;
        }
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> newLessons
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (String line : attendingDataList) {
            String[] currLine = line.split("\\|");
            String lessonType = currLine[1];
            String lessonDay = currLine[2];
            String lessonStart = currLine[3];
            String lessonEnd = currLine[4];
            String classNumber = currLine[5];

            Module currModule = moduleList.get(currModuleIndex);
            while (currModule.getLessons().size() == 0) {
                assert currModuleIndex != moduleList.size() : "Theres no module with that index";
                currModuleIndex++;
                currModule = moduleList.get(currModuleIndex);
            }
            String currModuleCode = moduleList.get(currModuleIndex).getModuleCode();
            if (currModuleCode.equals(currLine[0])) { //if the current module is the same as the one in the list
                addLessonsIntoMap(newLessons, new Lesson(lessonDay, lessonStart, lessonEnd, lessonType, classNumber));
            } else {
                currModule.replaceNewAttending(newLessons); //update the attending for the current module
                newLessons = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>(); //clear the data
                addLessonsIntoMap(newLessons, new Lesson(lessonDay, lessonStart, lessonEnd, lessonType, classNumber));
                currModuleIndex++;
            }
        }
        Module currModule = moduleList.get(currModuleIndex);
        currModule.replaceNewAttending(newLessons);
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
        int attendingIndex = 0;

        for (int index = 0; index < attendingDataList.size(); index++) {
            currLine = attendingDataList.get(index).split("\\|");

            currModule = moduleList.get(moduleIndex);
            moduleCode = currModule.getModuleCode();

            String lessonType = currLine[1];
            String lessonDay = currLine[2];
            String lessonStart = currLine[3];
            String lessonEnd = currLine[4];
            String classNumber = currLine[5];

            if (!currLine[0].equals(moduleCode)) {
                moduleIndex++;
                attendingIndex = 0;
                if (moduleIndex >= moduleList.size()) {
                    break;
                }
                currModule = moduleList.get(moduleIndex);
            }
            Lesson newLesson = new Lesson(lessonDay, lessonStart, lessonEnd, lessonType, classNumber);
            currModule.replaceAttending(newLesson, attendingIndex);
            attendingIndex++;
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
