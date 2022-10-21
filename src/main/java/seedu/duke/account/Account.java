package seedu.duke.account;

import java.util.List;

import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.InputManager;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class Account {
    protected Wallet wallet;

    public Account(Wallet wallet) {
        this.wallet = wallet;
    }

    public void handleAccountRequest() {
        boolean isAccountExit = false;
        AccountUi.showAccountEntryMessage(wallet.getUserName());
        CurrencyStructure currency;
        while (!isAccountExit) {
            String in = InputManager.receiveInputLine().toLowerCase();
            String[] splits = in.split(" ", 2);
            String[] commandParams = (splits.length == 2) ? splits : new String[] {splits[0], ""};
            String commandType = commandParams[0];
            String commandArg = commandParams[1];
            try {
                switch (commandType) {
                case "setdefault":
                    setDefaultCurrency(commandArg);
                    break;
                case "balance":
                    double totalBalance = wallet.getTotalBalance();
                    currency = wallet.getDefaultCurrency();
                    AccountUi.showTotalBalance(totalBalance, currency);
                    break;
                case "detail":
                    String username = wallet.getUserName();
                    currency = wallet.getDefaultCurrency();
                    List<Deposit> deposits = wallet.getDeposits();
                    AccountUi.showWalletDetails(username, currency, deposits);
                    break;
                case "save":
                    MoneyCommand.saveCommand(wallet,commandArg);
                    break;
                case "withdraw":
                    MoneyCommand.withdrawCommand(wallet, commandArg);
                    break;
                case "delete":
                    isAccountExit = DeleteCommand.handleDelete(wallet);
                    break;
                case "exit":
                    isAccountExit = true;
                    AccountUi.showAccountExitMessage(wallet.getUserName());
                    break;
                default:
                    throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            } catch (FinanceException e) {
                e.handleException();
            }
        }
    }

    private void setDefaultCurrency(String commandArg) throws FinanceException {
        if (commandArg.isEmpty()) {
            throw new FinanceException(ExceptionCollection.SET_DEFAULT_CURRENCY_EXCEPTION);
        }
        CurrencyStructure oldCurrency = wallet.getDefaultCurrency();
        CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(commandArg);
        wallet.setDefaultCurrency(currency);
        CurrencyStructure newCurrency = wallet.getDefaultCurrency();
        double rate = newCurrency.getRate() / oldCurrency.getRate();
        double totalBalance = wallet.getTotalBalance() * rate;
        wallet.setTotalBalance(totalBalance);
        WalletFile.updateWallet(wallet);
        AccountUi.showSetDefaultCurrencyResult(oldCurrency.getAbbrName(), newCurrency.getAbbrName());
    }

}
