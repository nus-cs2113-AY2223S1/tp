package seedu.duke;

public class DeleteCommand {
    public static Boolean handleDelete(Wallet wallet) throws FinanceException {
        Ui.showDeletionConfirmPrompt(wallet.getUserName());
        Boolean isDeleted = false;
        String confirm = InputManager.receiveInputLine().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            WalletFile.deleteWallet(wallet);
            UserNameFileWorkings.deleteUserName(wallet.getUserName());
            isDeleted = true;
        }
        Ui.showDeletionResult(isDeleted);
        return isDeleted;
    }
}
