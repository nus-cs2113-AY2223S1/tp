package seedu.duke.authentication;

import seedu.duke.InputManager;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.account.Account;
import seedu.duke.exception.FinanceException;

public class LoginCommand {
    public static void handleLogin() throws FinanceException {
        boolean isLoginSuccess = false;
        while(!isLoginSuccess){
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
                    boolean isAccountExit = false;
                    while(!isAccountExit){
                        try{
                            isAccountExit = account.handleAccountRequest();
                        }
                        catch(FinanceException e){
                            e.handleException();
                        }
                    }
                } else {
                    AuthenticationUi.showLoginResults(isLoginSuccess);
                }
            } catch (FinanceException e) {
                AuthenticationUi.showLoginResults(isLoginSuccess);
            }
        }
    }
}
