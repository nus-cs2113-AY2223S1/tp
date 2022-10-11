package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import seedu.duke.FinanceException.exceptionCollection;

public class WalletFile {
    final static Path FILE_PATH = Paths.get("src","main","data");
    final static String PASSWORD = "password";
    final static String BALANCE = "balance";

    public static void createNewWallet(Wallet newWallet) throws IOException {
        Path filePath = Paths.get("src","main","data");
        File f = new File(filePath + "/" + newWallet.userName + ".txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        fw.write("password | " + newWallet.passWord);
        fw.write(System.lineSeparator());
        fw.write("balance | " + newWallet.balance);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static Wallet getWallet(String username) throws FinanceException {
        Wallet wallet;
        try {
            wallet = assembleWalletFromFile(username);
        } catch (FileNotFoundException e) {
            throw new FinanceException(exceptionCollection.FILE_NOT_FOUND);
        }
        return wallet;
    }

    private static Wallet assembleWalletFromFile(String username) throws FileNotFoundException {
        String password = "";
        float balance = 0;
        File file = new File(FILE_PATH + "/" + username + ".txt");
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String fileLine = fileScanner.nextLine();
            if (fileLine.trim().isEmpty())
                continue;
            String[] splits = fileLine.split(" \\| ");
            switch (splits[0]) {
            case PASSWORD:
                password = splits[1];
                break;
            case BALANCE:
                balance = Float.parseFloat(splits[1]);
                break;
            default:
                break;
            }
        }
        Wallet wallet = new Wallet(username, password, balance);
        return wallet;
    }
}
