package seedu.duke.commands;

import seedu.duke.Exceptions;
import seedu.duke.Timetable;
import seedu.duke.UI;
import seedu.duke.module.lessons.Lesson;

import java.util.logging.Logger;

public class CommandSetLesson {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static String setLesson() {
        if (Timetable.getListLength() == 0) {
            return "No modules available for lessons to be set.";
        }

        String indexToSet = UI.getModuleIndexFromUser(Timetable.getShortenedList());
        if (isInvalidInput(indexToSet, Timetable.getListLength())) {
            return "Invalid module index!";
        }

        assert Integer.parseInt(indexToSet) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexToSet) <= Timetable.getListLength() : "index must be in range";
        lgr.fine("module index accepted");

        return runSetProcedure(Integer.parseInt(indexToSet) - 1);
    }

    private static String runSetProcedure(int indexForModule) {

        String indexForLesson = UI.getLessonIndexFromUser(Timetable.getLessonTypes(indexForModule));
        int lessonTypeListLength = Timetable.getLessonTypeLength(indexForModule);

        if (isInvalidInput(indexForLesson, lessonTypeListLength)) {
            return "Invalid Lesson Index!";
        }

        assert Integer.parseInt(indexForLesson) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexForLesson) <= lessonTypeListLength : "index must be in range";
        lgr.fine("lesson type index accepted");

        String targetLessonType = Timetable.getLessonTypeFromIndex(indexForModule,
                Integer.parseInt(indexForLesson) - 1);

        try {
            Lesson newLesson = getPreferredLesson(indexForModule,
                    Integer.parseInt(indexForLesson) - 1, targetLessonType);
            replaceAttendingLesson(newLesson, indexForModule, Integer.parseInt(indexForLesson) - 1);
        } catch (Exceptions.InvalidTimeslotException e) {
            return "Invalid Timeslot Index!";
        }

        return "Successfully set your lesson!";
    }

    private static void replaceAttendingLesson(Lesson newLesson, int indexForModule, Integer indexForLesson) {
        Timetable.replaceLesson(newLesson, indexForModule, indexForLesson);
    }

    private static Lesson getPreferredLesson(int indexForModule, int i, String targetLessonType)
            throws Exceptions.InvalidTimeslotException {

        int numberOfReplacements = Timetable.getNumberOfPossibleReplacements(indexForModule, targetLessonType);
        String replacementIndex = UI.getTimeslotIndexFromUser(
                Timetable.listAllPossibleLessonReplacements(indexForModule, targetLessonType));

        if (isInvalidInput(replacementIndex, numberOfReplacements)) {
            throw new Exceptions.InvalidTimeslotException();
        }

        assert Integer.parseInt(replacementIndex) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(replacementIndex) <= numberOfReplacements : "index must be in range";
        lgr.fine("preferred lesson index accepted");

        return Timetable.getLessonReplacement(indexForModule, Integer.parseInt(replacementIndex), targetLessonType);
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
