package seedu.duke;

/**
 * Represents a property.
 */
public class Property {
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
        String propertyDetails = "  Landlord: " + landlordName + System.lineSeparator()
                + "  Address: " + propertyAddress + System.lineSeparator()
                + "  Renting Price: SGD" + rentingPrice + "/month" + System.lineSeparator()
                + "  Unit Type: " + unitType + System.lineSeparator();
        return propertyDetails.trim();
    }
}
