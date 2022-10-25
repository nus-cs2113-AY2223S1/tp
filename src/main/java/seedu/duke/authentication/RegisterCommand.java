package seedu.duke.authentication;

import java.io.IOException;
import java.util.List;

import seedu.duke.InputManager;
import seedu.duke.UserNameFileWorkings;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class RegisterCommand {
    public static void handleRegister() throws FinanceException {
        AuthenticationUi.showRegisterInfo();
        List<String> existingUsernames;
        try {
            existingUsernames = UserNameFileWorkings.userNameFile();
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.USERFILE_CREATE_EXCEPTION);
        }
        String[] userInputs = InputManager.receiveValidRegisterInputs(existingUsernames);
        String username = userInputs[0];
        String passWord = userInputs[1];
        try {
            UserNameFileWorkings.writeToUserNames(username);
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.USERFILE_WRITE_EXCEPTION);
        }
        Wallet newWallet = new Wallet(username,
                passWord);
        try {
            WalletFile.createNewWallet(newWallet);
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.USERFILE_CREATE_EXCEPTION);
        }
        AuthenticationUi.showRegisterSuccessInfo();
    }
}
