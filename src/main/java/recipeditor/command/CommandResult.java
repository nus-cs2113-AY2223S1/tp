package recipeditor.command;

public class CommandResult {
    private String message;
    //TODO: elaborate on Command Result

    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "Echo CommandResult message: " + message;
    }
}
