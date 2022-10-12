package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private static final int MODULES_LIST_SIZE = 3;
    private int semester = 1;

    private List<List<SelectedModule>> selectedModulesList = new ArrayList<>(Collections.nCopies(MODULES_LIST_SIZE,
            new ArrayList<>()));

    public List<SelectedModule> getSelectedModulesList() {
        return selectedModulesList.get(semester);
    }

    public void setSelectedModulesList(List<SelectedModule> list) {
        this.selectedModulesList.set(semester, list);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void addSelectedModule(SelectedModule selectedModule) {
        selectedModulesList.get(semester).add(selectedModule);
    }

    public void removeSelectedModule(SelectedModule selectedModule) {
        selectedModulesList.get(semester).remove(selectedModule);
    }

}
