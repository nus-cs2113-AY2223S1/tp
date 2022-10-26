package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private static final int MODULES_LIST_SIZE = 5;
    private int semester;

    private final List<List<SelectedModule>> selectedModulesList;

    public State() {
        this.semester = 1;
        this.selectedModulesList = new ArrayList<>();
        for (int i = 0; i < MODULES_LIST_SIZE; i++) {
            this.selectedModulesList.add(new ArrayList<>());
        }
    }


    public List<SelectedModule> getSelectedModulesList() {
        return this.selectedModulesList.get(this.semester);
    }

    public void setSelectedModulesList(List<SelectedModule> list) {
        this.selectedModulesList.set(this.semester, list);
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int updatedSemester) {
        this.semester = updatedSemester;
    }

    public void addSelectedModule(SelectedModule selectedModule) {
        this.selectedModulesList.get(this.semester).add(selectedModule);
    }

    public void removeSelectedModule(SelectedModule selectedModule) {
        this.selectedModulesList.get(this.semester).remove(selectedModule);
    }

}
