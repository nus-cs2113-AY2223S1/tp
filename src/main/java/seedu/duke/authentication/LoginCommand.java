package seedu.duke.authentication;

import java.io.FileNotFoundException;

import seedu.duke.InputManager;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.account.Account;
import seedu.duke.exception.FinanceException;

public class LoginCommand {
    public static void handleLogin() throws FinanceException {
        Boolean isLoginSuccess = false;
        try {
            AuthenticationUi.showLoginInfo();
            String[] userInputs = InputManager.receiveLoginInputs();
            String username = userInputs[0];
            String password = userInputs[1];
            Wallet wallet = WalletFile.getWallet(username);
            if (wallet.getPassWord().equals(password)) {
                isLoginSuccess = true;
                AuthenticationUi.showLoginResults(isLoginSuccess);
                Account account = new Account(wallet);
                account.handleAccountRequest();
            } else {
                isLoginSuccess = false;
                AuthenticationUi.showLoginResults(isLoginSuccess);
            }
        } catch (FinanceException e) {
            //Ui.showStandardOutput("Username not found, please enter another username");
            AuthenticationUi.showLoginResults(isLoginSuccess);
        }
    }
}
