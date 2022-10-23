package computercomponentchooser.components;

/**
 * Represents the case of the computer build.
 */
public class Case extends Component {
    /**
     * The size of the case.
     */
    protected String formFactor;

    /**
     * Additional slots for the case to use additional features.
     */
    protected String expansionSlots;

    /**
     * Initializes a new case.
     *
     * @param name the name of the case
     * @param price the price of the case
     * @param power the power consumption of the case
     * @param formFactor the size of the case
     * @param expansionSlots the additional slots for the case to use additional features
     */
    public Case(String name, String price, String power, String formFactor, String expansionSlots) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.formFactor = formFactor;
        this.expansionSlots = expansionSlots;
    }

    /**
     * Gets the size of the case.
     *
     * @return the size of the case
     */
    public String getFormFactor() {
        return formFactor;
    }

    /**
     * Sets the size of the case.
     *
     * @param formFactor the size of the case
     */
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * Gets the additional slots for the case to use additional features.
     *
     * @return the additional slots for the case to use additional features
     */
    public String getExpansionSlots() {
        return expansionSlots;
    }

    /**
     * Sets the additional slots for the case to use additional features.
     *
     * @param expansionSlots the additional slots for the case to use additional features
     */
    public void setExpansionSlots(String expansionSlots) {
        this.expansionSlots = expansionSlots;
    }

    /**
     * Creates a string representation of all the information of the case.
     * Uses this method to display the information of the case.
     *
     * @return a string representation of all the information of the case
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s] [%s]", formFactor, expansionSlots);
    }

    /**
     * Creates a string representation of all the information of the case.
     * Uses this method to save the information of the case to a csv file.
     *
     * @return a string representation of all the information of the case
     */
    public String toCsv() {
        return super.toCsv() + "," + formFactor + "," + expansionSlots;
    }

    /**
     * Creates a string representation of all the information of the case.
     * Uses this method to save the information of the case to a text file.
     *
     * @return a string representation of all the information of the case
     */
    public String saveAsString() {
        return super.saveAsString() + "/" + formFactor + "/" + expansionSlots;
    }

    /**
     * Gets all the information of the case.
     *
     * @return all the information of the case
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nForm Factor: %s\nExpansion Slots: %s", formFactor,
                expansionSlots);
    }

    /**
     * Gets the type of the component.
     *
     * @return the type of the component
     */
    @Override
    public String getType() {
        return "case";
    }
}
