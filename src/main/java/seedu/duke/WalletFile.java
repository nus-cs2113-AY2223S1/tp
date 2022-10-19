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
import java.util.Base64;
import java.util.Scanner;

import seedu.duke.FinanceException.ExceptionCollection;

public class WalletFile {

    static final Path FILE_PATH = Paths.get("src","main","data");

    public static void createNewWallet(Wallet newWallet) throws IOException {
        File f = new File(FILE_PATH + "/" + newWallet.userName + ".txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        String encodedPassword = Base64.getEncoder().encodeToString(newWallet.passWord.getBytes());
        fw.write("password:" + encodedPassword);
        fw.write(System.lineSeparator());
        fw.write("balance:" + newWallet.balance);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static Wallet getWallet(String userName) throws FileNotFoundException {
        File f = new File(FILE_PATH + "/" + userName + ".txt");
        Scanner scan = new Scanner(f);
        String password = scan.nextLine().split(":")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        int balance = Integer.parseInt(scan.nextLine().split(":")[1]);
        scan.close();
        return new Wallet(userName, decodedPassword, balance);
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
