package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.List;
public class State {

    public int semester = 0;
    List<List<SelectedModule>> selectedModulesList;

    public List<SelectedModule> getSelectedModulesList() {
        return selectedModulesList.get(semester);
    }

    public void setSelectedModulesList(List<SelectedModule> selectedModulesList) {
        this.selectedModulesList.set(semester, selectedModulesList);
    }
}
