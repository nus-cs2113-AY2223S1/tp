package seedu.commands;

import seedu.api.Api;
import seedu.common.CommonFiles;
import seedu.data.CarparkList;
import seedu.exception.ParkingException;
import seedu.files.FileStorage;

/**
 * Represents a command to update the Json file according to the latest API.
 */
public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private final Api api;

    /**
     * Constructor for the {@link UpdateCommand} class.
     *
     * @param api {@link Api} instance to be used to fetch data.
     * @param carparkList {@link CarparkList} instance to be updated.
     */
    public UpdateCommand(Api api, CarparkList carparkList) {
        this.api = api;
        this.carparkList = carparkList;
    }

    /**
     * Updates the carpark list.
     *
     * @param api api that the programme has authenticated.
     * @return updated carpark list
     */
    private String updateCarparkList(Api api, CarparkList carparkList) {
        try {
            //fetch api
            api.syncFetchData();
            CarparkList newCarparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            carparkList.update(newCarparkList);
            FileStorage.saveCarparkList(carparkList);
            return "Update Successful.";
        } catch (ParkingException e) {
            return "Update unsuccessful. " + e.getMessage();
        }
    }

    @Override
    public CommandResult execute() {
        String message = updateCarparkList(api, carparkList);
        return new CommandResult(message);
    }
}
