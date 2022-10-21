package seedu.duke.account;

import java.util.List;

import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class MoneyCommand {
    public static void saveCommand(Wallet wallet, String commandArg) throws FinanceException {
        String[] splittedCommandArgs = commandArg.split(" ");
        if (splittedCommandArgs.length != 2) {
            throw new FinanceException(ExceptionCollection.SAVE_MONEY_EXCEPTION);
        }
        String argCurrency = splittedCommandArgs[0];
        String argAmount = splittedCommandArgs[1];
        CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(argCurrency);
        double amount = parsePositiveAmount(argAmount);
        wallet.saveMoney(currency, amount);
        WalletFile.updateWallet(wallet);
        AccountUi.showSaveResult(currency, amount);
    }

    public static void withdrawCommand(Wallet wallet, String commandArg) throws FinanceException {
        String[] splittedCommandArgs = commandArg.split(" ");
        if (splittedCommandArgs.length != 2) {
            throw new FinanceException(ExceptionCollection.WITHDRAW_MONEY_EXCEPTION);
        }
        String argCurrency = splittedCommandArgs[0];
        String argAmount = splittedCommandArgs[1];
        CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(argCurrency);
        double amount = parsePositiveAmount(argAmount);
        wallet.withdrawMoney(currency, amount);
        WalletFile.updateWallet(wallet);
        AccountUi.showWithdrawResult(currency, amount);
    }

    public static double parsePositiveAmount(String argAmount) throws FinanceException{
        double amount;
        try {
            amount = Double.parseDouble(argAmount);
        } catch (NumberFormatException e) {
            throw new FinanceException(ExceptionCollection.AMOUNT_PARSE_EXCEPTION);
        }
        if (amount < 0) {
            throw new FinanceException(ExceptionCollection.AMOUNT_NEGATIVE_EXCEPTION);
        }
        return amount;
    }

    public static double parseAmount(String argAmount) throws FinanceException{
        double amount;
        try {
            amount = Double.parseDouble(argAmount);
        } catch (NumberFormatException e) {
            throw new FinanceException(ExceptionCollection.AMOUNT_PARSE_EXCEPTION);
        }
        return amount;
    }

    
}
