package seedu.moneygowhere.commands;

//@@author penguin-s

/**
 * Stores the command View-Target and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewTarget extends ConsoleCommand {
    private int targetIndex;

    public ConsoleCommandViewTarget(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }
}
