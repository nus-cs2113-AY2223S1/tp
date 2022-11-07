package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.command.add.CommandAddProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandAddPropertyTest {
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = SPACE + SPACE;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PROPERTY_LANDLORD_LABEL = "Landlord:";
    private static final String PROPERTY_ADDRESS_LABEL = "Address:";
    private static final String PROPERTY_RENTING_PRICE_LABEL = "Renting Price: SGD";
    private static final String PROPERTY_PER_MONTH_LABEL = "/month";
    private static final String PROPERTY_UNIT_TYPE_LABEL = "Unit Type:";

    //Test Case
    private static final String LANDLORD_NAME = "Giorno Giovanna";
    private static final String PROPERTY_ADDRESS = "60 Aria Street, Singapore 602580";
    private static final String PROPERTY_RENTING_PRICE = "1000";
    private static final String PROPERTY_UNIT_TYPE = "Landed Property";
    private static final ArrayList<String> PROPERTY_DETAILS = new ArrayList<>(
            List.of(LANDLORD_NAME, PROPERTY_ADDRESS, PROPERTY_RENTING_PRICE, PROPERTY_UNIT_TYPE)
    );
    private static final String PROPERTY_SUMMARY = PROPERTY_LANDLORD_LABEL + SPACE + LANDLORD_NAME + LINE_SEPARATOR
            + DOUBLE_SPACE + PROPERTY_ADDRESS_LABEL + SPACE + PROPERTY_ADDRESS + LINE_SEPARATOR
            + DOUBLE_SPACE + PROPERTY_RENTING_PRICE_LABEL + PROPERTY_RENTING_PRICE + PROPERTY_PER_MONTH_LABEL
            + LINE_SEPARATOR + DOUBLE_SPACE + PROPERTY_UNIT_TYPE_LABEL + SPACE + PROPERTY_UNIT_TYPE;

    //Initialization for Testing
    public Ui ui = new Ui();
    public PropertyList propertyList = new PropertyList();
    public ClientList clientList = new ClientList();
    public PairingList pairingList = new PairingList();
    public Storage storage = new Storage(clientList, propertyList, pairingList);

    public static final int OFFSET_UNIT_VALUE = 1;

    @Test
    public void execute_validPropertyDetails_matchingPropertyDetails() {
        int propertyListSizeByCounting = propertyList.getCurrentListSize();
        new CommandAddProperty(PROPERTY_DETAILS).execute(ui, storage, propertyList, clientList, pairingList);

        assertEquals(propertyList.getCurrentListSize(), ++propertyListSizeByCounting);
        int addedPropertyIndex = propertyList.getCurrentListSize() - OFFSET_UNIT_VALUE;
        assertEquals(LANDLORD_NAME, propertyList.getPropertyList().get(addedPropertyIndex).getLandlordName());
        assertEquals(PROPERTY_ADDRESS, propertyList.getPropertyList().get(addedPropertyIndex).getPropertyAddress());
        assertEquals(PROPERTY_RENTING_PRICE, propertyList.getPropertyList().get(addedPropertyIndex).getRentingPrice());
        assertEquals(PROPERTY_UNIT_TYPE, propertyList.getPropertyList().get(addedPropertyIndex).getUnitType());
        assertEquals(PROPERTY_SUMMARY, propertyList.getPropertyList().get(addedPropertyIndex).toString());
    }
}