package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import seedu.duke.account.Deposit;
import seedu.duke.account.MoneyCommand;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class WalletFile {

    static final Path FILE_PATH = Paths.get("src","main","data");

    public static void createNewWallet(Wallet newWallet) throws IOException {
        File f = new File(FILE_PATH + "/" + newWallet.userName + ".txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        String encodedPassword = Base64.getEncoder().encodeToString(newWallet.passWord.getBytes());
        fw.write("password:" + encodedPassword);
        fw.write(System.lineSeparator());
        fw.write("defaultCurrency:" + newWallet.defaultCurrency.getAbbrName());
        fw.write(System.lineSeparator());
        fw.write("balance:" + newWallet.totalBalance);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static void updateWallet(Wallet wallet) throws FinanceException {
        final String SEPARATOR = System.lineSeparator();
        File f = new File(FILE_PATH + "/" + wallet.userName + ".txt");
        FileWriter fw;
        try {
            fw = new FileWriter(f, false);
            String encodedPassword = Base64.getEncoder().encodeToString(wallet.passWord.getBytes());
            fw.write("password:" + encodedPassword + SEPARATOR);
            String currencyName = wallet.getDefaultCurrency().getAbbrName();
            fw.write("defaultCurrency:" + currencyName + SEPARATOR);
            fw.write("totalBalance:" + wallet.totalBalance + SEPARATOR);
            for (Deposit deposit : wallet.getDeposits()) {
                currencyName = deposit.getCurrency().getAbbrName();
                fw.write(currencyName + ":" + deposit.getBalance() + SEPARATOR);
            }
        fw.close();
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.WALLET_FILE_OCCUPIED_EXCEPTION);
        }
    }

    public static Wallet getWallet(String userName) throws FinanceException {
        File f = new File(FILE_PATH + "/" + userName + ".txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new FinanceException(ExceptionCollection.WALLET_FILE_NOT_FOUND_EXCEPTION);
        }
        String password = scan.nextLine().split(":")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        String currencyName = scan.nextLine().split(":")[1];
        CurrencyStructure defaultCurrency = CurrencyList.findCurrencyByAbbrName(currencyName);
        double totalBalance = Double.parseDouble(scan.nextLine().split(":")[1]);
        List<Deposit> depositList = new ArrayList<>();
        while (scan.hasNext()) {
            String[] splits = scan.nextLine().split(":");
            currencyName = splits[0];
            CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(currencyName);
            double balance = MoneyCommand.parseAmount(splits[1]);
            Deposit deposit = new Deposit(currency, balance);
            depositList.add(deposit);
        }
        scan.close();
        return new Wallet(userName, decodedPassword, defaultCurrency, totalBalance, depositList);
    }

    public static void deleteWallet(Wallet wallet) throws FinanceException {
        Path path = Paths.get(FILE_PATH.toString(), wallet.getUserName() + ".txt");
        try {
            Files.delete(path);
        } catch (NoSuchFileException e) {
            throw new FinanceException(ExceptionCollection.WALLET_FILE_NOT_FOUND_EXCEPTION);
        } catch (FileSystemException e) {
            throw new FinanceException(ExceptionCollection.WALLET_FILE_OCCUPIED_EXCEPTION);
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.WALLET_FILE_DELETION_EXCEPTION);
        }


    }
}
