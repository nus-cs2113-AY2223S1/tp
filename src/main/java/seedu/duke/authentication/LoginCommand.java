package seedu.duke.authentication;

import seedu.duke.InputManager;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.account.Account;
import seedu.duke.exception.FinanceException;

import java.io.IOException;

public class LoginCommand {
    public static void handleLogin() throws FinanceException {
        boolean isLoginSuccess = false;
        while(!isLoginSuccess){
            String username = null;
            String password = null;
            try {
                AuthenticationUi.showLoginInfo();
                String[] userInputs = InputManager.receiveLoginInputs();
                username = userInputs[0];
                password = userInputs[1];
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
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    AuthenticationUi.showLoginResults(isLoginSuccess);
                }
            } catch (FinanceException e) {
                if(username.equals("exit") || password.equals("exit")){
                    throw new FinanceException(FinanceException.ExceptionCollection.EXIT_LOGIN_TERMINAL);
                }
                else {
                    AuthenticationUi.showLoginResults(isLoginSuccess);
                }
            }
        }
    }
}
