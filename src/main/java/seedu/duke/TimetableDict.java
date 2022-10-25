package seedu.duke;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.Module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
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

    private int checkClash(TimetableDict timetableDict, Lesson lesson) {
        LinkedHashMap<String, LinkedHashMap<String, String>> timetable = timetableDict.getTimetable(); 
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

    public String getClashingModuleCode(Lesson lesson) {
        String startTime = lesson.getStartTime();
        String endTime = lesson.getEndTime();
        String day = lesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return "";
        }

        LinkedHashMap<String, String> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime && !dayMap.get(hour).equals("______")) {
                return dayMap.get(hour);
            }
        }

        return "";
    }

    public void deleteLesson(Lesson oldLesson) {
        String startTime = oldLesson.getStartTime();
        String endTime = oldLesson.getEndTime();
        String day = oldLesson.getDay();
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
                dayMap.replace(hour, "______");
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
        String resultString = "Sorry, but we were unable to allocate timings for these modules due to timetable clashes:\n";

        List<Module> listOfModules = Timetable.getListOfModules();
        int numOfPermutations = 1;
        List<List<List<Lesson>>> permutationsByMod = new ArrayList<>();
        for (Module module : listOfModules) {
            List<List<Lesson>> possiblePermutations = getPossibleLessonPermutations(module);
            numOfPermutations = numOfPermutations * possiblePermutations.size();
            permutationsByMod.add(possiblePermutations);
        }

        List<List<Lesson>> result = new ArrayList<>();
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            result.add(new ArrayList<>());
        }

        for (int moduleIndex = 0; moduleIndex < listOfModules.size(); moduleIndex++) {
            int permutationIndex = 0;
            for (int i = 0; i < numOfPermutations; i++) {
                List<Lesson> currPermutation = result.get(i);
                currPermutation.addAll(permutationsByMod.get(moduleIndex).get(permutationIndex));
                if ((i + 1) % (numOfPermutations / permutationsByMod.get(moduleIndex).size()) == 0) { 
                    permutationIndex++;
                }
            }
        }

        TreeMap<Integer, List<Lesson>> permutationsByClashes = new TreeMap<Integer, List<Lesson>>();
        for (List<Lesson> permutation : result) {
            int numOfClashes = getNumOfClashes(permutation);
            permutationsByClashes.put(numOfClashes, permutation);
            if (numOfClashes == 0) {
                break;
            }
        }

        int unallocated = 0;
        for (Lesson lesson : permutationsByClashes.get(permutationsByClashes.firstKey())) {
            Module module = Timetable.getModuleByCode(lesson.getModuleCode());
            if (checkClash(this, lesson) == 0) {
                module.replaceAttending(lesson);
            } else {
                if (!lesson.getModuleCode().equals(getClashingModuleCode(lesson))) {
                    unallocated++;
                    resultString += module.getModuleCode() + " (" + lesson.getLessonType() + ")\n";
                }
            }
        }

        if (unallocated != 0) {
            resultString += "Please rearrange some of your modules and try again!\n";
            return resultString;
        } else {
            return "All your mods have been successfully allocated!";
        }
    } 

    private int getNumOfClashes(List<Lesson> lessons) {
        TimetableDict tempTimetable = new TimetableDict();
        int numOfClashes = 0;
        for (Lesson lesson : lessons) {
            if (checkClash(tempTimetable, lesson) == 1) {
                numOfClashes++;
                continue;
            }
            tempTimetable.addLesson(lesson, lesson.getModuleCode());
        }
        return numOfClashes;
    }

    private List<List<Lesson>> getPossibleLessonPermutations(Module module) {
        LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons = module.getClassifiedLessons();
        List<List<Lesson>> result = new ArrayList<>();

        int numOfPermutations = 1;
        List<Integer> numOfLessonsPerType = new ArrayList<Integer>();
        List<String> lessonTypes = new ArrayList<String>();

        //getting info
        for (String lessonType : classifiedLessons.keySet()) {
            //check if a lesson has already been set for that lesson type
            boolean isAlreadyAttending = false;
            for (Lesson lesson : module.getAttending()) {
                if (lesson.getLessonType().equals(lessonType) && !lesson.getDay().equals("Undetermined Day")) {
                    isAlreadyAttending = true;
                    break;
                }
            }
            if (isAlreadyAttending) {
                lessonTypes.add(lessonType);
                numOfLessonsPerType.add(1);
            } else {
                lessonTypes.add(lessonType);
                numOfLessonsPerType.add(classifiedLessons.get(lessonType).size());
                numOfPermutations = numOfPermutations * classifiedLessons.get(lessonType).size();
            }
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
            if (numOfLessons == 1 && currLessonTypeLessons.size() != 1) { //already set
                Lesson tempLesson;
                for (Lesson attendingLesson : module.getAttending()) {
                    if (attendingLesson.getLessonType().equals(currLessonType)) {
                        tempLesson = attendingLesson;
                        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                            List<Lesson> currPermutation = result.get(permutationIndex);
                            currPermutation.add(tempLesson);
                        }
                    }
                    break;
                }
            } else { //not yet set
                for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                    List<Lesson> currPermutation = result.get(permutationIndex);
                    currPermutation.add(currLessonTypeLessons.get(lessonIndex));
                    if ((permutationIndex + 1) % (numOfPermutations / numOfLessons) == 0) {
                        lessonIndex++;
                    }
                }
            }
        }
        return result;
    }
}
