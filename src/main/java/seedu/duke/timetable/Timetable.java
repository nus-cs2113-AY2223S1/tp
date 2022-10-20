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

    public void addLesson(Lesson newLesson) throws DuplicateLessonException {
        if (userTimetable.get(newLesson.getDay()).contains(newLesson)) {
            throw new DuplicateLessonException("Duplicate lesson not allowed.");
        }
        try {
            if (newLesson.isValidDay() && newLesson.isValidStartTime() && newLesson.isValidEndTime()) {
                userTimetable.get(newLesson.getDay()).add(newLesson);
            }
        } catch (InvalidTimeFormatException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void deleteLesson(Lesson oldLesson) throws LessonNotFoundException {
        if (!(userTimetable.get(oldLesson.getDay()).contains(oldLesson))) {
            throw new LessonNotFoundException("Lesson not found in timetable.");
        }
        userTimetable.get(oldLesson.getDay()).remove(oldLesson);
    }

    public void printTimetable() {
        for (Map.Entry<String, ArrayList<Lesson>> set : userTimetable.entrySet()) {
            String day = set.getKey();
            ArrayList<Lesson> lessonList = set.getValue();
            System.out.println(day);
            System.out.print(Ui.printLessonsByDayInTimetable(lessonList));
        }
    }
}
