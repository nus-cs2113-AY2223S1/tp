package recipeditor.command;

public class CommandResult {
    private String message;

    /**
     * Construct a CommandResult object, which consists of a single piece of message
     *
     * @param message the resulting message from executing command
     */
    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        assert message != null;
        return message;
    }
}
