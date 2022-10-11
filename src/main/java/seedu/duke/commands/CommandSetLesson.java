package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Timetable;
import seedu.duke.module.lessons.Lesson;

import java.util.logging.Logger;

public class CommandSetLesson {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static String setLesson(Timetable timetable) {
        if (timetable.getListLength() == 0) {
            return "No modules available for lessons to be set.";
        }

        String indexToSet = "0";
        int listLength = timetable.getListLength();
        boolean isTryAgain = false;

        while (isInvalidInput(indexToSet, listLength)) {
            if (isTryAgain) {
                System.out.println("Please try again!\n");
            }

            System.out.println("Which module would you like to set lessons for? Enter corresponding valid index\n"
                    + timetable.getShortenedList());
            indexToSet = Duke.sc.nextLine();
            isTryAgain = true;
        }

        assert Integer.parseInt(indexToSet) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexToSet) <= timetable.getListLength() : "index must be in range";

        lgr.fine("module index accepted");

        return runSetProcedure(timetable, (Integer.parseInt(indexToSet) - 1));
    }

    private static String runSetProcedure(Timetable timetable, int indexForModule) {
        String indexForLesson = "0";
        int lessonTypeListLength = timetable.getLessonTypeLength(indexForModule);
        boolean isTryAgain = false;

        while (isInvalidInput(indexForLesson, lessonTypeListLength)) {
            if (isTryAgain) {
                System.out.println("Please try again!\n");
            }

            System.out.println("Which lesson type do you want to set? Enter corresponding valid index\n"
                    + timetable.getLessonTypes(indexForModule));
            indexForLesson = Duke.sc.nextLine();
            isTryAgain = true;
        }

        assert Integer.parseInt(indexForLesson) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(indexForLesson) <= lessonTypeListLength : "index must be in range";

        lgr.fine("lesson type index accepted");

        String targetLessonType = timetable.getLessonTypeFromIndex(indexForModule,
                Integer.parseInt(indexForLesson) - 1);

        Lesson newLesson = getPreferredLesson(indexForModule,
                Integer.parseInt(indexForLesson) - 1, targetLessonType, timetable);

        replaceAttendingLesson(newLesson, timetable, indexForModule);
        return "Successfully set your lesson!";
    }

    private static void replaceAttendingLesson(Lesson newLesson, Timetable timetable, int indexForModule) {
        timetable.replaceLesson(newLesson, indexForModule);
    }

    private static Lesson getPreferredLesson(int indexForModule, int i, String targetLessonType, Timetable timetable) {
        int numberOfReplacements = timetable.getNumberOfPossibleReplacements(indexForModule, targetLessonType);
        String replacementIndex = "0";
        boolean isTryAgain = false;

        while (isInvalidInput(replacementIndex, numberOfReplacements)) {
            if (isTryAgain) {
                System.out.println("Please try again!\n");
            }

            System.out.println("Which is your preferred timeslot? Enter corresponding valid index\n"
                    + timetable.listAllPossibleLessonReplacements(indexForModule, targetLessonType));
            replacementIndex = Duke.sc.nextLine();
            isTryAgain = true;
        }

        assert Integer.parseInt(replacementIndex) > 0 : "index must be greater than 1 to be in range";
        assert Integer.parseInt(replacementIndex) <= numberOfReplacements : "index must be in range";

        lgr.fine("preferred lesson index accepted");

        return timetable.getLessonReplacement(indexForModule, Integer.parseInt(replacementIndex), targetLessonType);
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
