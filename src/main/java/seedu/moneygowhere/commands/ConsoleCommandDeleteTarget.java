package seedu.moneygowhere.commands;

//@@author penguin-s

/**
 * Stores the command Delete-Target and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteTarget extends ConsoleCommand {
    private int targetIndex;

    public ConsoleCommandDeleteTarget(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }
}
