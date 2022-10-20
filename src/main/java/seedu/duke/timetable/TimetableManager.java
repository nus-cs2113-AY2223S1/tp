package seedu.duke.timetable;

import seedu.duke.exceptions.DuplicateLessonException;
import seedu.duke.exceptions.LessonNotFoundException;
import seedu.duke.exceptions.TimetableNotFoundException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimetableManager {
    private HashMap<String, Timetable> timetableManager;

    public TimetableManager() {
        this.timetableManager = new HashMap<String, Timetable>();
    }

    public Timetable getTimetableByUniversityName(String universityName) {
        return timetableManager.get(universityName);
    }

    public void createTimetable(String universityName) {
        assert universityName.length() > 0 : "Input university name cannot be empty";
        if (timetableManager.containsKey(universityName)) {
            System.out.println("Error: Timetable for " + universityName + " already exists");
        } else {
            Timetable newTimetable = new Timetable();
            timetableManager.put(universityName, newTimetable);
            System.out.print(Ui.printTimetableCreatedAcknowledgement(universityName));
        }
    }

    public boolean foundKeyAll(String universityName) {
        return timetableManager.containsKey(universityName);
    }

    public void deleteTimetable(String universityName) {
        assert universityName.length() > 0 : "Input university name cannot be empty";
        if (!foundKeyAll(universityName)) {
            System.out.println("Error: Timetable for " + universityName + " already exists");
        } else {
            timetableManager.remove(universityName);
            System.out.print(Ui.printTimetableDeletedAcknowledgement(universityName));
        }
    }

    public void addLesson(Lesson newLesson) {
        String universityName = newLesson.getUniversity().getName();
        try {
            timetableManager.get(universityName).addLesson(newLesson);
        } catch (DuplicateLessonException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void deleteLesson(Lesson oldLesson) {
        String universityName = oldLesson.getUniversity().getName();
        try {
            timetableManager.get(universityName).deleteLesson(oldLesson);
        } catch (LessonNotFoundException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void printTimetable(String universityName) throws TimetableNotFoundException {
        if (!timetableManager.containsKey(universityName)) {
            throw new TimetableNotFoundException("Timetable for " + universityName + " not found!");
        } else {
            Timetable timetable = timetableManager.get(universityName);
            System.out.println("Timetable for " + universityName + ":");
            timetable.printTimetable();
        }
    }

    public void printAllTimetables() {
        for (Map.Entry<String, Timetable> set : timetableManager.entrySet()) {
            String universityName = set.getKey();
            Timetable timetable = timetableManager.get(universityName);
            System.out.println("Timetable for " + universityName + ":");
            timetable.printTimetable();
        }
    }
}
