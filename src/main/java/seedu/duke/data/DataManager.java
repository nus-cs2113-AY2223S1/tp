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
import seedu.duke.module.lessons.Lecture;
import seedu.duke.module.lessons.Tutorial;
import seedu.duke.module.lessons.Laboratory;
import seedu.duke.module.lessons.Others;

public class DataManager {
    /*
     * ModuleLines:
     * <code>|<name>|<description>
     * 
     * LessonLines:
     * <ModuleCode>|<lessonType>|<day>|<start>|<end>|
     * 
     * attendingLessonLines:
     * <ModuleCode>|<lessonType>|<day>|<start>|<end>|
     */
    public static ArrayList<String> ModuleLines = new ArrayList<String>();
    public static ArrayList<String> LessonLines = new ArrayList<String>();
    public static ArrayList<String> attendingLessonLines = new ArrayList<String>();

    protected static String currentSemester;
    protected static String dataDirectoryPath;

    public static void initDataFile(String semester) {
        if (!semester.equals("1") && !semester.equals("2")) {
            return;
        }
        currentSemester = semester;
        dataDirectoryPath = "./Sem" + currentSemester + "DataDirectory";

        File dataDir = new File(dataDirectoryPath);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        try {
            File moduleDataFile = new File(dataDirectoryPath + "/ModuleData.txt");
            if (!moduleDataFile.exists()) {
                moduleDataFile.createNewFile();
            }

            File lessonDataFile = new File(dataDirectoryPath + "/LessonData.txt");
            if (!lessonDataFile.exists()) {
                lessonDataFile.createNewFile();
            }

            File attendingLessonDataFile = new File(dataDirectoryPath + "/AttendingLessonData.txt");
            if (!attendingLessonDataFile.exists()) {
                attendingLessonDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data files");
        }
    }

    public static boolean attendingExists(Lesson lesson, String moduleCode) {
        for (int i = 0; i < attendingLessonLines.size(); i++) {
            String[] currAttendingLessonLineList = attendingLessonLines.get(i).split("\\|");
            if (currAttendingLessonLineList[0].equals(moduleCode)
                    && currAttendingLessonLineList[1].equals(lesson.getLessonType())) {
                return true;
            }
        }
        return false;
    }

    public static void addModule(String code, String name, String description) {
        /*
         * Stores modules into ModuleData.txt in the following format:
         * <code>|<name>|<description>\n
         */
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/ModuleData.txt", true);
            String line = code + "|" + name + "|" + description;
            myWriter.write(line + "\n");
            ModuleLines.add(line + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to add module to ModuleData.txt");
        }
    }

    public static void addLessons(Module module) {
        /*
         * Stores lessons into LessonData.txt in the following format:
         * <ModuleCode>|<lessonType>|<day>|<start>|<end>\n
         */
        String code = module.getModuleCode();
        List<Lesson> lessons = module.getLessons();
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/LessonData.txt", true);
            for (Lesson lesson : lessons) {
                String line = code + "|" + lesson.getLessonType() + "|" + lesson.getDay()
                        + "|" + lesson.getStartTime() + "|" + lesson.getEndTime();
                myWriter.write(line + "\n");
                LessonLines.add(line + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to add module to ModuleData.txt");
        }
    }

    public static void addAttending(Lesson lesson, String code) {
        /*
         * Stores lessons into Data.txt in the following format:
         * <ModuleCode>|<lessonType>|<day>|<start>|<end>\n
         */
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/AttendingLessonData.txt", true);
            String line = code + "|" + lesson.getLessonType() + "|" + lesson.getDay()
                    + "|" + lesson.getStartTime() + "|" + lesson.getEndTime();
            myWriter.write(line + "\n");
            attendingLessonLines.add(line + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to add module to AttendingLessonData.txt");
        }
    }

    public static void setAttending(Lesson lessonToSet, String moduleCode) {
        String currLine;
        for (int i = 0; i < attendingLessonLines.size(); i++) {
            currLine = attendingLessonLines.get(i);
            String[] currLineList = currLine.split("\\|");
            if (currLineList[0].equals(moduleCode) && currLineList[1].equals(lessonToSet.getLessonType())) {
                String newLine = moduleCode + "|" + lessonToSet.getLessonType() + "|" + lessonToSet.getDay()
                        + "|" + lessonToSet.getStartTime() + "|" + lessonToSet.getEndTime();
                attendingLessonLines.set(i, newLine + "\n");
                break;
            }
        }

        try {
            // Erase Data
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/AttendingLessonData.txt");
            myWriter.write("");
            myWriter.close();

            // Replace Data
            myWriter = new FileWriter(dataDirectoryPath + "/AttendingLessonData.txt", true);
            for (int i = 0; i < attendingLessonLines.size(); i++) {
                myWriter.write(attendingLessonLines.get(i));
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to set lesson in data file");
        }
    }

    private static void loadAttending() {
        List<Module> moduleList = Timetable.getListOfModules();

        // load attendingLessonLines
        String currLine;
        String[] currLineList;

        Module currModule;
        String moduleCode;
        int moduleIndex = 0;

        for (int i = 0; i < attendingLessonLines.size(); i++) {
            currLine = attendingLessonLines.get(i);
            currLineList = currLine.split("\\|");

            currModule = moduleList.get(moduleIndex);
            moduleCode = currModule.getModuleCode();

            String lessonType = currLineList[1];
            String lessonDay = currLineList[2];
            String lessonStart = currLineList[3];
            String lessonEnd = currLineList[4];

            if (!currLineList[0].equals(moduleCode)) {
                moduleIndex++;
                currModule = moduleList.get(moduleIndex);
                moduleCode = currModule.getModuleCode();
            }

            switch (lessonType) {
            case "Lecture":
                Lecture newLecture = new Lecture(lessonDay, lessonStart, lessonEnd, lessonType);
                currModule.replaceAttending(newLecture);
                break;
            case "Tutorial":
                Tutorial newTutorial = new Tutorial(lessonDay, lessonStart, lessonEnd, lessonType);
                currModule.replaceAttending(newTutorial);
                break;
            case "Laboratory":
                Laboratory newLaboratory = new Laboratory(lessonDay, lessonStart, lessonEnd, lessonType);
                currModule.replaceAttending(newLaboratory);
                break;
            default:
                Others newOthers = new Others(lessonDay, lessonStart, lessonEnd, lessonType);
                currModule.replaceAttending(newOthers);
                break;
            }
        }
    }

    public static void loadTimetableFromDataFile() {
        // Initiate readers
        FileReader moduleFileReader;
        BufferedReader moduleBufferedReader;
        FileReader lessonFileReader;
        BufferedReader lessonBufferedReader;
        FileReader attendingLessonFileReader;
        BufferedReader attendingLessonBufferedReader;

        // Get dataFile
        File moduleDataFile = new File(dataDirectoryPath + "/ModuleData.txt");
        File lessonDataFile = new File(dataDirectoryPath + "/LessonData.txt");
        File attendingLessonDataFile = new File(dataDirectoryPath + "/AttendingLessonData.txt");

        // Variables for iterating through data files
        String currModuleLine;
        String[] moduleInfoList; // code, name, description
        String currLessonLine;
        String[] lessonInfoList;
        String currAttendingLine;

        List<Lesson> lessons = new ArrayList<Lesson>();

        try {
            moduleFileReader = new FileReader(moduleDataFile);
            moduleBufferedReader = new BufferedReader(moduleFileReader);

            lessonFileReader = new FileReader(lessonDataFile);
            lessonBufferedReader = new BufferedReader(lessonFileReader);

            attendingLessonFileReader = new FileReader(attendingLessonDataFile);
            attendingLessonBufferedReader = new BufferedReader(attendingLessonFileReader);

            while ((currAttendingLine = attendingLessonBufferedReader.readLine()) != null) {
                attendingLessonLines.add(currAttendingLine);
            }

            while ((currModuleLine = moduleBufferedReader.readLine()) != null) {
                lessons.clear();

                ModuleLines.add(currModuleLine + "\n");
                moduleInfoList = currModuleLine.split("\\|");

                String code = moduleInfoList[0];
                String name = moduleInfoList[1];
                String description = moduleInfoList[2];

                while ((currLessonLine = lessonBufferedReader.readLine()) != null) {
                    if (!currLessonLine.split("\\|")[0].equals(code)) {
                        break;
                    }

                    lessonInfoList = currLessonLine.split("\\|");

                    String lessonType = lessonInfoList[1];
                    String lessonDay = lessonInfoList[2];
                    String lessonStart = lessonInfoList[3];
                    String lessonEnd = lessonInfoList[4];

                    switch (lessonType) {
                    case "Lecture":
                        lessons.add(new Lecture(lessonDay, lessonStart, lessonEnd, lessonType));
                        break;
                    case "Tutorial":
                        lessons.add(new Tutorial(lessonDay, lessonStart, lessonEnd, lessonType));
                        break;
                    case "Laboratory":
                        lessons.add(new Laboratory(lessonDay, lessonStart, lessonEnd, lessonType));
                        break;
                    default:
                        lessons.add(new Others(lessonDay, lessonStart, lessonEnd, lessonType));
                        break;
                    }
                }
                Timetable.addNewModuleFromFile(code, name, description, lessons);
            }
            moduleFileReader.close();
            moduleBufferedReader.close();
            lessonFileReader.close();
            lessonBufferedReader.close();

            loadAttending();

        } catch (IOException e) {
            System.out.println("Sorry, failed to load modules from ModuleData.txt");
        }
    }

}
