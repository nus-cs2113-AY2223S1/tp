package seedu.duke.model;

import java.util.HashMap;
import java.util.Map;

public class SelectedModule {

    private final Module module;

    private final int semester;
    private Map<LessonType, String> selectedSlots;

    public SelectedModule(Module module, int semester) {
        this.module = module;
        this.semester = semester;
        this.selectedSlots = new HashMap<>();
    }

    public Module getModule() {
        return module;
    }

    public boolean isFullySelected() {
        for (LessonType lessonType : module.semesterData.get(semester).getLessonTypes()) {
            if(!selectedSlots.containsKey(lessonType)) {
                return false;
            }
        }
        return true;
    }

    public void selectSlot(LessonType lessonType,String classNo) {
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
