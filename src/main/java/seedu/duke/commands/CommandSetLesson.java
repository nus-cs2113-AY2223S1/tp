package seedu.duke.commands;

import seedu.duke.Exceptions;
import seedu.duke.UI;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Command class for managing the process of setting lessons for a particular module.
 */
public class CommandSetLesson {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Starts the process of bringing the user through the lesson setting procedure.
     *
     * @return A response string to be printed by UI class to the user.
     */
    public static String setLesson() {
        if (Timetable.getNumberOfSettableLessons() == 0) {
            return "No modules available for lessons to be set.";
        }

        String indexToSet = UI.getModuleIndexFromUser(Timetable.getShortenedList());
        if (isInvalidInput(indexToSet, Timetable.getNumberOfSettableLessons())) {
            return "Invalid module index!";
        }

        assert Integer.parseInt(indexToSet) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexToSet) <= Timetable.getNumberOfSettableLessons() : "index must be in range";
        lgr.fine("module index accepted");

        return runSetProcedure(Integer.parseInt(indexToSet) - 1);
    }

    /**
     * Gets the lesson the user is attending and wants to set, and sets it.
     *
     * @param indexForModule Index number of the module in the list which the user wants to set lesson for.
     * @return A response string to the user.
     */
    private static String runSetProcedure(int indexForModule) {

        //gets
        String indexForLesson = UI.getLessonIndexFromUser(Timetable.getSettableLessonTypes(indexForModule));
        int lessonTypeListLength = Timetable.getSettableLessonTypeLength(indexForModule); //no of types for this mod

        if (isInvalidInput(indexForLesson, lessonTypeListLength)) {
            return "Invalid Lesson Index!";
        }

        assert Integer.parseInt(indexForLesson) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexForLesson) <= lessonTypeListLength : "index must be in range";
        lgr.fine("lesson type index accepted");

        String targetLessonType = Timetable.getSettableLessonTypeFromIndex(indexForModule,
                Integer.parseInt(indexForLesson) - 1);

        try {
            ArrayList<Lesson> newLessons = getPreferredLesson(indexForModule, targetLessonType);
            replaceAttendingLesson(newLessons, indexForModule, targetLessonType);
        } catch (Exceptions.InvalidTimeslotException e) {
            return "Invalid Timeslot Index!";
        }

        return "Successfully set your lesson!";
    }

    private static void replaceAttendingLesson(ArrayList<Lesson> newLessons, int indexForModule, String moduleType) {
        Timetable.replaceSettableLesson(newLessons, indexForModule, moduleType);
    }

    /**
     * Allows the user to choose their preferred lesson timeslot and returns list of lessons.
     *
     * @param indexForModule Index number of the module in the list which the user wants to set lesson for.
     * @param targetLessonType The type of the lesson they want to set.
     * @return A list of the new lessons for the user.
     * @throws Exceptions.InvalidTimeslotException If the timeslot selected by the user is not a valid one.
     */
    private static ArrayList<Lesson> getPreferredLesson(int indexForModule, String targetLessonType)
            throws Exceptions.InvalidTimeslotException {

        int numberOfReplacements = Timetable.getSettableNumberOfPossibleReplacements(indexForModule, targetLessonType);
        String replacementIndex = UI.getTimeslotIndexFromUser(
                Timetable.listAllSettableLessonReplacements(indexForModule, targetLessonType));

        if (isInvalidInput(replacementIndex, numberOfReplacements)) {
            throw new Exceptions.InvalidTimeslotException();
        }

        assert Integer.parseInt(replacementIndex) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(replacementIndex) <= numberOfReplacements : "index must be in range";
        lgr.fine("preferred lesson index accepted");

        return Timetable.getSettableLessonReplacement(indexForModule,
                Integer.parseInt(replacementIndex), targetLessonType);
    }

    private static boolean isInvalidInput(String index, int length) {
        return (!isInteger(index) || Integer.parseInt(index) < 1 || Integer.parseInt(index) > length);
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
