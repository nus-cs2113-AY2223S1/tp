package seedu.duke.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * SelectedModule represents a user's selection of a module to add to his/her timetable. The selection involves a module
 * for a certain semester and the user's selection of the timetable slots.
 */
public class SelectedModule {

    private final Module module;
    private final int semester;
    private Map<LessonType, String> selectedSlots;

    /**
     * Constructs a SelectedModule object. 
     * This initializes the lesson slot selection to the first slot for each lesson type.
     * @param module Module to be selected
     * @param semester Semester of selection
     */
    public SelectedModule(Module module, int semester) {
        this.module = module;
        this.semester = semester;
        this.selectedSlots = new TreeMap<>();
        for (LessonType lessonType : module.getSemesterData(semester).getLessonTypes()) {
            selectedSlots.put(lessonType,
                    module.getSemesterData(semester).getClassNosByType(lessonType).iterator().next());
        }
    }

    /**
     * Gets the module for the selection.
     * @return The module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Selects a slot. TODO valid arguments
     * @param lessonType Lesson type
     * @param classNo Class number
     */
    public void selectSlot(LessonType lessonType, String classNo) {
        // overwrites currently selected slot if any
        selectedSlots.put(lessonType, classNo);
    }

    /**
     * Gets the map of selected slots.
     * @return The map of selected slots.
     */
    public Map<LessonType, String> getSelectedSlots() {
        return selectedSlots;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SelectedModule)) {
            return false;
        }
        // typecast o to SelectedModule so that we can compare data members
        SelectedModule selectedModule = (SelectedModule) object;
        return this.semester == selectedModule.semester && this.module.equals(selectedModule.module);
    }

}
