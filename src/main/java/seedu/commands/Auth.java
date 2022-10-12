package seedu.commands;

import static seedu.common.CommonFiles.API_KEY_FILE_PATH;

import java.io.FileWriter;
import java.io.IOException;

import seedu.api.Api;
import seedu.exception.EmptyAuthException;
import seedu.exception.EmptyResponseException;
import seedu.exception.EmptySecretFileException;
import seedu.exception.NoFileFoundException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.ui.Ui;

/**
 * Represents the 'auth' command to authenticate user API key
 */
public class Auth {
    private Api api = new Api();
    private Ui ui = new Ui();

    /**
     * Passes the API KEY into secret.txt
     * @param input Input string of User
     * @throws IOException
     */
    public void sendApiKey(String input) throws IOException, EmptyAuthException {
        String[] words = input.split("\\s+", 2);
        if (words.length < 2 || words[1].length() == 0) {
            throw new EmptyAuthException("No API Key entered! Try again.");
        } else {
            String apiKey = words[1];
            //follow the path of secret.txt -> rewrite the whole txt file with the API KEY / words[1];
            FileWriter fw = new FileWriter(API_KEY_FILE_PATH.toFile());
            fw.write(apiKey);
            ui.print("successfully pushed api key into secret.txt");
            fw.close();
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
     * @throws EmptyAuthException
     */
    public void authenticate(String input) throws IOException, EmptySecretFileException, NoFileFoundException,
            EmptyResponseException, UnauthorisedAccessApiException, EmptyAuthException {
        sendApiKey(input);
        api.loadApiKey();
        api.asyncExecuteRequest();
        api.fetchData();
    }
}
