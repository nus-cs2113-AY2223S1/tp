package seedu.duke;

import java.util.List;

public class Login {
    public static Wallet handleLogin(List<String> existingUsernames) throws FinanceException{
        String[] userInputs = InputManager.receiveLoginInputs();
        String username = userInputs[0];
        String password = userInputs[1];
        if(!existingUsernames.contains(username)){
            Ui.showLoginResults(false);
            return null;
        }
        Wallet wallet = WalletFile.getWallet(username);
        if(!wallet.getPassword().equals(password)){
            Ui.showLoginResults(false);
            return null;
        }
        Ui.showLoginResults(true);
        return wallet;
    }
}
