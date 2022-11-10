package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

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
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public Other(String name, String price, String power) throws NegativeNumberException, NumberFormatException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        if (priceDouble < 0 || powerInt < 0) {
            throw new NegativeNumberException();
        }
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

