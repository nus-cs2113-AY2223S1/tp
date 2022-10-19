package seedu.data;

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
    private final HashMap<LotType, Integer> allAvailableLots = new HashMap<>();
    @JsonIgnore
    private final boolean isFavourited = false;
    private String carparkId;
    private String area;
    private String development;
    private String location;
    private int availableLots;
    private LotType lotType;
    private String agency;
    @JsonIgnore
    private Sentence developmentSentence;

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
            + Ui.getSeparatorString() + "\n";
        return bufferString;
    }

    public String getLotBreakdownString() {
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

    public String getCarparkId() {
        return carparkId;
    }

    @JsonProperty("CarParkID")
    public void setCarparkId(String carparkId) {
        this.carparkId = carparkId;
    }

    public String getArea() {
        return area;
    }

    @JsonProperty("Area")
    public void setArea(String area) {
        this.area = area;
    }

    public String getDevelopment() {
        return development;
    }

    @JsonProperty("Development")
    public void setDevelopment(String development) {
        this.development = development;
        developmentSentence = new Sentence(development);
    }

    public String getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(String location) {
        this.location = location;
    }

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
    }

    public String getAgency() {
        return agency;
    }

    @JsonProperty("Agency")
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Add a carpark lot type to the {@link Carpark#allAvailableLots} Hashmap object,
     * where the key is a {@link LotType} and the value is the number of lots.
     *
     * @param carpark Carpark object to be added.
     */
    public void addCarparkLotType(Carpark carpark) {
        allAvailableLots.put(carpark.getLotType(), carpark.getAvailableLots());
        availableLots += carpark.getAvailableLots();
    }


    public Sentence getDevelopmentSentence() {
        return developmentSentence;
    }
}
