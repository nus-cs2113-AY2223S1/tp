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

    /**
     * Creates a timetable for a university.
     *
     * @param universityName The name of the university for which the timetable is created.
     */
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

    /**
     * Deletes the timetable for a university.
     *
     * @param universityName The name of the university for which the timetable is deleted.
     */
    public void deleteTimetable(String universityName) {
        assert universityName.length() > 0 : "Input university name cannot be empty";
        if (!foundKeyAll(universityName)) {
            System.out.println("Error: Timetable for " + universityName + " does not exist");
        } else {
            timetableManager.remove(universityName);
            System.out.print(Ui.printTimetableDeletedAcknowledgement(universityName));
        }
    }

    /**
     * Adds a lesson to the timetable based on day.
     *
     * @param newLesson The new lesson to be added to the timetable.
     */
    public void addLesson(Lesson newLesson) {
        String universityName = newLesson.getUniversity().getName();
        try {
            timetableManager.get(universityName).addLesson(newLesson);
        } catch (DuplicateLessonException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Deletes a lesson from the timetable based on day.
     *
     * @param oldLesson The old lesson to be deleted from the timetable.
     */
    public void deleteLesson(Lesson oldLesson) {
        String universityName = oldLesson.getUniversity().getName();
        try {
            timetableManager.get(universityName).deleteLesson(oldLesson);
        } catch (LessonNotFoundException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Sequentially prints each day's lessons for the timetable for a particular university.
     *
     * @param universityName The name of the university for which the timetable is to be printed.
     * @throws TimetableNotFoundException if the timetable to be printed is not stored in the manager.
     */
    public void printTimetable(String universityName) throws TimetableNotFoundException {
        if (!timetableManager.containsKey(universityName)) {
            throw new TimetableNotFoundException("Timetable for " + universityName + " not found!");
        } else {
            Timetable timetable = timetableManager.get(universityName);
            System.out.println("Timetable for " + universityName + ":");
            timetable.printTimetable();
        }
    }

    /**
     * Sequentially prints all the timetables that the user has created for various universities.
     */
    public void printAllTimetables() {
        for (Map.Entry<String, Timetable> set : timetableManager.entrySet()) {
            String universityName = set.getKey();
            Timetable timetable = timetableManager.get(universityName);
            System.out.println("Timetable for " + universityName + ":");
            timetable.printTimetable();
        }
    }
}
