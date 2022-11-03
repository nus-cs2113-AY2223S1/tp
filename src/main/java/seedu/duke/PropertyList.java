package seedu.duke;

import java.util.ArrayList;

/**
 * Stores the list of properties.
 */
public class PropertyList {
    private static int currentListSize;
    private static ArrayList<Property> propertyList;

    public PropertyList() {
        propertyList = new ArrayList<>();
        currentListSize = 0;
    }

    public int getCurrentListSize() {
        return currentListSize;
    }

    public ArrayList<Property> getPropertyList() {
        return propertyList;
    }

    /**
     * Adds a property to property list and updates property list size.
     *
     * @param landlordName Name of landlord of property.
     * @param propertyAddress Address of property.
     * @param rentingPrice Renting price per month for property.
     * @param unitType Unit type of property.
     */
    public void addProperty(String landlordName, String propertyAddress, String rentingPrice, String unitType) {
        propertyList.add(new Property(landlordName, propertyAddress, rentingPrice, unitType));
        currentListSize++;
    }

    //@@author FeliciaBeatrice
    /**
     * Deletes a property from the property list and updates property list size.
     *
     * @param propertyIndex Index of property to be deleted.
     * @return Property deleted.
     */
    public Property deleteProperty(int propertyIndex) {
        Property deletedProperty = propertyList.get(propertyIndex);
        propertyList.remove(propertyIndex);
        currentListSize--;
        return deletedProperty;
    }
    //@@author
}


