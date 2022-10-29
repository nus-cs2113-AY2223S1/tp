package seedu.duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

/*
 * ### Public Functions: ###
 * initLessonDataFile()
 * addLesson(Module module)
 * deleteLesson(Module module)
 * 
 * ### Helper (private) Functions: ###
 * rewriteLessonData()
 * removeModuleFromDataList(Module module)
 */

public class LessonManager {
    public static ArrayList<String> lessonDataList = new ArrayList<String>();
    static String currentSemester;
    static String dataDirectoryPath;

    public static ArrayList<String> getDataList() {
        return lessonDataList;
    }

    /*
     * Creates LessonData.txt if doesnt exist
     */
    public static void initLessonDataFile() {
        currentSemester = DataManager.getCurrentSem();
        dataDirectoryPath = DataManager.getDirPath();

        try {
            File lessonDataFile = new File(dataDirectoryPath + "/LessonData.txt");
            if (!lessonDataFile.exists()) {
                lessonDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating LessonData.txt");
        }
    }

    /*
     * Erases data in lessonDataFile and writes data from lessonDataList into lessonDataFile
     */
    public static void rewriteLessonData() {
        try {
            // Erase Data
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/LessonData.txt");
            myWriter.write("");
            myWriter.close();

            myWriter = new FileWriter(dataDirectoryPath + "/LessonData.txt", true);
            for (String line : lessonDataList) {
                myWriter.write(line + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to erase Lesson Data");
        }
    }

    /*
     * Removes all Lessons for a given module from LessonDataList
     */
    public static void removeModuleFromDataList(Module module) {
        String moduleCode = module.getModuleCode();
        String[] currLine;
        int index = 0;

        while (index < lessonDataList.size()) {
            currLine = lessonDataList.get(index).split("\\|");
            if (currLine[0].equals(moduleCode)) {
                lessonDataList.remove(index);
            } else {
                index++;
            }
        }
    }

    /*
     * Removes all instances of Lessons from lessonDataList and lessonDataFile
     */
    public static void deleteLesson(Module module) {
        removeModuleFromDataList(module);
        rewriteLessonData();
    }

    /*
    * Stores lessons into LessonData.txt in the following format:
    * <ModuleCode>|<lessonType>|<day>|<start>|<end>\n
    */
    public static void addLesson(Module module) {
        String code = module.getModuleCode();
        List<Lesson> lessons = module.getLessons();
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/LessonData.txt", true);
            for (Lesson lesson : lessons) {
                String line = code + "|" + lesson.getLessonType() + "|" + lesson.getDay()
                        + "|" + lesson.getStartTime() + "|" + lesson.getEndTime()
                        + "|" + lesson.getClassNumber();
                myWriter.write(line + "\n");
                lessonDataList.add(line);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to add lesson to LessonData.txt");
        }
    }

    public static void loadLessonIntoDataList() {
        // Initiate readers
        FileReader lessonFileReader;
        BufferedReader lessonBufferedReader;

        //Get dataFile
        File lessonDataFile = new File(dataDirectoryPath + "/LessonData.txt");

        String currLine;

        try {
            // Initiate readers
            lessonFileReader = new FileReader(lessonDataFile);
            lessonBufferedReader = new BufferedReader(lessonFileReader);

            while ((currLine = lessonBufferedReader.readLine()) != null) {
                lessonDataList.add(currLine);
            }
        } catch (IOException e) {
            System.out.println("Failed to load lessonDataList from LessonData.txt!");
        }
    }
}
