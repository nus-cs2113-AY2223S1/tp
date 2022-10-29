package recipeditor.command;

public class CommandResult {
    private String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        assert message != null;
        return message;
    }
}
