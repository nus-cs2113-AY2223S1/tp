package seedu.commands;

import static seedu.common.CommonFiles.API_KEY_FILE_PATH;

import java.io.FileWriter;
import java.io.IOException;

import seedu.api.Api;
import seedu.exception.EmptyResponseException;
import seedu.exception.FileWriteException;
import seedu.exception.UnauthorisedAccessApiException;


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
            if (apiKey.equals("status")) {
                String message = api.getApiAuthStatus();
                return new CommandResult(message);
            } else if (apiKey.equals("default")) {
                api.loadDefaultApiKey();
                String message = fetchApiData();
                return new CommandResult("Authenticated successfully using default API Key." + message);
            } else if (api.isApiValid(apiKey)) {
                saveApiKey(apiKey);
                String message = fetchApiData();
                return new CommandResult("Authenticated successfully." + message);
            }
            return new CommandResult("Authentication failed.");
        } catch (FileWriteException | UnauthorisedAccessApiException e) {
            return new CommandResult("Authentication failed. " + e.getMessage());
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

    /**
     * Fetch API Data from LTA and adds useful information for error handling.
     *
     * @return Appended message for more information.
     * @throws UnauthorisedAccessApiException if authentication fails.
     */
    private String fetchApiData() throws UnauthorisedAccessApiException {
        try {
            api.syncFetchData();
            return "";
        } catch (FileWriteException e) {
            return " However, data is not saved to local file. " + e.getMessage();
        } catch (EmptyResponseException e) {
            return " However, data from API is empty.";
        }
    }
}
