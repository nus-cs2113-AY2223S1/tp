package seedu.duke;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.Module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.List;

public class TimetableDict {
    public LinkedHashMap<String, LinkedHashMap<String, Lesson>> timetable 
                        = new LinkedHashMap<String, LinkedHashMap<String, Lesson>>();

    public TimetableDict() {
        init();
    }

    public TimetableDict(List<Module> attendingModules) {
        init();
        for (Module module : attendingModules) {
            List<Lesson> attendingLessons = module.getAttending();
            for (Lesson lesson : attendingLessons) {
                addLesson(lesson);
            }
        }
    }

    /*
     * adds lesson to timetable
     */
    public void addLesson(Lesson newLesson) {
        String startTime = newLesson.getStartTime();
        String endTime = newLesson.getEndTime();
        String day = newLesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return;
        }

        LinkedHashMap<String, Lesson> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime) {
                dayMap.replace(hour, newLesson);
            }
        }
    }

    /*
     * deletes given lesson from timetable
     */
    public void deleteLesson(Lesson oldLesson) {
        String startTime = oldLesson.getStartTime();
        String endTime = oldLesson.getEndTime();
        String day = oldLesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return;
        }

        LinkedHashMap<String, Lesson> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime) {
                dayMap.replace(hour, null);
            }
        }
    }

    /*
     * returns timetable
     */
    public LinkedHashMap<String, LinkedHashMap<String, Lesson>> getTimetable() {
        return timetable;
    }

    /*
     * Calls fillDay for each day
     */
    public void init() {
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 0: //Monday
                timetable.put("Monday", new LinkedHashMap<String, Lesson>());
                fillDay(timetable.get("Monday"));
                break;
            case 1: //Tuesday
                timetable.put("Tuesday", new LinkedHashMap<String, Lesson>());
                fillDay(timetable.get("Tuesday"));
                break;
            case 2: //Wednesday
                timetable.put("Wednesday", new LinkedHashMap<String, Lesson>());
                fillDay(timetable.get("Wednesday"));
                break;
            case 3: //Thursday
                timetable.put("Thursday", new LinkedHashMap<String, Lesson>());
                fillDay(timetable.get("Thursday"));
                break;
            case 4: //Friday
                timetable.put("Friday", new LinkedHashMap<String, Lesson>());
                fillDay(timetable.get("Friday"));
                break;
            default:
                break;
            }
        }
    }

    /*
     * Fills all days with blank code first
     */
    private void fillDay(LinkedHashMap<String, Lesson> day) {
        int hourInt = 800;
        String hourStr;
        for (int i = 0; i < 24; i++) {
            hourStr = String.format("%04d", hourInt);
            day.put(hourStr, null);
            if (i % 2 == 0) {
                hourInt += 30;
            } else {
                hourInt += 70;
            }
        }
    }

    /*
     * checks for clash
     * returns 1 if clash
     * else 0
     */
    private int checkClash(TimetableDict timetableDict, Lesson lesson) {
        LinkedHashMap<String, LinkedHashMap<String, Lesson>> timetable = timetableDict.getTimetable(); 
        String startTime = lesson.getStartTime();
        String day = lesson.getDay();
        String endTime = lesson.getEndTime();
        boolean isLessonTime = false;

        LinkedHashMap<String, Lesson> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime && dayMap.get(hour) != null) {
                return 1;
            }
        }
        return 0;
    }

    /*
     * checks for clash
     * returns clashing moduleCode if clash
     * else ""
     */
    public Lesson getClashingModuleCode(Lesson lesson) {
        String startTime = lesson.getStartTime();
        String endTime = lesson.getEndTime();
        String day = lesson.getDay();
        boolean isLessonTime = false;

        if (day.equals("Undetermined Day")) {
            return null;
        }

        LinkedHashMap<String, Lesson> dayMap = timetable.get(day);
        for (String hour : dayMap.keySet()) {
            if (!isLessonTime && startTime.equals(hour)) {
                isLessonTime = true;
            }
            if (isLessonTime && endTime.equals(hour)) {
                break;
            }
            if (isLessonTime && dayMap.get(hour) != null) {
                return dayMap.get(hour);
            }
        }

        return null;
    }

    /*
    For debugging
     */
    public void print() {
        for (String day : timetable.keySet()) {
            System.out.println(day + ":");
            for (String time : timetable.get(day).keySet()) {
                System.out.println(time + ": " + timetable.get(day).get(time).getModuleCode());
            }
        }
    }

    /*
     * Brute force method, can be optimized later
     */
    public void deleteModule(Module module) {
        String moduleCode = module.getModuleCode();
        
        for (String day : timetable.keySet()) {
            LinkedHashMap<String, Lesson> dayMap = timetable.get(day);
            for (String hour : dayMap.keySet()) {
                Lesson currLesson = dayMap.get(hour);
                if (currLesson == null) {
                    continue;
                }
                if (currLesson.getModuleCode().equals(moduleCode)) {
                    dayMap.replace(hour, null);
                }
            }
        }
    }

    private List<List<List<Lesson>>> getPermutationsByMod(List<Module> listOfModules) {
        List<List<List<Lesson>>> permutationsByMod = new ArrayList<>();
        for (Module module : listOfModules) {
            List<List<Lesson>> possiblePermutations = getPossibleLessonPermutations(module);
            permutationsByMod.add(possiblePermutations);
        }
        return permutationsByMod;
    }

    private int getTotalNumberOfPermutations(List<List<List<Lesson>>> permutationsByMod) {
        int numOfPermutations = 1;
        for (List<List<Lesson>> permutationsForCurrMod : permutationsByMod) {
            numOfPermutations *= permutationsForCurrMod.size();
        }
        return numOfPermutations;
    }

    private List<List<Lesson>> getAllPermutations(List<Module> listOfModules, int numOfPermutations, List<List<List<Lesson>>> permutationsByMod) {
        //generate all permutations
        List<List<Lesson>> allPermutations = new ArrayList<>();
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            allPermutations.add(new ArrayList<>());
        }
        
        for (int moduleIndex = 0; moduleIndex < listOfModules.size(); moduleIndex++) {
            int permutationIndexForCurrMod = 0;
            for (int i = 0; i < numOfPermutations; i++) {
                List<Lesson> currPermutation = allPermutations.get(i);
                currPermutation.addAll(permutationsByMod.get(moduleIndex).get(permutationIndexForCurrMod));
                if ((i + 1) % (numOfPermutations / permutationsByMod.get(moduleIndex).size()) == 0) { 
                    permutationIndexForCurrMod++;
                }
            }
        }

        return allPermutations;
    }

    public String allocateModules() {
        String resultString = "Sorry we were unable to allocate timings for these modules due to timetable clashes:\n";

        List<Module> listOfModules = Timetable.getListOfModules();
        List<List<List<Lesson>>> permutationsByMod = getPermutationsByMod(listOfModules);
        int numOfPermutations = getTotalNumberOfPermutations(permutationsByMod);
        List<List<Lesson>> allPermutations = getAllPermutations(listOfModules, numOfPermutations, permutationsByMod);

        /*
         * go thru all permutations, check num of clashes for every permutation
         * add permutation to ordered hashmap where:
         * key = num of clashes
         * entry = permutation
         */
        TreeMap<Integer, List<Lesson>> permutationsByClashes = new TreeMap<Integer, List<Lesson>>();
        for (List<Lesson> permutation : allPermutations) {
            int numOfClashes = getNumOfClashes(permutation);
            permutationsByClashes.put(numOfClashes, permutation);
            if (numOfClashes == 0) {
                break;
            }
        }

        /*
         * take first entry (will be the least number of clashes)
         */
        System.out.println("------ FINAL PERMUTATION -------");
        List<Lesson> permutationWithLeastClashes = permutationsByClashes.get(permutationsByClashes.firstKey()); 
        int unallocated = 0;
        for (Lesson lesson : permutationWithLeastClashes) {
            System.out.println(lesson.getModuleCode() + "|" + lesson.getLessonType() + "|" + lesson.getDay() + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
            Module module = Timetable.getModuleByCode(lesson.getModuleCode());
            if (checkClash(Timetable.timetableDict, lesson) == 0) {
                module.replaceAttending(lesson);
            } 
        }

        //Quick fix
        for (Module module : listOfModules) {
            for (Lesson attendingLesson : module.getAttending()) {
                if (attendingLesson.getDay().equals("Undetermined Day")) {
                    unallocated++;
                    resultString += attendingLesson.getModuleCode() + " (" + attendingLesson.getLessonType() + ")\n";
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
            tempTimetable.addLesson(lesson);
        }
        return numOfClashes;
    }

    private List<Integer> getNumberOfLessonsPerType(Module module, 
                    LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons) {
            List<Integer> numOfLessonsPerType = new ArrayList<Integer>();
            String prevLessonType = "";
            for (String lessonType : module.getAttendingLessonTypes()) {
                boolean isAlreadyAttended = module.checkLessonTypeAttended(lessonType);
                if (isAlreadyAttended) {
                    numOfLessonsPerType.add(1);
                } else {
                    if (prevLessonType.equals(lessonType)) { //if got 2 slots for same lesson type
                        numOfLessonsPerType.add(classifiedLessons.get(lessonType).size() - 1);
                    } else {
                        numOfLessonsPerType.add(classifiedLessons.get(lessonType).size());
                    }
                }
                prevLessonType = lessonType;
            }
            return numOfLessonsPerType;
    }

    private int getNumberOfLessonPermutations (List<Integer> numOfLessonsPerType) {
        int numOfPermutations = 1;
        for (int numOfLessons : numOfLessonsPerType) {
            numOfPermutations *= numOfLessons;
        }
        return numOfPermutations;
    }

    private List<List<Lesson>> generateLessonPermutations(List<Integer> numOfLessonsPerType, List<String> lessonTypes, int numOfPermutations, Module module) {
        LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons = module.getClassifiedLessons();
        List<List<Lesson>> result = new ArrayList<>();

        //setting all empty lists
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            result.add(new ArrayList<Lesson>());
        }

        for (int lessonTypeIndex = 0; lessonTypeIndex < numOfLessonsPerType.size(); lessonTypeIndex++) {
            int lessonIndex = 0;
            String currLessonType = lessonTypes.get(lessonTypeIndex);
            List<Lesson> currLessonTypeLessons = classifiedLessons.get(currLessonType);
            Lesson lesson = currLessonTypeLessons.get(lessonIndex);
            int numOfLessons = numOfLessonsPerType.get(lessonTypeIndex);

            if (module.checkLessonTypeAttended(currLessonType)) { //if lesson for this lessonType has already been set
                for (Lesson attendingLesson : module.getAttending()) {
                    lesson = attendingLesson;
                    if (attendingLesson.getLessonType().equals(currLessonType)) {
                        if (result.get(0).contains(attendingLesson)) { //if got 2 of the same kind of lesson, skips to the one which hasnt been set
                            continue;
                        }
                        //assign to all permutations
                        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                            List<Lesson> currPermutation = result.get(permutationIndex);
                            currPermutation.add(attendingLesson);
                        }
                        break;
                    }
                }
            } else { //not yet set
                //assign lessons to all permutations equally
                for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                    List<Lesson> currPermutation = result.get(permutationIndex);
                    currPermutation.add(lesson);
                    if ((permutationIndex + 1) % (numOfPermutations / numOfLessons) == 0) {
                        lessonIndex++;
                    }
                }
            }
        }
        //debug
        int permutationCounter = 1;
        System.out.println("------ " + module.getModuleCode() + " ------");
        for (List<Lesson> permutation : result) {
            System.out.println("--- Permutation" + permutationCounter + " ---");
            for (Lesson lesson : permutation) {
                System.out.println(lesson.getLessonType() + "|" + lesson.getDay()
                     + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
            }
            permutationCounter++;
        }
        System.out.print("\n");
        return result;
    }

    private List<List<Lesson>> getPossibleLessonPermutations(Module module) {
        LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons = module.getClassifiedLessons();
        List<Integer> numOfLessonsPerType = getNumberOfLessonsPerType(module, classifiedLessons);
        int numOfPermutations = getNumberOfLessonPermutations(numOfLessonsPerType);
        List<String> lessonTypes = module.getAttendingLessonTypes();

        // debugging
        System.out.println("------ PRINTING ATTENDING ------");
        for (Lesson lesson : module.getAttending()) {
            System.out.println(lesson.getLessonType() + "|" + lesson.getDay() + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
        }

        System.out.println("------ INFO ------");
        System.out.println("Total number of permutations: " + numOfPermutations);
        System.out.println("Size of numOfLessonsPerType: " + numOfLessonsPerType.size());
        System.out.println("Size of lessonTypes: " + lessonTypes.size());
        for (int i = 0; i < lessonTypes.size(); i++) {
            System.out.println(lessonTypes.get(i) + " has " + numOfLessonsPerType.get(i) + " possible lessons");
        }
        

        List<List<Lesson>> result = generateLessonPermutations(numOfLessonsPerType, lessonTypes, numOfPermutations, module);
        return result;
    }
}
