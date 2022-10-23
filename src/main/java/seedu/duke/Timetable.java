package seedu.duke;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.data.DataManager;
import seedu.duke.data.LessonManager;
import seedu.duke.data.ModuleManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Timetable {
    public static List<Module> listOfModules = new ArrayList<>();
    public static LinkedHashMap<String, LinkedHashMap<String, String>> timetableDict 
                        = new LinkedHashMap<String, LinkedHashMap<String, String>>();

    private static void fillDay(LinkedHashMap<String, String> day) {
        int hourInt = 800;
        String hourStr;
        for (int i = 0; i < 24; i++) {
            hourStr = String.format("%04d", hourInt);
            day.put(hourStr, "______");
            if (i % 2 == 0) {
                hourInt += 30;
            } else {
                hourInt += 70;
            }
        }
    }
    
    public static void initDict() {
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 0: //Monday
                timetableDict.put("Monday", new LinkedHashMap<String, String>());
                fillDay(timetableDict.get("Monday"));
                break;
            case 1: //Tuesday
                timetableDict.put("Tuesday", new LinkedHashMap<String, String>());
                fillDay(timetableDict.get("Tuesday"));
                break;
            case 2: //Wednesday
                timetableDict.put("Wednesday", new LinkedHashMap<String, String>());
                fillDay(timetableDict.get("Wednesday"));
                break;
            case 3: //Thursday
                timetableDict.put("Thursday", new LinkedHashMap<String, String>());
                fillDay(timetableDict.get("Thursday"));
                break;
            case 4: //Friday
                timetableDict.put("Friday", new LinkedHashMap<String, String>());
                fillDay(timetableDict.get("Friday"));
                break;
            default:
                break;
            }
        }
    }

    /*
    For debugging
     */
    public static void printDict() {
        for (String day : timetableDict.keySet()) {
            System.out.println(day + ":");
            for (String time : timetableDict.get(day).keySet()) {
                System.out.println(time + ": " + timetableDict.get(day).get(time));
            }
        }
    }

    public static void addToDict(Lesson newLesson, String moduleCode) {
        String startTime = newLesson.getStartTime();
        String endTime = newLesson.getEndTime();
        String day = newLesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return;
        }

        LinkedHashMap<String, String> dayMap = timetableDict.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime) {
                dayMap.replace(hour, moduleCode);
            }
        }
    }

    /*
     * Brute force method, can be optimized later
     */
    public static void deleteFromDict(Module module) {
        String moduleCode = module.getModuleCode();
        
        for (String day : timetableDict.keySet()) {
            LinkedHashMap<String, String> dayMap = timetableDict.get(day);
            for (String hour : dayMap.keySet()) {
                if (dayMap.get(hour).equals(moduleCode)) {
                    dayMap.replace(hour, "______");
                }
            }
        }
    }

    public static void addNewModule(String code, String name, List<Lesson> lessons) {
        Module newModule = new Module(code, name, lessons);
        LessonManager.addLesson(newModule);
        ModuleManager.addModule(code, name);
        listOfModules.add(newModule);
    }

    public static void addNewModuleFromFile(String code, String name, List<Lesson> lessons) {
        Module newModule = new Module(code, name, lessons);
        listOfModules.add(newModule);
    }

    public static String allocateModules() {
        int unallocated = 0;
        String result;
        result = "Sorry, but we were unable to allocate timings for these modules due to timetable clashes:\n";

        for (Module module : listOfModules) {
            List<Lesson> attendingList = module.getAttending();
            for (int index = 0; index < attendingList.size(); index++) {
                Lesson attendingLesson = attendingList.get(index);
                if (attendingLesson.getDay().equals("Undetermined Day")) {
                    boolean isAllocated = false;

                    for (Lesson lesson : module.getLessons()) {
                        if (!lesson.getLessonType().equals(attendingLesson.getLessonType())) {
                            continue;
                        }
                        String startTime = lesson.getStartTime();
                        String endTime = lesson.getEndTime();
                        String day = lesson.getDay();

                        if (endTime.charAt(2) == '3') {
                            int endTimeInt = Integer.parseInt(endTime);
                            endTimeInt -= 30;
                            endTime = String.format("%04d", endTimeInt);
                        } else {
                            int endTimeInt = Integer.parseInt(endTime);
                            endTimeInt -= 70;
                            endTime = String.format("%04d", endTimeInt);
                        }

                        LinkedHashMap<String, String> dayMap = timetableDict.get(day);
                        if (dayMap.get(startTime).equals("______") && dayMap.get(endTime).equals("______")) {
                            module.replaceAttending(lesson, index);
                            isAllocated = true;
                            break;
                        }
                    }
                    if (!isAllocated) {
                        unallocated++;
                        result += module.getModuleCode() + " (" + attendingLesson.getLessonType() + ")\n";
                    }
                }
            }
        }
        result += "Please rearrange some of your modules and try again!\n";
        if (unallocated != 0) {
            return result;
        } else {
            return "Allocation successful!";
        }
    }

    public static List<Module> getListOfModules() {
        return listOfModules;
    }

    public static Module getModuleByCode(String code) {
        for (int i = 0; i < listOfModules.size(); i++) {
            if (listOfModules.get(i).getModuleCode().equals(code)) {
                return listOfModules.get(i);
            }
        }
        return null;
    }

    public static String listModules() {
        //list to print
        StringBuilder list;
        int counter = 1;


        if (listOfModules.size() == 0) {
            return "You have no modules at the moment!";
        } else {
            list = new StringBuilder("Here are your modules:\n");
        }
        for (Module modules : listOfModules) {
            list.append(counter).append(". ").append(modules.getModuleDetails()).append("\n");
            counter += 1;
        }
        return list.toString();
    }

    public static void deleteModule(int index) {
        Module module = listOfModules.get(index - 1);
        DataManager.deleteModule(module);
        deleteFromDict(module);
        listOfModules.remove(index - 1);
    }    // the nth module in list has index n-1

    public static int getListLength() {
        return listOfModules.size();
    }

    public static String getShortenedList() {
        StringBuilder list = new StringBuilder();
        int index = 1;
        for (Module module : listOfModules) {
            list.append(index).append(". ").append(module.toString()).append("\n");
            index += 1;
        }
        return list.toString();
    }

    public static int getLessonTypeLength(int index) {
        return listOfModules.get(index).getLessonTypeLength();
    }

    public static String getLessonTypes(int index) {
        return listOfModules.get(index).getLessonTypes();
    }

    public static String getLessonTypeFromIndex(int indexForModule, int lessonIndex) {
        assert indexForModule >= 0 : "index should be within range";
        assert lessonIndex >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getTypeOfLessonFromIndex(lessonIndex);
    }

    public static String listAllPossibleLessonReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getListOfLessonReplacements(targetLessonType);
    }

    public static int getNumberOfPossibleReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getNumberOfReplacements(targetLessonType);
    }

    public static Lesson getLessonReplacement(int indexForModule, int indexForTarget, String targetType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getReplacement(targetType, indexForTarget);
    }

    public static void replaceLesson(Lesson newLesson, int indexForModule, Integer indexForLesson) {
        assert indexForModule >= 0 : "index should be within range";

        listOfModules.get(indexForModule).replaceAttending(newLesson, indexForLesson);
    }
}
