package seedu.duke.timetable;

import seedu.duke.exceptions.DuplicateLessonException;
import seedu.duke.exceptions.InvalidTimeFormatException;
import seedu.duke.exceptions.LessonNotFoundException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Timetable {
    private HashMap<String, ArrayList<Lesson>> userTimetable;

    public Timetable() {
        userTimetable = new HashMap<String, ArrayList<Lesson>>();
        ArrayList<Lesson> mondayList = new ArrayList<Lesson>();
        userTimetable.put("monday", mondayList);
        ArrayList<Lesson> tuesdayList = new ArrayList<Lesson>();
        userTimetable.put("tuesday", tuesdayList);
        ArrayList<Lesson> wednesdayList = new ArrayList<Lesson>();
        userTimetable.put("wednesday", wednesdayList);
        ArrayList<Lesson> thursdayList = new ArrayList<Lesson>();
        userTimetable.put("thursday", thursdayList);
        ArrayList<Lesson> fridayList = new ArrayList<Lesson>();
        userTimetable.put("friday", fridayList);
    }

    /**
     * Adds a lesson to the timetable based on day.
     *
     * @param newLesson The new lesson to be added to the timetable.
     * @throws DuplicateLessonException if there is a duplicate lesson already stored in the timetable.
     */
    public void addLesson(Lesson newLesson) throws DuplicateLessonException {
        if (userTimetable.get(newLesson.getDay()).contains(newLesson)) {
            throw new DuplicateLessonException("Duplicate lesson not allowed.");
        }
        try {
            if (newLesson.isValidDay() && newLesson.isValidStartTime() && newLesson.isValidEndTime()) {
                userTimetable.get(newLesson.getDay()).add(newLesson);
                System.out.print(Ui.printLessonAddedAcknowledgement(newLesson));
            }
        } catch (InvalidTimeFormatException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Deletes a lesson from the timetable based on day.
     *
     * @param oldLesson The old lesson to be deleted from the timetable.
     * @throws LessonNotFoundException if the lesson to be deleted is not stored in the timetable.
     */
    public void deleteLesson(Lesson oldLesson) throws LessonNotFoundException {
        if (!(userTimetable.get(oldLesson.getDay()).contains(oldLesson))) {
            throw new LessonNotFoundException("Lesson not found in timetable.");
        }
        userTimetable.get(oldLesson.getDay()).remove(oldLesson);
        System.out.print(Ui.printLessonDeletedAcknowledgement(oldLesson));
    }

    /**
     * Sequentially prints each day's lessons for a timetable.
     */
    public void printTimetable() {
        for (Map.Entry<String, ArrayList<Lesson>> set : userTimetable.entrySet()) {
            String day = set.getKey();
            ArrayList<Lesson> lessonList = set.getValue();
            System.out.println(day);
            System.out.print(Ui.printLessonsByDayInTimetable(lessonList));
        }
    }
}
