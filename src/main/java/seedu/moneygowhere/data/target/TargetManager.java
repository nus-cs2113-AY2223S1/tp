package seedu.moneygowhere.data.target;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.target.TargetManagerTargetNotFoundException;
import seedu.moneygowhere.storage.LocalStorage;

import java.util.ArrayList;

//@@author penguin-s

/**
 * Stores and manages a list of expenses.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class TargetManager {
    private ArrayList<Target> targets;

    public TargetManager() {
        targets = new ArrayList<>();
    }

    public void addTarget(Target target, LocalStorage localStorage) {
        targets.add(target);
        localStorage.setSavedTargets(targets);
    }

    public Target getTarget(int targetIndex) throws TargetManagerTargetNotFoundException {
        try {
            return targets.get(targetIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new TargetManagerTargetNotFoundException(Messages.TARGET_MANAGER_ERROR_TARGET_NOT_FOUND);
        }
    }

    public ArrayList<Target> getTargets() {
        return new ArrayList<>(targets);
    }

    //@@author LokQiJun
    public void setTargets(ArrayList<Target> savedTargets) {
        this.targets = new ArrayList<Target>(savedTargets);
    }

    public void deleteTarget(int targetIndex, LocalStorage localStorage)
            throws TargetManagerTargetNotFoundException {
        try {
            targets.remove(targetIndex);
            localStorage.setSavedTargets(targets);
        } catch (IndexOutOfBoundsException exception) {
            throw new TargetManagerTargetNotFoundException(Messages.TARGET_MANAGER_ERROR_TARGET_NOT_FOUND);
        }
    }

    public void editTarget(int targetIndex, Target target, LocalStorage localStorage)
            throws TargetManagerTargetNotFoundException {
        try {
            targets.set(targetIndex, target);
            localStorage.setSavedTargets(targets);
        } catch (IndexOutOfBoundsException exception) {
            throw new TargetManagerTargetNotFoundException(Messages.TARGET_MANAGER_ERROR_TARGET_NOT_FOUND);
        }
    }

    public void updateTargets(ArrayList<Target> targets) {
        this.targets = targets;
    }
}
