package seedu.duke.exception.storageexception;

public class InvalidPairingPropertyException extends StorageException {
    private String landlordName;
    private String address;
    private String rentalPrice;
    private String unitType;
    private static final String NAME_LABEL = "Landlord Name: ";
    private static final String ADDRESS_LABEL = "Address: ";
    private static final String RENTAL_LABEL = "Rental Rate: ";
    private static final String RENTAL_PER_MONTH_LABEL = "SGD/month";
    private static final String UNIT_LABEL = "Unit Type: ";
    private static final String ERROR_ENTRY_HEADER = "The following won't be added as it does not exist in the "
        + "property list:";
    private static final String NEXT_LINE = "\n";

    public InvalidPairingPropertyException(String name, String contact, String rentalPrice, String unitType) {
        this.landlordName = name;
        this.address = contact;
        this.rentalPrice = rentalPrice;
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return ERROR_ENTRY_HEADER + NEXT_LINE
                + NAME_LABEL + landlordName + NEXT_LINE
                + ADDRESS_LABEL + address + NEXT_LINE
                + RENTAL_LABEL + rentalPrice + RENTAL_PER_MONTH_LABEL + NEXT_LINE
                + UNIT_LABEL + unitType + NEXT_LINE;
    }
}
