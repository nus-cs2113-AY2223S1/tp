package seedu.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jetbrains.annotations.NotNull;

import seedu.common.CommonData;
import seedu.exception.InvalidFormatException;
import seedu.parser.search.Sentence;
import seedu.parser.search.Word;
import seedu.ui.Ui;

/**
 * Carpark class that represents a carpark and all the information stored in it.
 */
public class Carpark implements Comparable<Carpark> {
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
        return String.format("CarparkID @|yellow,bold %s|@ at %s\n@|faint -->|@ @|yellow %s|@ "
                + "available lots total",
            carparkId,
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
            + "Last Updated: " + lastUpdated.format(CommonData.DATE_TIME_FORMATTER) + "\n"
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

    public String getSaveString() {
        String saveStringFormat = "%s || %s || %s || %s || %s || %s || %s || %s || %s \n";
        String allAvailableLotsString = String.format("%s %s %s", allAvailableLots.get(LotType.CAR),
                allAvailableLots.get(LotType.MOTORCYCLE), allAvailableLots.get(LotType.HEAVY_VEHICLE));
        return String.format(saveStringFormat, carparkId, area, development, location, availableLots,
                allAvailableLotsString, isFavourited, agency, lastUpdated.format(CommonData.DATE_TIME_FORMATTER));
    }

    @JsonProperty("CarParkID")
    public String getCarparkId() {
        return carparkId;
    }

    @JsonProperty("CarParkID")
    public void setCarparkId(String carparkId) throws InvalidFormatException {
        exceptIfBlank(carparkId);
        validateFormat(carparkId);
        this.carparkId = carparkId;
    }

    private void validateFormat(String carparkId) throws InvalidFormatException {
        Pattern carparkIdPattern = Pattern.compile("^[a-zA-Z]*[0-9]*[a-zA-Z]*$|^[0-9]$");
        Matcher carparkIdMatcher = carparkIdPattern.matcher(carparkId);
        if (!carparkIdMatcher.matches()) {
            throw new InvalidFormatException("Invalid Carpark ID format!");
        }
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
    public void setDevelopment(String development) throws InvalidFormatException {
        exceptIfBlank(development);
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
    public void setLotType(String lotType) throws InvalidFormatException {
        this.lotType = chooseLotType(lotType);
        allAvailableLots.put(this.lotType, this.availableLots);
    }

    private LotType chooseLotType(String lotTypeString) throws InvalidFormatException {
        if (lotTypeString.equals("H")) {
            return LotType.HEAVY_VEHICLE;
        } else if (lotTypeString.equals("Y")) {
            return LotType.MOTORCYCLE;
        } else if (lotTypeString.equals("C")) {
            return LotType.CAR;
        } else {
            throw new InvalidFormatException("Lot type not recognised!");
        }
    }

    @JsonProperty("AvailableLots")
    public int getAvailableLots() {
        return availableLots;
    }

    @JsonProperty("AvailableLots")
    public void setAvailableLots(String availableLots) throws InvalidFormatException {
        exceptIfBlank(availableLots);
        int parsedValue;

        try {
            parsedValue = Integer.parseInt(availableLots);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number format!");
        }

        validateAvailableLots(parsedValue, availableLots);
        this.availableLots = parsedValue;
        lastUpdated = LocalDateTime.now();
    }

    private void validateAvailableLots(int value, String compareString) throws InvalidFormatException {
        if (!Integer.toString(value).equals(compareString)) {
            throw new InvalidFormatException("Invalid number format! Available lots cannot be a float.");
        }
        if (value < 0) {
            throw new InvalidFormatException("Invalid number format! Number cannot be negative!");
        }
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

    public void setLastUpdated(String dateTimeString) throws InvalidFormatException {
        try {
            lastUpdated = LocalDateTime.parse(dateTimeString, CommonData.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid last updated string!");
        }
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

    public void setAllAvailableLots(String params) throws InvalidFormatException {
        int carLots;
        int motorCycleLots;
        int heavyVehicleLots;
        String[] lots = params.split(" ");

        try {
            carLots = Integer.parseInt(lots[0]);
            motorCycleLots = Integer.parseInt(lots[1]);
            heavyVehicleLots = Integer.parseInt(lots[2]);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number format!");
        }
        validateLotNumbers(carLots, motorCycleLots, heavyVehicleLots, lots);
        validateNonNegative(lots);

        addCarparkLotType(LotType.CAR, Integer.parseInt(lots[0]));
        addCarparkLotType(LotType.MOTORCYCLE, Integer.parseInt(lots[1]));
        addCarparkLotType(LotType.HEAVY_VEHICLE, Integer.parseInt(lots[2]));
        updateAvailableLotsTotal();
    }

    public void setAllAvailableLots(HashMap<LotType, Integer> newAvailableLots) {
        allAvailableLots = newAvailableLots;
        updateAvailableLotsTotal();
    }

    private void validateNonNegative(String[] lots) throws InvalidFormatException {
        for (String lot : lots) {
            if (Integer.parseInt(lot) < 0) {
                throw new InvalidFormatException("No negative numbers allowed!");
            }
        }
    }

    private static void validateLotNumbers(int carLots, int motorCycleLots, int heavyVehicleLots, String[] lots)
            throws InvalidFormatException {
        if (!Integer.toString(carLots).equals(lots[0]) && !Integer.toString(motorCycleLots).equals(lots[1])
                && !Integer.toString(heavyVehicleLots).equals(lots[2])) {
            throw new InvalidFormatException("Invalid number format!");
        }
    }

    public void setFavourite(boolean setFavourite) {
        isFavourited = setFavourite;
    }


    /**
     * Static method to generate a {@link CarparkList} object from a save string.
     * @param saveString string to be parsed
     * @return generated object.
     */
    public static Carpark parseCarpark(String saveString) throws InvalidFormatException {
        validateNumberOfParams(saveString);
        String[] params = removeWhitespaces(saveString.split("\\|\\|"));
        Carpark parsedCarpark = new Carpark();
        parsedCarpark.setCarparkId(params[0]);
        parsedCarpark.setArea(params[1]);
        parsedCarpark.setDevelopment(params[2]);
        parsedCarpark.setLocation(params[3]);
        parsedCarpark.setAvailableLots(params[4]);
        parsedCarpark.setAllAvailableLots(params[5]);
        // isFavourited should always be false, then set to true later when loading in favourites
        parsedCarpark.setFavourited(false);
        parsedCarpark.setAgency(params[7]);
        parsedCarpark.setLastUpdated(params[8]);
        return parsedCarpark;
    }

    private static void validateNumberOfParams(String saveString) throws InvalidFormatException {
        Pattern delimiterPattern = Pattern.compile("\\|\\|");
        Matcher patternMatcher = delimiterPattern.matcher(saveString);

        int count = 0;
        while (patternMatcher.find()) {
            count++;
        }
        if (count != 8) {
            throw new InvalidFormatException("Invalid format in save string!");
        }
    }

    public void updateTime() {
        lastUpdated = LocalDateTime.now();
    }


    private static String[] removeWhitespaces(String[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }


    public HashMap<LotType, Integer> getAllAvailableLots() {
        return allAvailableLots;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NotNull Carpark o) {
        // It is known that the CarparkId format is letters + numbers.
        // where either field can be empty.
        String c1 = carparkId;
        String c2 = o.getCarparkId();
        String alpha1 = getAlphabet(c1);
        String alpha2 = getAlphabet(c2);
        int num1 = getNumbers(c1);
        int num2 = getNumbers(c2);

        // If the alphabet component is equal, sort by numbers. Else, sort by the alphabets lexicographically.
        if (alpha2.equals(alpha1)) {
            if (num1 < num2) {
                return -1;
            } else if (num1 == num2) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return c1.compareTo(c2);
        }
    }

    /**
     * Gets the alphabet component of a carpark ID.
     * @param string input string
     * @return String containing alphabet component.
     */
    private String getAlphabet(String string) {
        return string.replaceAll("[0-9]", "");
    }

    /**
     * Gets the numerical component of a carpark ID.
     *
     * @param string input string
     * @return String containing alphabet component.
     */
    private int getNumbers(String string) {
        try {
            return Integer.parseInt(string.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void exceptIfBlank(String input) throws InvalidFormatException {
        if (input.isBlank()) {
            throw new InvalidFormatException("A field is empty! This should not be.");
        }
    }
}
