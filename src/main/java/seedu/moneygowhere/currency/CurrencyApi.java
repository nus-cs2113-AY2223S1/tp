package seedu.moneygowhere.currency;

import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.currency.CurrencyManager;
import seedu.moneygowhere.data.currency.Currency;
import seedu.moneygowhere.exceptions.CurrencyLoadDataInputException;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyApi {
    public static void getCurrencyApi(CurrencyManager currencyManager) {
        getJson();
        loadFromFile(currencyManager);
    }

    public static File initialiseFile() {
        File directory = new File(Configurations.DIRECTORY_PATH);
        directory.mkdir();
        String newFilePath = new File(Configurations.FILE_PATH_CURRENCIES).getAbsolutePath();
        return new File(newFilePath);
    }

    private static Currency createCurrency(String textFromFile) throws CurrencyLoadDataInputException {
        String[] splitInputs = textFromFile.split(":");
        if (splitInputs.length != 2) {
            throw new CurrencyLoadDataInputException();
        }
        String currencyCode = splitInputs[0];
        Float rate = Float.parseFloat(splitInputs[1]);
        return new Currency(currencyCode, rate);
    }

    public static void loadFromFile(CurrencyManager currencyManager) {
        Currency loadCurrency;
        int fileIndex = 1;
        try {
            File f = initialiseFile();
            Scanner s = new Scanner(f);

            String textFromFile;
            while (s.hasNext()) {
                textFromFile = s.nextLine();
                loadCurrency = createCurrency(textFromFile);
                currencyManager.addCurrency(loadCurrency);
                ++fileIndex;
            }
            System.out.println(Messages.CURRENCY_API_LOAD_SUCCESS);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.CURRENCY_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (CurrencyLoadDataInputException | NumberFormatException e) {
            System.out.println(Messages.CURRENCY_STORAGE_ERROR_IN_LOAD_FILE + fileIndex);
        }
    }

    private static void writeToFile(ArrayList<String> textToWrite) throws IOException {
        FileWriter fw = new FileWriter(new File(Configurations.FILE_PATH_CURRENCIES).getAbsolutePath(), false);
        for (String task : textToWrite) {
            fw.write(task + "\n");
        }
        fw.close();
    }

    private static void getJson() {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(Configurations.CURRENCY_API_URL);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            ArrayList<String> sb = new ArrayList<>();
            String line;
            for (int i = 0; i < Configurations.IGNORED_LINES; i++) {
                line = br.readLine();
            }
            for (int j = 0; j < Configurations.NUMBER_OF_CURRENCIES; j++) {
                line = br.readLine();
                line = line.replace(",", "");
                line = line.replace('"',' ');
                line = line.replace(" ", "");
                sb.add(line.toUpperCase());
            }
            br.close();
            con.disconnect();
            writeToFile(sb);
        } catch (IOException exception) {
            System.out.println(Messages.CURRENCY_API_CONNECTION_FAILURE);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
