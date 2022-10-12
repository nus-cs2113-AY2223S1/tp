package seedu.commands;

import seedu.api.Api;
import seedu.exception.*;
import seedu.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static seedu.common.CommonFiles.API_KEY_FILE_PATH;

public class Auth {
    Api api = new Api();
    Ui ui = new Ui();

    /**
     * Passes the API KEY into secret.txt
     * @param input Input string of User
     * @throws IOException
     */
    public void sendApiKey(String input) throws IOException, EmptyAuthException{
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
     * @return Returns true if API Key is valid, else return false.
     * @throws IOException
     */
    public void authenticate(String input) throws IOException, EmptySecretFileException, NoFileFoundException,
            EmptyResponseException, UnauthorisedAccessApiException, EmptyAuthException {
        sendApiKey(input);
        api.loadApiKey();
        api.asyncExecuteRequest();
        api.fetchData();
    }
}
