package seedu.duke;

import java.io.FileNotFoundException;

public class LoginCommand {
    public static void handleLogin() throws FinanceException {
        Boolean isLoginSuccess = false;
        try {
            Ui.showLoginInfo();
            String[] userInputs = InputManager.receiveLoginInputs();
            String username = userInputs[0];
            String password = userInputs[1];
            Wallet wallet = WalletFile.getWallet(username);
            if (wallet.getPassWord().equals(password)) {
                isLoginSuccess = true;
                Ui.showLoginResults(isLoginSuccess);
                Account.accountCommands(wallet);
            } else {
                isLoginSuccess = false;
                Ui.showLoginResults(isLoginSuccess);
            }
        } catch (FileNotFoundException e) {
            //Ui.showStandardOutput("Username not found, please enter another username");
            Ui.showLoginResults(isLoginSuccess);
        }
    }
}
