package seedu.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import seedu.common.CommonData;
import seedu.parser.search.Sentence;
import seedu.parser.search.Word;
import seedu.ui.Ui;

/**
 * Carpark class that represents a carpark and all the information stored in it.
 */
public class Carpark {
    @JsonIgnore
    private HashMap<LotType, Integer> allAvailableLots = new HashMap<>() { {
            put(LotType.CAR, 0);
            put(LotType.MOTORCYCLE, 0);
            put(LotType.HEAVY_VEHICLE, 0);
        }};
    @JsonIgnore
    private boolean isFavourited = false;
    private String carparkId;
    private String area;
    private String development;
    private String location;
    private int availableLots;
    private LotType lotType;
    private String agency;
    @JsonIgnore
    private Sentence developmentSentence;
    @JsonIgnore
    private LocalDateTime lastUpdated;

    /**
     * Used to print the identifier for a carpark.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("CarparkID %s at %s: %s lots available", carparkId, development, availableLots);
    }

    /**
     * Resets the bolding on every word in the {@link Carpark#developmentSentence} instance.
     */
    public void resetBold() {
        for (Word word : developmentSentence.getWords()) {
            word.makeBold(false);
        }
    }

    public String getListViewString() {
        return String.format("CarparkID %s at %s\n   %s available lots total", carparkId,
            developmentSentence.toString(), availableLots);
    }

    public String getDetailViewString() {
        String formatString = "Carpark code: %s\n"
            + "Favourited: %s\n"
            + "Number of available lots (total): %s";
        String favourited = isFavourited ? "Yes" : "No";
        String bufferString = Ui.getSeparatorString() + "\n"
            + developmentSentence.toString() + "\n"
            + Ui.getSeparatorString() + "\n"
            + String.format(formatString, carparkId, favourited, availableLots)
            + getLotBreakdownString() + "\n"
            + Ui.getSeparatorString();
        return bufferString;
    }

    private String getLotBreakdownString() {
        StringBuilder bufferString = new StringBuilder();
        List<LotType> lotTypes = new ArrayList<>();
        lotTypes.add(LotType.CAR);
        lotTypes.add(LotType.MOTORCYCLE);
        lotTypes.add(LotType.HEAVY_VEHICLE);
        for (LotType lotType : lotTypes) {
            bufferString.append("\n").append(String.format("  %s: %s lots", CommonData.LOT_TYPE_TO_STRING.get(lotType),
                allAvailableLots.getOrDefault(lotType, 0)));
        }
        return bufferString.toString();
    }

    @JsonProperty("CarParkID")
    public String getCarparkId() {
        return carparkId;
    }

    @JsonProperty("CarParkID")
    public void setCarparkId(String carparkId) {
        this.carparkId = carparkId;
    }

    @JsonProperty("Area")
    public String getArea() {
        return area;
    }

    @JsonProperty("Area")
    public void setArea(String area) {
        this.area = area;
    }

    @JsonProperty("Development")
    public String getDevelopment() {
        return development;
    }

    @JsonProperty("Development")
    public void setDevelopment(String development) {
        this.development = development;
        developmentSentence = new Sentence(development);
    }

