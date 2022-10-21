package seedu.moneygowhere.commands;

/**
 * Stores the merge external file command and its arguments.
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
