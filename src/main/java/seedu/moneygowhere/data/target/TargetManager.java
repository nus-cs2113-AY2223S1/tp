package seedu.moneygowhere.data.target;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.target.TargetManagerTargetNotFoundException;

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

    public void addTarget(Target target) {
        targets.add(target);
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

    public void deleteTarget(int targetIndex) throws TargetManagerTargetNotFoundException {
        try {
            targets.remove(targetIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new TargetManagerTargetNotFoundException(Messages.TARGET_MANAGER_ERROR_TARGET_NOT_FOUND);
        }
    }

    public void editTarget(int targetIndex, Target target) throws TargetManagerTargetNotFoundException {
        try {
            targets.set(targetIndex, target);
        } catch (IndexOutOfBoundsException exception) {
            throw new TargetManagerTargetNotFoundException(Messages.TARGET_MANAGER_ERROR_TARGET_NOT_FOUND);
        }
    }

    public void updateTargets(ArrayList<Target> targets) {
        this.targets = targets;
    }
}