    @JsonProperty("Location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("LotType")
    public LotType getLotType() {
        return lotType;
    }

    @JsonProperty("LotType")
    public void setLotType(String lotType) {
        this.lotType = chooseLotType(lotType);
        allAvailableLots.put(this.lotType, this.availableLots);
    }

    private LotType chooseLotType(String lotTypeString) {
        if (lotTypeString.equals("H")) {
            return LotType.HEAVY_VEHICLE;
        } else if (lotTypeString.equals("Y")) {
            return LotType.MOTORCYCLE;
        } else {
            return LotType.CAR;
        }
    }

    @JsonProperty("AvailableLots")
    public int getAvailableLots() {
        return availableLots;
    }

    @JsonProperty("AvailableLots")
    public void setAvailableLots(String availableLots) {
        try {
            this.availableLots = Integer.parseInt(availableLots);
        } catch (NumberFormatException e) {
            this.availableLots = 0;
        }
        lastUpdated = LocalDateTime.now();
    }

    @JsonProperty("Agency")
    public String getAgency() {
        return agency;
    }

    @JsonProperty("Agency")
    public void setAgency(String agency) {
        this.agency = agency;
    }

    private void setFavourited(boolean bool) {
        isFavourited = bool;
    }

    public void setLastUpdated(String dateTimeString) {
        lastUpdated = LocalDateTime.parse(dateTimeString, CommonData.DATE_TIME_FORMATTER);
    }

    /**
     * Add a carpark lot type to the {@link Carpark#allAvailableLots} Hashmap object,
     * where the key is a {@link LotType} and the value is the number of lots.
     *
     * @param carpark Carpark object to be added.
     */
    public void addCarparkLotType(Carpark carpark) {
        allAvailableLots.put(carpark.getLotType(), carpark.getAvailableLots());
        updateAvailableLotsTotal();
    }

    /**
     * Add a carpark lot type to the {@link Carpark#allAvailableLots} Hashmap object,
     * where the key is a {@link LotType} and the value is the number of lots.
     *
     * @param lotType Type of lot
     * @param numberOfLots Number of lots
     */
    public void addCarparkLotType(LotType lotType, int numberOfLots) {
        allAvailableLots.put(lotType, numberOfLots);
        updateAvailableLotsTotal();
    }

    private void updateAvailableLotsTotal() {
        int sum = 0;
        for (int lotNumber : allAvailableLots.values()) {
            sum += lotNumber;
        }
        availableLots = sum;
    }

    public Sentence getDevelopmentSentence() {
        return developmentSentence;
    }

    public String getSaveString() {
        String saveStringFormat = "%s || %s || %s || %s || %s || %s || %s || %s || %s \n";
        String allAvailableLotsString = String.format("%s %s %s", allAvailableLots.get(LotType.CAR),
                allAvailableLots.get(LotType.MOTORCYCLE), allAvailableLots.get(LotType.HEAVY_VEHICLE));
        return String.format(saveStringFormat, carparkId, area, development, location, availableLots,
                allAvailableLotsString, isFavourited, agency, lastUpdated.format(CommonData.DATE_TIME_FORMATTER));
    }

    /**
     * Static method to generate a {@link CarparkList} object from a save string.
     * @param saveString string to be parsed
     * @return generated object.
     */
    public static Carpark parseCarpark(String saveString) {
        String[] params = removeWhitespaces(saveString.split("\\|\\|"));
        Carpark parsedCarpark = new Carpark();
        parsedCarpark.setCarparkId(params[0]);
        parsedCarpark.setArea(params[1]);
        parsedCarpark.setDevelopment(params[2]);
        parsedCarpark.setLocation(params[3]);
        parsedCarpark.setAvailableLots(params[4]);
        parsedCarpark.setAllAvailableLots(params[5]);
        parsedCarpark.setFavourited(Boolean.parseBoolean(params[6]));
        parsedCarpark.setAgency(params[7]);
        parsedCarpark.setLastUpdated(params[8]);
        return parsedCarpark;
    }

    public void updateTime() {
        lastUpdated = LocalDateTime.now();
    }

    public void setAllAvailableLots(String params) {
        String[] lots = params.split(" ");
        addCarparkLotType(LotType.CAR, Integer.parseInt(lots[0]));
        addCarparkLotType(LotType.MOTORCYCLE, Integer.parseInt(lots[1]));
        addCarparkLotType(LotType.HEAVY_VEHICLE, Integer.parseInt(lots[2]));
        updateAvailableLotsTotal();
    }

    public void setAllAvailableLots(HashMap<LotType, Integer> newAvailableLots) {
        allAvailableLots = newAvailableLots;
        updateAvailableLotsTotal();
    }

    private static String[] removeWhitespaces(String[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    public void setFavourite(boolean setFavourite) {
        isFavourited = setFavourite;
    }

    public HashMap<LotType, Integer> getAllAvailableLots() {
        return allAvailableLots;
    }
}
