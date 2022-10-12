package seedu.duke.model;

import java.util.Map;
import java.util.TreeMap;

public class SelectedModule {

    private final Module module;

    private final int semester;
    private Map<LessonType, String> selectedSlots;

    public SelectedModule(Module module, int semester) {
        this.module = module;
        this.semester = semester;
        this.selectedSlots = new TreeMap<>();
        for (LessonType lessonType : module.getSemesterData(semester).getLessonTypes()) {
            selectedSlots.put(lessonType,
                    module.getSemesterData(semester).getClassNosByType(lessonType).iterator().next());
        }
    }

    public Module getModule() {
        return module;
    }

    public boolean isFullySelected() {
        for (LessonType lessonType : module.semesterData.get(semester).getLessonTypes()) {
            if (!selectedSlots.containsKey(lessonType)) {
                return false;
            }
        }
        return true;
    }

    public void selectSlot(LessonType lessonType, String classNo) {
        // overwrites currently selected slot if any
        selectedSlots.put(lessonType, classNo);
    }

    public void unselectSlot(LessonType lessonType) {
        selectedSlots.remove(lessonType);
    }

    public Map<LessonType, String> getSelectedSlots() {
        return selectedSlots;
    }

}
