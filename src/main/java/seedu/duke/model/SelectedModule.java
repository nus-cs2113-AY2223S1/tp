package seedu.duke.model;

import java.util.Map;
import java.util.Objects;
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SelectedModule)) {
            return false;
        }
        // typecast o to Complex so that we can compare data members
        SelectedModule selectedModule = (SelectedModule) object;

        return this.module.equals(selectedModule.module);
    }

}
