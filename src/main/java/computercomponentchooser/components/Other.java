package computercomponentchooser.components;

/**
 * Represents other computer parts such as mouse, keyboard etc.
 */
public class Other extends Component {

    /**
     * Initializes a new other component such as mouse, keyboard etc.
     *
     * @param name the name of the other component
     * @param price the price of the other component
     * @param power the power consumption of the other component
     */
    public Other(String name, String price, String power) {
        this.name = name;
        this.price = price;
        this.power = power;
    }

    /**
     * Creates a string representation of all the information of the Other computer part.
     * Uses this method to display the information of the Other computer part.
     *
     * @return a string representation of all the information of the Other computer part
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Creates a string representation of all the information of the Other computer part.
     * Uses this method to save the information of the Other computer part to a csv file.
     *
     * @return a string representation of all the information of the Other computer part
     */
    public String toCsv() {
        return super.toCsv();
    }

    /**
     * Creates a string representation of all the information of the Other computer part.
     * Uses this method to save the information of the Other computer part to a text file.
     *
     * @return a string representation of all the information of the Other computer part
     */
    @Override
    public String saveAsString() {
        return super.saveAsString();
    }

    /**
     * Gets all the information of the Other computer part.
     *
     * @return all the information of the Other computer part
     */
    public String getDetails() {
        return super.getDetails();
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "other";
    }
}

