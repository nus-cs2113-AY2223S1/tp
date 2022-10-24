package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

/**
 * Represents the CPU in the computer build.
 */
public class Cpu extends Component {

    /**
     * The socket of the CPU.
     */
    protected String socket;

    /**
     * Clock speed of the CPU in GHz.
     */
    protected String clock;

    /**
     * Initializes a new CPU.
     *
     * @param name the name of the CPU
     * @param price the price of the CPU
     * @param power the power consumption of the CPU
     * @param socket the socket of the CPU
     * @param clock the clock speed of the CPU
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public Cpu(String name, String price, String power, String socket, String clock) throws NegativeNumberException,
            NumberFormatException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        double clockDouble = Double.parseDouble(clock);
        if (priceDouble < 0 || powerInt < 0 || clockDouble < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.price = price;
        this.power = power;
        this.socket = socket;
        this.clock = clock;
    }

    /**
     * Gets the power of the CPU.
     *
     * @return the power of the CPU
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Gets the clock speed of the CPU in GHz.
     *
     * @return the clock speed of the CPU in GHz
     */
    public String getClock() {
        return clock;
    }

    /**
     * Sets the clock speed of the CPU in GHz.
     *
     * @param clock the clock speed of the CPU in GHz
     */
    public void setClock(String clock) {
        this.clock = clock;
    }

    /**
     * Creates a string representation of all the information of the CPU.
     * Uses this method to display the information of the CPU.
     *
     * @return a string representation of all the information of the CPU
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s] [%s GHz]", socket, clock);
    }

    /**
     * Creates a string representation of all the information of the CPU.
     * Uses this method to save the information of the CPU to a csv file.
     *
     * @return a string representation of all the information of the CPU
     */
    public String toCsv() {
        return super.toCsv() + "," + socket + "," + clock;
    }

    /**
     * Creates a string representation of all the information of the CPU.
     * Uses this method to save the information of the CPU to a text file.
     *
     * @return a string representation of all the information of the CPU
     */
    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + socket + "/" + clock;
    }

    /**
     * Gets all the information of the CPU.
     *
     * @return all the information of the CPU
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nClock: %s GHz", socket, clock);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "cpu";
    }
}

