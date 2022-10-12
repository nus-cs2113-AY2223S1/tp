package seedu.commands;

import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.API_KEY_FILE_PATH;
import static seedu.common.CommonFiles.LTA_JSON_FILE;

import java.io.FileWriter;
import java.io.IOException;

import seedu.api.Api;
import seedu.exception.EmptyResponseException;
import seedu.exception.EmptySecretFileException;
import seedu.exception.FileWriteException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.NoFileFoundException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.ui.Ui;


/**
 * Represents the 'auth' command to authenticate user API key
 */
public class Auth {

    private final Api api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);
    private final Ui ui = new Ui();

    /**
     * Passes the API KEY into secret.txt
     * @param input Input string of User
     * @throws NoCommandArgumentException If no command is found
     * @throws FileWriteException If there is an error writing to file
     */
    public void sendApiKey(String input) throws NoCommandArgumentException, FileWriteException {
        String[] words = input.trim().split("\\s+", 2);
        if (words.length < 2 || words[1].length() == 0) {
            throw new NoCommandArgumentException("auth");
        } else {
            String apiKey = words[1];
            //follow the path of secret.txt -> rewrite the whole txt file with the API KEY / words[1];
            try {
                FileWriter fw = new FileWriter(API_KEY_FILE_PATH.toFile());
                fw.write(apiKey);
                ui.print("Successfully pushed api key into secret.txt");
                fw.close();
            }
            catch (IOException e){
                throw new FileWriteException(API_KEY_FILE_PATH.toString());
            }
        }
    }
    /**
     * Check if API Key is valid.
     * @param input Input string of user
     * @throws IOException
     * @throws EmptySecretFileException
     * @throws NoFileFoundException
     * @throws EmptyResponseException
     * @throws UnauthorisedAccessApiException
     */
    public void authenticate(String input) throws IOException, EmptySecretFileException, NoFileFoundException,
        EmptyResponseException, UnauthorisedAccessApiException, NoCommandArgumentException, FileWriteException {
        sendApiKey(input);
        api.loadApiKey(LTA_JSON_FILE, API_JSON_DIRECTORY);
        api.asyncExecuteRequest();
        api.fetchData();
    }
}
