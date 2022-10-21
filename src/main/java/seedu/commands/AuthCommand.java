package seedu.commands;

import seedu.api.Api;
import seedu.exception.FileWriteException;
import seedu.exception.NoCommandArgumentException;

import java.io.FileWriter;
import java.io.IOException;

import static seedu.common.CommonFiles.API_KEY_FILE_PATH;

public class AuthCommand extends Command {
    public static final String COMMAND_WORD = "auth";

    private final Api api;

    public AuthCommand(Api api) {
        this.api = api;
    }

    @Override
    public CommandResult execute() {
        try {
            if (api.isApiValid(api.toString())) {
                saveApiKey(api);
                return new CommandResult("Authenticated successfully.");
            } throw new FileWriteException(API_KEY_FILE_PATH.toString());
        } catch (FileWriteException e) {
            e.getMessage();
            return new CommandResult("Authenticated failed.");
        }
    }

    private void saveApiKey(Api api) throws FileWriteException {
        try {
            String apiKey = api.toString();
            FileWriter fw = new FileWriter(API_KEY_FILE_PATH.toFile());
            fw.write(apiKey);
            fw.close();
        } catch (IOException e) {
            throw new FileWriteException(API_KEY_FILE_PATH.toString());
        }
    }
}
