package seedu.commands;

import seedu.api.Api;
import seedu.exception.ParkingException;

/**
 * Represents a command to update the Json file according to the latest API.
 */
public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private final Api api;

    public UpdateCommand(Api api) {
        this.api = api;
    }

    /**
     * Updates the carpark list.
     *
     * @param api api that the programme has authenticated.
     * @return updated carpark list
     */
    private String updateCarparkList(Api api) {
        try {
            //fetch api
            api.syncFetchData();
            // Todo: Actually update the carpark instance.
            return "Update Successful.";
        } catch (ParkingException e) {
            return "Update unsuccessful. " + e.getMessage();
        }
    }

    @Override
    public CommandResult execute() {
        String message = updateCarparkList(api);
        return new CommandResult(message);
    }
}
