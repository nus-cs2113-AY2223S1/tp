package seedu.common;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import seedu.data.LotType;

/**
 * Common shared data.
 */
public class CommonData {
    public static final String API_KEY_DEFAULT = "1B+7tBxzRNOtFbTxGcCiYA==";
    public static final String LTA_BASE_URL = "http://datamall2.mytransport.sg/ltaodataservice/CarParkAvailabilityv2";
    public static final String API_RESPONSE_HEADER = "{\"odata.metadata\":\""
            + "http://datamall2.mytransport.sg/ltaodataservice/$metadata#CarParkAvailability\",\"value\":[";
    public static final String API_RESPONSE_TAIL = "]}";
    public static final HashMap<LotType, String> LOT_TYPE_TO_STRING = new HashMap<>() { {
            put(LotType.CAR, "Cars");
            put(LotType.MOTORCYCLE, "Motorcycles");
            put(LotType.HEAVY_VEHICLE, "Heavy Vehicles");
        }};
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Command format
    public static final String AUTH_FORMAT = " `auth API_KEY` or `a API_KEY` \t: to authenticate your personal API key."
            + "\n`auth default` or `a default` \t: to authenticate using the default key provided by parKING."
            + "\n`auth status` or `a status` \t: to get the authentication status.";
    public static final String FAVOURITE_FORMAT =  " `favourite list` or `fav list` "
            + "\t: to get the list of favourited carparks.\n`favourite CARPARK_ID` or `fav CARPARK_ID` "
            + "\t: favourite carpark by its ID.";
    public static final String UNFAVOURITE_FORMAT = " `unfavourite CARPARK_ID` or `ufav CARPARK_ID` "
            + "\t: unfavourite carpark by its ID.";
    public static final String FIND_FORMAT = " `find CARPARK_ID` or `fin CARPARK_ID` "
            + "\t: Display information about the queried carpark.";
    public static final String FILTER_FORMAT = " `filter KEYWORD` or `fil KEYWORD` "
            + "\t: Find carparks based on its address.";
    public static final String UPDATE_FORMAT = " `update` or `u` \t: To fetch the latest data from LTA.";
    public static final String LIST_FORMAT = " `list` or `l` \t: List the carparks and its details.";
    public static final String EXIT_FORMAT = " `exit` or `e` \t: To quit parKING.";
    public static final String HELP_FORMAT = " `help` or `h` \t: To display all possible commands.";

}
