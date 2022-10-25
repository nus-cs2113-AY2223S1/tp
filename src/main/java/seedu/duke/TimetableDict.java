package seedu.duke;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.Module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TimetableDict {
    /*
     * timetable[String:day][String:hour] = String:moduleCode
     */
    public LinkedHashMap<String, LinkedHashMap<String, String>> timetable 
                        = new LinkedHashMap<String, LinkedHashMap<String, String>>();

    public TimetableDict() {
        this.init();
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getTimetable() {
        return timetable;
    }

    private void fillDay(LinkedHashMap<String, String> day) {
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

    private int checkClash(Lesson lesson) {
        String startTime = lesson.getStartTime();
        String day = lesson.getDay();
        String endTime = lesson.getEndTime();
        boolean isLessonTime = false;

        LinkedHashMap<String, String> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime && !dayMap.get(hour).equals("______")) {
                return 1;
            }
        }
        return 0;
    }

    public void init() {
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 0: //Monday
                timetable.put("Monday", new LinkedHashMap<String, String>());
                fillDay(timetable.get("Monday"));
                break;
            case 1: //Tuesday
                timetable.put("Tuesday", new LinkedHashMap<String, String>());
                fillDay(timetable.get("Tuesday"));
                break;
            case 2: //Wednesday
                timetable.put("Wednesday", new LinkedHashMap<String, String>());
                fillDay(timetable.get("Wednesday"));
                break;
            case 3: //Thursday
                timetable.put("Thursday", new LinkedHashMap<String, String>());
                fillDay(timetable.get("Thursday"));
                break;
            case 4: //Friday
                timetable.put("Friday", new LinkedHashMap<String, String>());
                fillDay(timetable.get("Friday"));
                break;
            default:
                break;
            }
        }
    }

    /*
    For debugging
     */
    public void print() {
        for (String day : timetable.keySet()) {
            System.out.println(day + ":");
            for (String time : timetable.get(day).keySet()) {
                System.out.println(time + ": " + timetable.get(day).get(time));
            }
        }
    }

    public void addLesson(Lesson newLesson, String moduleCode) {
        String startTime = newLesson.getStartTime();
        String endTime = newLesson.getEndTime();
        String day = newLesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return;
        }

        LinkedHashMap<String, String> dayMap = timetable.get(day);
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
    public void deleteModule(Module module) {
        String moduleCode = module.getModuleCode();
        
        for (String day : timetable.keySet()) {
            LinkedHashMap<String, String> dayMap = timetable.get(day);
            for (String hour : dayMap.keySet()) {
                if (dayMap.get(hour).equals(moduleCode)) {
                    dayMap.replace(hour, "______");
                }
            }
        }
    }

    public String allocateModules() {
        return " ";
    }

    private int getNumOfClashes(List<Lesson> lessons) {
        TimetableDict tempTimetable = new TimetableDict();
        int numOfClashes = 0;
        for (Lesson lesson : lessons) {
            if (checkClash(lesson) == 1){
                numOfClashes++;
                continue;
            }
            tempTimetable.addLesson(lesson, "XXXXXX");
        }
        return numOfClashes;
    }

    private List<List<Lesson>> getPossibleLessonPermutations(Module module) {
        LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons = module.getClassifiedLessons(); //Lesson type -> list of lessons
        List<List<Lesson>> result = new ArrayList<>();

        int numOfPermutations = 1;
        List<Integer> numOfLessonsPerType = new ArrayList<Integer>();
        List<String> lessonTypes = new ArrayList<String>();

        //getting info
        for (String lessonType : classifiedLessons.keySet()) {
            numOfLessonsPerType.add(classifiedLessons.get(lessonType).size());
            numOfPermutations = numOfPermutations * classifiedLessons.get(lessonType).size();
            lessonTypes.add(lessonType);
        }

        //setting all empty lists
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            result.add(new ArrayList<Lesson>());
        }

        for (int lessonTypeIndex = 0; lessonTypeIndex < numOfLessonsPerType.size(); lessonTypeIndex++) {
            String currLessonType = lessonTypes.get(lessonTypeIndex);
            List<Lesson> currLessonTypeLessons = classifiedLessons.get(currLessonType);
            int numOfLessons = numOfLessonsPerType.get(lessonTypeIndex);
            int lessonIndex = 0;
            for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                List<Lesson> currPermutation = result.get(permutationIndex);
                currPermutation.add(currLessonTypeLessons.get(lessonIndex));
                if ((permutationIndex + 1) % (numOfPermutations / numOfLessons) == 0) {
                    lessonIndex++;
                }
            }
        }
        // For debugging
        for (int i = 0; i < numOfPermutations; i++) {
            List<Lesson> currPermutation = result.get(i);
            System.out.println("--- Permutation " + (i+1) + " ---");
            for (Lesson lesson : currPermutation) {
                System.out.println(lesson.getLessonType() + "|" + lesson.getDay() + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
            }
        }
        return result;
    }
}
