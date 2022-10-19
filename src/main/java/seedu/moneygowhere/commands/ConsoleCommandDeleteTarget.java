package seedu.moneygowhere.commands;

/**
 * Stores the delete-target command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteTarget extends ConsoleCommand{
    private int targetIndex;

    public ConsoleCommandDeleteTarget(int targetIndex){ this.targetIndex = targetIndex; }

    public int getTargetIndex(){ return targetIndex; }
}
