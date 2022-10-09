package seedu.duke.utils;

import seedu.duke.model.SelectedModule;

import java.util.List;
public class State {
    List<SelectedModule> selectedModulesList;

    public List<SelectedModule> getSelectedModulesList() {
        return selectedModulesList;
    }

    public void setSelectedModulesList(List<SelectedModule> selectedModulesList) {
        this.selectedModulesList = selectedModulesList;
    }
}
