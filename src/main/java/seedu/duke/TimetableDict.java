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
            List<Lesson> attendingLessons = module.getAttendingList();
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

    public void addClass(List<Lesson> newClass) {
        for (Lesson lesson : newClass) {
            String startTime = lesson.getStartTime();
            String endTime = lesson.getEndTime();
            String day = lesson.getDay();
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
                    dayMap.replace(hour, lesson);
                }
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
     * Deletes all lessons of the module from timetable
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
    For debugging
     */
    public void print() {
        for (String day : timetable.keySet()) {
            System.out.println(day + ":");
            for (String time : timetable.get(day).keySet()) {
                if (timetable.get(day).get(time) == null) {
                    System.out.println("_______");
                    continue;
                }
                System.out.println(time + ": " + timetable.get(day).get(time).getModuleCode());
            }
        }
    }

    /*
     * HELPER FUNCTIONS FOR OUTER ALLOCATION ALGORITHM
     */

    private List<List<List<List<Lesson>>>> getPermutationsByMod(List<Module> listOfModules) {
        List<List<List<List<Lesson>>>> permutationsByMod = new ArrayList<>();
        for (Module module : listOfModules) {
            List<List<List<Lesson>>> possiblePermutations = getPossibleClassPermutations(module);
            permutationsByMod.add(possiblePermutations);
        }
        return permutationsByMod;
    }

    //get number of permutations
    private int getTotalNumberOfPermutations(List<List<List<List<Lesson>>>> permutationsByMod) {
        int numOfPermutations = 1;
        for (List<List<List<Lesson>>> permutationsForCurrMod : permutationsByMod) {
            numOfPermutations *= permutationsForCurrMod.size();
        }
        return numOfPermutations;
    }

    //generate all permutations
    private List<List<List<Lesson>>> getAllPermutations(List<Module> listOfModules, int numOfPermutations, List<List<List<List<Lesson>>>> permutationsByMod) {
        List<List<List<Lesson>>> allPermutations = new ArrayList<>();
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            allPermutations.add(new ArrayList<>());
        }
        
        for (int moduleIndex = 0; moduleIndex < listOfModules.size(); moduleIndex++) {
            int permutationIndexForCurrMod = 0;
            for (int i = 0; i < numOfPermutations; i++) {
                List<List<Lesson>> currPermutation = allPermutations.get(i);
                currPermutation.addAll(permutationsByMod.get(moduleIndex).get(permutationIndexForCurrMod));
                if ((i + 1) % (numOfPermutations / permutationsByMod.get(moduleIndex).size()) == 0) { 
                    permutationIndexForCurrMod++;
                }
            }
        }

        return allPermutations;
    }

    //check for number of clashes in permutation
    private int getNumOfClashes(List<List<Lesson>> classes) {
        TimetableDict tempTimetable = new TimetableDict();
        int numOfClashes = 0;
        for (List<Lesson> currClass : classes) {
            for (Lesson lesson : currClass) {
                if (checkClash(tempTimetable, lesson) == 1) {
                    numOfClashes++;
                    continue;
                }
            }
            tempTimetable.addClass(currClass);
        }
        return numOfClashes;
    }

    //Main allocation algorithm
    public String allocateModules() {
        String resultString = "Sorry we were unable to allocate timings for these modules due to timetable clashes:\n";

        List<Module> listOfModules = Timetable.getListOfModules();
        List<List<List<List<Lesson>>>> permutationsByMod = getPermutationsByMod(listOfModules);
        int numOfPermutations = getTotalNumberOfPermutations(permutationsByMod);
        List<List<List<Lesson>>> allPermutations = getAllPermutations(listOfModules, numOfPermutations, permutationsByMod);

        if (numOfPermutations == 0) {
            return "Sorry you have no modules to allocate!";
        }

        int lowestNumOfClashes = 999;
        List<List<Lesson>> permutationWithLeastClashes = new ArrayList<>();
        for (List<List<Lesson>> permutation : allPermutations) {
            int numOfClashes = getNumOfClashes(permutation);
            if (numOfClashes < lowestNumOfClashes) {
                permutationWithLeastClashes = permutation;
            }
            if (numOfClashes == 0) {
                break;
            }
        }

        //Adds all lessons in permutation with least clashes
        System.out.println("------ FINAL PERMUTATION -------");
        int unallocated = 0;
        for (List<Lesson> currClass : permutationWithLeastClashes) {
            for (Lesson lesson : currClass) {
                System.out.println(lesson.getModuleCode() + "|" + lesson.getLessonType() + "|" + lesson.getDay() + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
                Module module = Timetable.getModuleByCode(lesson.getModuleCode());
                if (checkClash(Timetable.timetableDict, lesson) == 0) {
                    module.replaceAttending(lesson);
                } 
            }
        }

        //Prints what has not been allocated
        for (Module module : listOfModules) {
            for (Lesson attendingLesson : module.getAttendingList()) {
                if (attendingLesson.getDay().equals("Undetermined Day")) {
                    unallocated++;
                    resultString += attendingLesson.getModuleCode() + " (" + attendingLesson.getLessonType() + ")\n";
                }
            }
        }

        //End of function
        if (unallocated != 0) {
            resultString += "Please rearrange some of your modules and try again!\n";
            return resultString;
        } else {
            return "All your mods have been successfully allocated!";
        }
    } 

    /*
     * Helper functions for lesson permutation generation
     */
    private int getNumberOfClassPermutations (List<Integer> numOfClassesPerType) {
        int numOfPermutations = 1;
        for (int numOfLessons : numOfClassesPerType) {
            numOfPermutations *= numOfLessons;
        }
        return numOfPermutations;
    }

    private List<List<List<Lesson>>> generateLessonPermutations(List<Integer> numOfClassesPerType, List<String> lessonTypes, int numOfPermutations, Module module) {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> lessonMap = module.getLessonMap();
        List<List<List<Lesson>>> result = new ArrayList<>();
        int numOfLessonTypes = lessonTypes.size();

        //setting all empty lists
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            result.add(new ArrayList<>());
        }

        for (int lessonTypeIndex = 0; lessonTypeIndex < numOfLessonTypes; lessonTypeIndex++) {
            int classNumberIndex = 0;
            String currLessonType = lessonTypes.get(lessonTypeIndex);
            LinkedHashMap<String, ArrayList<Lesson>> currLessonTypeClasses = lessonMap.get(currLessonType);
            List<String> classNumbers = new ArrayList<String>(lessonMap.get(currLessonType).keySet());
            int numOfClasses = numOfClassesPerType.get(lessonTypeIndex);

            //if lessonType is already attended
            if (module.checkLessonTypeAttended(currLessonType)) { 
                List<Lesson> attendingClass = module.getClassFromAttendingMap(currLessonType);
                //assign to all permutations
                // System.out.println("attending class added (" + currLessonType + "|" + attendingClass.get(0).getClassNumber() + ")");
                for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                    List<List<Lesson>> currPermutation = result.get(permutationIndex);
                    currPermutation.add(attendingClass);
                }
            //not yet attended
            } else { 
                //assign lessons to all permutations equally
                for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                    List<List<Lesson>> currPermutation = result.get(permutationIndex);
                    String currClassNumber = classNumbers.get(classNumberIndex);
                    List<Lesson> currClass = currLessonTypeClasses.get(currClassNumber);
                    // System.out.println(classNumberIndex + "[" + classNumbers.get(classNumberIndex) + "]: " + "new class added (" + currLessonType + "|" + currClassNumber + ")");
                    currPermutation.add(currClass);
                    if ((permutationIndex + 1) % (numOfPermutations / numOfClasses) == 0) {
                        classNumberIndex++;
                    }
                }
            }
        }
        //debug
        int permutationCounter = 1;
        System.out.println("------ " + module.getModuleCode() + " ------");
        for (List<List<Lesson>> permutation : result) {
            System.out.println("--- Permutation" + permutationCounter + " ---");
            for (List<Lesson> currClass : permutation) {
                for (Lesson lesson : currClass) {
                    System.out.println(lesson.getLessonType() + "|" + lesson.getDay()
                        + "|" + lesson.getStartTime() + "-" + lesson.getEndTime());
                }
            }
            permutationCounter++;
        }
        System.out.print("\n");
        return result;
    }

    private List<Integer> getNumberOfClassesPerType(Module module, 
            LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> lessonMap) {
        List<Integer> numOfClassesPerType = new ArrayList<Integer>();
        for (String lessonType : module.getAttendingLessonTypes()) {
            boolean isAlreadyAttended = module.checkLessonTypeAttended(lessonType);
            if (isAlreadyAttended) {
                numOfClassesPerType.add(1);
            } else {
                numOfClassesPerType.add(lessonMap.get(lessonType).size());
            }
        }
        return numOfClassesPerType;
    }

    private List<List<List<Lesson>>> getPossibleClassPermutations(Module module) {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> lessonMap = module.getLessonMap();
        List<Integer> numOfClassesPerType = getNumberOfClassesPerType(module, lessonMap);
        int numOfPermutations = getNumberOfClassPermutations(numOfClassesPerType);
        List<String> lessonTypes = module.getAttendingLessonTypes();

        List<List<List<Lesson>>> result = generateLessonPermutations(numOfClassesPerType, lessonTypes, numOfPermutations, module);
        return result;
    }
}
