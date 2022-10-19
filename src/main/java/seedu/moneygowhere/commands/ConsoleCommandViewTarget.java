package seedu.moneygowhere.commands;

/**
 * Stores the view-target command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewTarget {
    private int targetIndex;

    public ConsoleCommandViewTarget(int targetIndex){
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex(){ return targetIndex; }
}
