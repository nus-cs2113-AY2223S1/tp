package seedu.duke;

/**
 * Represents a property.
 */
public class Property {
    public static final String LANDLORD_LABEL = "  Landlord: ";
    public static final String ADDRESS_LABEL = "  Address: ";
    public static final String PRICE_SGD_LABEL = "  Renting Price: SGD";
    public static final String PER_MONTH_LABEL = "/month";
    public static final String UNIT_TYPE_LABEL = "  Unit Type: ";
    private String landlordName;
    private String propertyAddress;
    private String rentingPrice;
    private String unitType;

    public Property(String landlordName, String propertyAddress, String rentingPrice, String unitType) {
        this.landlordName = landlordName;
        this.propertyAddress = propertyAddress;
        this.rentingPrice = rentingPrice;
        this.unitType = unitType;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public String getRentingPrice() {
        return rentingPrice;
    }

    public String getUnitType() {
        return unitType;
    }

    public String toString() {
        String propertyDetails = LANDLORD_LABEL + landlordName + System.lineSeparator()
                + ADDRESS_LABEL + propertyAddress + System.lineSeparator()
                + PRICE_SGD_LABEL + rentingPrice + PER_MONTH_LABEL + System.lineSeparator()
                + UNIT_TYPE_LABEL + unitType + System.lineSeparator();
        return propertyDetails.trim();
    }
}
