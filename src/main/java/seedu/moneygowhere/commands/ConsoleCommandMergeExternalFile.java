package seedu.moneygowhere.commands;

/**
 * Stores the command Merge-File and its arguments.
 */
public class ConsoleCommandMergeExternalFile extends ConsoleCommand {
    private String filePath;

    public ConsoleCommandMergeExternalFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
