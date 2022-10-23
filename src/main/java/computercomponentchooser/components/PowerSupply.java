package computercomponentchooser.components;

/**
 * Represents the power supply(PSU) in the computer build.
 */
public class PowerSupply extends Component {

    /**
     * Initializes a new power supply.
     *
     * @param name the name of the power supply
     * @param price the price of the power supply
     * @param power the total power supplied to the computer
     */
    public PowerSupply(String name, String price, String power) {
        this.name = name;
        this.price = price;
        this.power = power;
    }

    /**
     * Creates a string representation of all the information about the PSU.
     * Uses this method to display the information about the PSU.
     *
     * @return the string representation of all the information about the PSU
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Creates a string representation of all the information about the PSU.
     * Uses this method to save the information about the PSU to a csv file.
     *
     * @return the string representation of all the information about the PSU
     */
    public String toCsv() {
        return super.toCsv();
    }

    /**
     * Creates a string representation of all the information about the PSU.
     * Uses this method to save the information about the PSU to a text file.
     *
     * @return the string representation of all the information about the PSU
     */
    public String saveAsString() {
        return super.saveAsString();
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "powersupply";
    }
}
