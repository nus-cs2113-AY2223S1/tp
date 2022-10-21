package seedu.commands;

import seedu.api.Api;
import seedu.common.CommonFiles;
import seedu.data.CarparkList;
import seedu.exception.ParkingException;

public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private final Api api;

    public UpdateCommand(Api api) {
        this.api = api;
    }

    private CarparkList updateCarparkList(Api api) {
        try {
            //fetch api
            api.syncFetchData();
            //update json
            CarparkList updatedCarparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            return updatedCarparkList;
        } catch (ParkingException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public CommandResult execute() {
        CarparkList result = updateCarparkList(api);
        return new CommandResult(result.toString());
    }
}
