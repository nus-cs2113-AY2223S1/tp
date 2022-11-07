package seedu.duke.account;

import seedu.duke.InputManager;
import seedu.duke.UserNameFileWorkings;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.exception.FinanceException;

public class DeleteCommand {
    public static boolean handleDelete(Wallet wallet) throws FinanceException {
        AccountUi.showDeletionConfirmPrompt(wallet.getUserName());
        boolean isDeleted = false;
        String confirm = InputManager.receiveInputLine().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            WalletFile.deleteWallet(wallet);
            UserNameFileWorkings.deleteUserName(wallet.getUserName());
            isDeleted = true;
        }
        AccountUi.showDeletionResult(isDeleted);
        return isDeleted;
    }
}
