package seedu.moneygowhere.commands;

//@@author LokQiJun

/**
 * Stores the command Merge-File and its arguments.
 */
public class ConsoleCommandMergeFile extends ConsoleCommand {
    private String filePath;

    public ConsoleCommandMergeFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
