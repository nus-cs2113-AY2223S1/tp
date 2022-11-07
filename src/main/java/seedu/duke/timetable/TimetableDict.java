package seedu.duke.timetable;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.Module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TimetableDict {
    public LinkedHashMap<String, LinkedHashMap<String, Lesson>> timetable 
                        = new LinkedHashMap<String, LinkedHashMap<String, Lesson>>();

    public TimetableDict() {
        this.init();
    }

    /**
     * Adds lesson to timetableDict.
     * @param newLesson The lesson to be added into timetable
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

    /**
     * Adds all lessons in a class to timetableDict.
     * In this case, a "class" refers to all lessons in the same module that share a common classNumber
     * Classes comes in the form of a list of lessons
     * 
     * @param newClass The class to be added to timetable
     */
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

    /**
     * Deletes given lesson from timetableDict.
     * 
     * @param oldLesson The lesson to be removed from the timetableDict
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

    /**
     * Deletes all lessons of the module from timetableDict.
     * 
     * @param module The module of which all lessons are to be removed from timetableDict
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

    /**
     * Accessor used to access the dictionary in timetableDict.
     * 
     * @return Timetable in dictionary form
     */
    public LinkedHashMap<String, LinkedHashMap<String, Lesson>> getTimetable() {
        return timetable;
    }

    /**
     * Sets up an empty timetableDict.
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

    /**
     * Fills a given day entry in timetableDict with null Lesson entries.
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

    /**
     * Allocates all lessons that have yet to be set in a way that ensures minimal timetable clashes.
     * If there are modules that were unable to be allocated due to timetable clashes, a message listing
     * the unallocated lessons is returned. If all
     * lessons were successfully allocated, a message indicating a successful allocation is returned.
     * 
     * @return Successful/Unsuccessful allocation message
     */
    public String allocateModules() {
        String resultString = "Sorry these modules have too many lessons for us to check, please manually"
                                + " allocate them before trying again:\n";

        
        List<Module> listOfModules = Timetable.getListOfModules();
        if (listOfModules.size() == 0) {
            return "Sorry you have no modules to allocate!";
        }

        List<List<List<List<Lesson>>>> permutationsByMod = getPermutationsByMod(listOfModules);

        //check for lessons with too many permutations
        boolean isTooManyPermutations = false;
        for (int modIndex = 0; modIndex < permutationsByMod.size(); modIndex++) {
            List<List<List<Lesson>>> modPermutation = permutationsByMod.get(modIndex);
            String moduleCode = listOfModules.get(modIndex).getModuleCode();
            String moduleName = listOfModules.get(modIndex).getModuleName();
            if (modPermutation == null) {
                isTooManyPermutations = true;
                resultString += moduleCode + " (" + moduleName + ")\n";
            }
        }
        if (isTooManyPermutations) {
            return resultString;
        }

        resultString = "Sorry we were unable to allocate timings for these modules due to timetable clashes:\n";

        int numOfPermutations = getTotalNumberOfPermutations(permutationsByMod);
        List<List<List<Lesson>>> allPermutations = getAllPermutations(listOfModules, 
                                                numOfPermutations, permutationsByMod);
        List<List<Lesson>> permutationWithLeastClashes = getPermutationWithLeastClashes(allPermutations);

        int unallocated = 0;
        for (List<Lesson> currClass : permutationWithLeastClashes) {
            for (Lesson lesson : currClass) {
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

    //get number of permutations according to module order
    private List<List<List<List<Lesson>>>> getPermutationsByMod(List<Module> listOfModules) {
        List<List<List<List<Lesson>>>> permutationsByMod = new ArrayList<>();
        for (Module module : listOfModules) {
            List<List<List<Lesson>>> possiblePermutations = getAllClassPermutations(module);
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
    private List<List<List<Lesson>>> getAllPermutations(List<Module> listOfModules, 
            int numOfPermutations, List<List<List<List<Lesson>>>> permutationsByMod) {
        List<List<List<Lesson>>> allPermutations = new ArrayList<>();
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            allPermutations.add(new ArrayList<>());
        }
        
        for (int moduleIndex = 0; moduleIndex < listOfModules.size(); moduleIndex++) {
            int permutationIndexForCurrMod = 0;
            for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
                List<List<Lesson>> currPermutation = allPermutations.get(permutationIndex);
                currPermutation.addAll(permutationsByMod.get(moduleIndex).get(permutationIndexForCurrMod));
                if (permutationIndexForCurrMod < permutationsByMod.get(moduleIndex).size() - 1) {
                    permutationIndexForCurrMod++;
                } else {
                    permutationIndexForCurrMod = 0;
                }
            }
        }
        return allPermutations;
    }

    
    // checks for clash (returns 1 if clash, else 0) 
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

    //check for number of clashes in permutation
    private int getNumOfClashes(List<List<Lesson>> permutation) {
        TimetableDict tempTimetable = new TimetableDict();
        int numOfClashes = 0;
        for (List<Lesson> currClass : permutation) {
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

    //given a list of all permutation, check all for number of clashes and returns permutation with least clashes
    private List<List<Lesson>> getPermutationWithLeastClashes(List<List<List<Lesson>>> allPermutations) {
        int lowestNumOfClashes = 99999;
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
        return permutationWithLeastClashes;
    }

    //
    private int getNumOfClassPermutations(List<Integer> numOfClassesPerType) {
        int numOfPermutations = 1;
        for (int numOfLessons : numOfClassesPerType) {
            numOfPermutations *= numOfLessons;
        }
        return numOfPermutations;
    }

    private List<List<List<Lesson>>> generateLessonPermutations(List<Integer> numOfClassesPerType,
                                         List<String> lessonTypes, int numOfPermutations, Module module) {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> uniqueLessonMap = module.getUniqueLessonMap();
        List<List<List<Lesson>>> result = new ArrayList<>();
        int numOfLessonTypes = lessonTypes.size();

        //setting all empty lists
        for (int permutationIndex = 0; permutationIndex < numOfPermutations; permutationIndex++) {
            result.add(new ArrayList<>());
        }

        //generating permutations
        for (int lessonTypeIndex = 0; lessonTypeIndex < numOfLessonTypes; lessonTypeIndex++) {
            int classNumberIndex = 0;
            String currLessonType = lessonTypes.get(lessonTypeIndex);
            LinkedHashMap<String, ArrayList<Lesson>> currLessonTypeClasses = uniqueLessonMap.get(currLessonType);
            List<String> classNumbers = new ArrayList<String>(uniqueLessonMap.get(currLessonType).keySet());
            int numOfClasses = numOfClassesPerType.get(lessonTypeIndex);

            //if lessonType is already attended
            if (module.isLessonTypeAttended(currLessonType)) {
                List<Lesson> attendingClass = module.getClassFromAttendingMap(currLessonType);
                //assign to all permutations
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
                    currPermutation.add(currClass);
                    if (classNumberIndex < numOfClasses - 1) {
                        classNumberIndex++;
                    } else {
                        classNumberIndex = 0;
                    }
                }
            }
        }
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

    private List<Integer> getNumOfClassesPerLessonType(Module module, 
            LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> uniqueLessonMap) {
        List<Integer> numOfClassesPerType = new ArrayList<Integer>();
        for (String lessonType : module.getAttendingLessonTypes()) {
            boolean isAlreadyAttended = module.isLessonTypeAttended(lessonType);
            if (isAlreadyAttended) {
                numOfClassesPerType.add(1);
            } else {
                int numOfClasses = uniqueLessonMap.get(lessonType).size();
                numOfClassesPerType.add(numOfClasses);
            }
        }
        return numOfClassesPerType;
    }

    //Get all class permutations for a given module
    private List<List<List<Lesson>>> getAllClassPermutations(Module module) {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> uniqueLessonMap = module.getUniqueLessonMap();
        List<Integer> numOfClassesPerType = getNumOfClassesPerLessonType(module, uniqueLessonMap);
        int numOfPermutations = getNumOfClassPermutations(numOfClassesPerType);
        if (numOfPermutations > 30) {
            return null;
        }
        List<String> lessonTypes = module.getAttendingLessonTypes();
        
        List<List<List<Lesson>>> result = generateLessonPermutations(numOfClassesPerType, 
                                                lessonTypes, numOfPermutations, module);
        return result;
    }

    //For debugging
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
}
