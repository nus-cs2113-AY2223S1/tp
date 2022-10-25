package seedu.common;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import seedu.data.LotType;

/**
 * Common shared data.
 */
public class CommonData {
    public static final String API_KEY_DEFAULT = "1B+7tBxzRNOtFbTxGcCiYA=";
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
}
