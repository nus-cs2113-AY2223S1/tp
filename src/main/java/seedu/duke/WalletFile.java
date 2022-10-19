package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

public class WalletFile {

    public static void createNewWallet(Wallet newWallet) throws IOException {
        Path filePath = Paths.get("src","main","data");
        File f = new File(filePath + "/" + newWallet.userName + ".txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        String encodedPassword = Base64.getEncoder().encodeToString(newWallet.passWord.getBytes());
        fw.write("password:" + encodedPassword);
        fw.write(System.lineSeparator());
        fw.write("balance:" + newWallet.balance);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static Wallet getWallet(String userName) throws FileNotFoundException {
        Path filePath = Paths.get("src","main","data");
        File f = new File(filePath + "/" + userName + ".txt");
        Scanner scan = new Scanner(f);
        String password = scan.nextLine().split(":")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        int balance = Integer.parseInt(scan.nextLine().split(":")[1]);
        return new Wallet(userName, decodedPassword, balance);
    }
}
