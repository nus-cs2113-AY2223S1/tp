package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.List;

public class State {

    private int semester = 1;
    List<List<SelectedModule>> selectedModulesList;

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
