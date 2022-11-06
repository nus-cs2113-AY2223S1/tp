package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;
import seedu.duke.newcurrency.PersonalCurrencyList;

public class CurrencyUpdater {
    final static String API_KEY_1 = "3bc24e00742f898872e5f0c1";
    final static String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY_1 + "/latest/USD";
    final static String REQUEST_SUCCESS = "success";
    static final Path FILE_PATH = Paths.get("src", "main", "data");
    static Map<String, Double> currencyRecords = new HashMap<String, Double>();
    static Date lastUpdateDate = new Date(0);
    static Date nextUpdateDate = new Date();
    static Boolean isGetValidRecords = false;

    public static void updateExchangeRate() {
        isGetValidRecords = false;
        Boolean isUpdateNeeded = readRecords();
        Boolean isUpdateSuccess = false;
        if (isUpdateNeeded) {
            try {
                isUpdateSuccess = updateFromUrl();
                writeRecords();
            } catch (FinanceException e) {
                e.handleException();
            }
        }
        if (isGetValidRecords) {
            CurrencyList.updateCurrencyList(currencyRecords);
            BasicUi.showCurrencyListUpdateSuccessInfo(isUpdateNeeded,isUpdateSuccess,lastUpdateDate,nextUpdateDate);
        } else {
            BasicUi.showCurrencyListUpdateFailureInfo();
        }
        
    }

    private static Boolean readRecords() {
        File f = new File(FILE_PATH + "/CurrencyRecords.txt");
        try {
            Scanner scan = new Scanner(f);
            Long lastUpdateMilliSeconds = Long.parseLong(scan.nextLine());
            Long nextUpdateMilliSeconds = Long.parseLong(scan.nextLine());
            lastUpdateDate = new Date(lastUpdateMilliSeconds);
            nextUpdateDate = new Date(nextUpdateMilliSeconds);
            while (scan.hasNextLine()) {
                String recordLine = scan.nextLine();
                String[] lineSplits = recordLine.split(":");
                String currencyAbbrName = lineSplits[0];
                Double exchangeRate = Double.parseDouble(lineSplits[1]);
                currencyRecords.put(currencyAbbrName, exchangeRate);
            }
            scan.close();
        } catch (Exception e) {
            return true;
        }
        Date currencyDate = new Date();
        if (currencyDate.after(nextUpdateDate)) {
            isGetValidRecords = true;
            return true;
        }
        isGetValidRecords = true;
        return false;
    }

    private static Boolean writeRecords() {
        File f = new File(FILE_PATH + "/CurrencyRecords.txt");
        FileWriter fw;
        final String SEPARATOR = System.lineSeparator();
        try {
            fw = new FileWriter(f, false);
            fw.write(Long.toString(lastUpdateDate.getTime()) + SEPARATOR);
            fw.write(Long.toString(nextUpdateDate.getTime()) + SEPARATOR);
            for (String currencyAbbrName : currencyRecords.keySet()) {
                Double exchangeRate = currencyRecords.get(currencyAbbrName);
                fw.write(currencyAbbrName + ":" + Double.toString(exchangeRate) + SEPARATOR);
            }  
            fw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static Boolean updateFromUrl() throws FinanceException {
        try {
            currencyRecords.clear();
            URL url = new URL(API_URL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();
            String requestResult = jsonObj.get("result").getAsString();
            if (requestResult.equals(REQUEST_SUCCESS)) {
                Long lastUpdateSeconds = jsonObj.get("time_last_update_unix").getAsLong();
                Long lastUpdateMilliSeconds = lastUpdateSeconds * 1000;
                lastUpdateDate = new Date(lastUpdateMilliSeconds);
                Long nextUpdateSeconds = jsonObj.get("time_next_update_unix").getAsLong();
                Long nextUpdateMilliSeconds = nextUpdateSeconds * 1000;
                nextUpdateDate = new Date(nextUpdateMilliSeconds);
                JsonObject rateObj = jsonObj.get("conversion_rates").getAsJsonObject();
                Set<Entry<String, JsonElement>> rateSet = rateObj.getAsJsonObject().entrySet();
                for (Entry<String, JsonElement> rateItem : rateSet)
                {
                    String currencyAbbrName = rateItem.getKey();
                    Double exchangeRate = Double.parseDouble(rateItem.getValue().getAsString());
                    currencyRecords.put(currencyAbbrName, exchangeRate);
                }
            }
            else {
                throw new FinanceException(ExceptionCollection.URL_REQUEST_EXCEPTION);
            }
        } catch (Exception e) {
            throw new FinanceException(ExceptionCollection.UPDATE_EXCHANGE_RATE_EXCEPTION);
        }
        isGetValidRecords = true;
        return true;
    }
}
