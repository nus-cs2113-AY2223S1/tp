package seedu.duke.model;

import java.util.HashSet;
import java.util.Set;

public class SelectedModule {

    private final Module module;
    private Set<String> selectedSlots;

    public SelectedModule(Module module) {
        this.module = module;
        this.selectedSlots = new HashSet<>();
    }

    public Module getModule() {
        return module;
    }

    public boolean isFullySelected() {
        // whether all lecture/tutorial/lab slots have been selected
        return false;
    }

    public void selectSlot(String slot) {
        // validate and switch if there is a current one selected
    }

    public void unselectSlot(String slot) {
    }

    public Set<String> getSelectedSlots() {
        return selectedSlots;
    }

}
