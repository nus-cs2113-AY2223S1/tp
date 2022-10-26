package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private static final int MODULES_LIST_SIZE = 5;
    private int semester;

    private List<List<SelectedModule>> selectedModulesList;

    public State() {
        semester = 1;
        selectedModulesList = new ArrayList<>();
        for (int i = 0; i < MODULES_LIST_SIZE; i++) {
            selectedModulesList.add(new ArrayList<>());
        }
    }


    public List<SelectedModule> getSelectedModulesList() {
        return selectedModulesList.get(semester);
    }

    public void setSelectedModulesList(List<SelectedModule> list) {
        List<SelectedModule> currentSelectedModules = selectedModulesList.get(semester);
        currentSelectedModules.clear();
        currentSelectedModules.addAll(list);
        //selectedModulesList.set(semester, list);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int updatedSemester) {
        semester = updatedSemester;
    }

    public void addSelectedModule(SelectedModule selectedModule) {
        selectedModulesList.get(semester).add(selectedModule);
    }

    public void removeSelectedModule(SelectedModule selectedModule) {
        selectedModulesList.get(semester).remove(selectedModule);
    }

}
