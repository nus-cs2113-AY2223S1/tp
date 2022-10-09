package seedu.duke.model;

import java.util.HashMap;
import java.util.Map;
public class SelectedModule {

    private final Module module;
    private Map<LessonType, String> selectedSlots;

    public SelectedModule(Module module) {
        this.module = module;
        this.selectedSlots = new HashMap<>();
    }

    public Module getModule() {
        return module;
    }

    public boolean isFullySelected(int semester) {
        for (LessonType lessonType : module.semesterData.get(semester).getLessonTypes()) {
            if(!selectedSlots.containsKey(lessonType)) return false;
        }
        return true;
    }

    public void selectSlot(String slot) {
        // validate and switch if there is a current one selected
        if(selectedSlots.containsValue(slot)){

        }
    }

    public void unselectSlot(String slot) {
    }

    public Map<LessonType, String> getSelectedSlots() {
        return selectedSlots;
    }

}
