package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Currency {
    public static List<List<String>> getListOfAllCurrencies() throws IOException {

        List<List<String>> currencies = new ArrayList<>();
        Path path = Paths.get("src","main","data");

        //creates the directory
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            currencies = readInCurrencies(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found for currencies, you messed up buddy");
        }

        return currencies;
    }

    public static List<List<String>> readInCurrencies(Path path) throws IOException {
        List<List<String>> existingUserNames = new ArrayList<>();
        File f = new File(path + "/currencies.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line = s.nextLine();
            List<String> items = Arrays.asList(line.split(","));
            existingUserNames.add(items);
        }

        return existingUserNames;
    }
}
