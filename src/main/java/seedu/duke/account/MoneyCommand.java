package seedu.duke.account;

import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.Wallet;
import seedu.duke.WalletFile;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class MoneyCommand {
    public static void saveCommand(Wallet wallet, String commandArg) throws FinanceException {
        String[] splittedCommandArgs = commandArg.split(" ");
        String argCurrency,argAmount;
        if (splittedCommandArgs.length == 1) {
            argCurrency = wallet.getDefaultCurrency().getAbbrName();
            argAmount = splittedCommandArgs[0];
        } else if (splittedCommandArgs.length == 2) {
            argCurrency = splittedCommandArgs[0];
            argAmount = splittedCommandArgs[1];
        } else {
            throw new FinanceException(ExceptionCollection.SAVE_MONEY_EXCEPTION);
        }
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

    public static void transferWithdrawCommand(Wallet wallet, String commandArg) throws FinanceException {
        try {
            String[] splittedCommandArgs = commandArg.split(" ");
            String argCurrency,argAmount;
            if (splittedCommandArgs.length == 1) {
                argCurrency = wallet.getDefaultCurrency().getAbbrName();
                argAmount = splittedCommandArgs[0];
            } else if (splittedCommandArgs.length == 2) {
                argCurrency = splittedCommandArgs[0];
                argAmount = splittedCommandArgs[1];
            } else {
                throw new FinanceException(ExceptionCollection.WITHDRAW_MONEY_EXCEPTION);
            }
            CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(argCurrency);
            double amount = parsePositiveAmount(argAmount);
            wallet.withdrawMoney(currency, amount);
            WalletFile.updateWallet(wallet);
        }
        catch (NumberFormatException e){
            System.out.println("Please make sure to use integers for amount");
        }
    }

    public static void convertAllCommand(Wallet wallet, String commandArg) throws FinanceException {
        CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(commandArg);
        wallet.convertAllMoney(currency);
        WalletFile.updateWallet(wallet);
        AccountUi.showConvertAllResult(currency);
    }

    public static void exchangeCommand(Wallet wallet, String commandArg) throws FinanceException {
        String[] splittedCommandArgs = commandArg.split(" ");
        String argOldCurrency = splittedCommandArgs[0];
        String argOldAmount = splittedCommandArgs[1];
        String argNewCurrency = splittedCommandArgs[2];
        CurrencyStructure oldCurrency = CurrencyList.findCurrencyByAbbrName(argOldCurrency);
        double oldAmount = parsePositiveAmount(argOldAmount);
        CurrencyStructure newCurrency = CurrencyList.findCurrencyByAbbrName(argNewCurrency);
        double oldRate = oldCurrency.getRate();
        double newRate = newCurrency.getRate();
        double newAmount = oldAmount * (newRate / oldRate);
        wallet.exchangeMoney(oldCurrency, newCurrency, oldAmount);
        WalletFile.updateWallet(wallet);
        AccountUi.showExchangeResult(oldCurrency, oldAmount, newCurrency, newAmount);
    }

    public static void transferSaveCommand(Wallet wallet, String commandArg) throws FinanceException {
        try {
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
        }
        catch (NumberFormatException e){
            System.out.println("Please make sure to use integers for amount");
        }
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

    public static void transferMoney(Wallet currentUserWallet, String recipientUsername, String transferCurrency, int amount) throws FinanceException {
        String commandArg = transferCurrency + " " + amount;
        Wallet recipientWallet = WalletFile.getWallet(recipientUsername);
        String currentWalletName = currentUserWallet.getUserName();

        if (Wallet.getDeposits().size() == 0) {
            throw new FinanceException(ExceptionCollection.ACCOUNT_OVERDRAW);
        }
        else if (currentWalletName.equals(recipientUsername)) {
            throw new FinanceException(ExceptionCollection.SAME_USER_TRANSFER);
        }
        else {
            transferSaveCommand(recipientWallet, commandArg);
            transferWithdrawCommand(currentUserWallet, commandArg);

        }
        //i should withdraw the amount from the current wallet based on the withdraw function, but maybe a new one without print
        //then get the new wallet and increment that amount
    }
}
