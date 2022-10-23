package seedu.commands;

import static seedu.common.CommonFiles.API_KEY_FILE_PATH;

import java.io.FileWriter;
import java.io.IOException;

import seedu.api.Api;
import seedu.exception.FileWriteException;



/**
 * Represents AuthCommand to authenticate user input api key
 */
public class AuthCommand extends Command {
    public static final String COMMAND_WORD = "auth";

    private final String apiKey;
    private final Api api;

    /**
     * Constructor for AuthCommand
     *
     * @param api authenticated api
     * @param apiKey apiKey to be authenticated
     */
    public AuthCommand(Api api, String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    /**
     * Executes the AuthCommand
     *
     * @return the command result of AuthCommand
     */
    @Override
    public CommandResult execute() {
        try {
            if (api.isApiValid(apiKey)) {
                saveApiKey(apiKey);
                return new CommandResult("Authenticated successfully.");
            }
            throw new FileWriteException(API_KEY_FILE_PATH.toString());
        } catch (FileWriteException e) {
            e.getMessage();
            return new CommandResult("Authenticated failed.");
        }
    }

    /**
     * To save the user input api key into secret.txt file
     *
     * @param apiKey user input api key
     * @throws FileWriteException Throws this error when the program is unable to write to secret.txt
     */
    private void saveApiKey(String apiKey) throws FileWriteException {
        try {
            FileWriter fw = new FileWriter(API_KEY_FILE_PATH.toFile());
            fw.write(apiKey);
            fw.close();
        } catch (IOException e) {
            throw new FileWriteException(API_KEY_FILE_PATH.toString());
        }
    }
}
