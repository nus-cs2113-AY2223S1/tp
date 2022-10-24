package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

/**
 * Represents the memory in the computer build.
 */
public class Memory extends Component {

    /**
     * The speed of the memory in MHz.
     */
    protected String speed;

    /**
     * The size of the memory in GB.
     */
    protected String size;

    /**
     * Initializes a new memory.
     *
     * @param name the name of the memory
     * @param price the price of the memory
     * @param power the power consumption of the memory
     * @param size the size of the memory
     * @param speed the speed of the memory
     */
    public Memory(String name, String price, String power, String size, String speed) throws NegativeNumberException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        int sizeInt = Integer.parseInt(size);
        int speedInt = Integer.parseInt(speed);
        if (priceDouble < 0 || powerInt < 0 || sizeInt < 0 || speedInt < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.price = price;
        this.power = power;
        this.size = size;
        this.speed = speed;
    }

    /**
     * Gets the speed of the memory.
     *
     * @return the speed of the memory
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the memory.
     *
     * @param speed the speed of the memory
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * Gets the size of the memory.
     *
     * @return the size of the memory
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the memory.
     *
     * @param size the size of the memory
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Creates a string representation of all the information of the Memory.
     * Uses this method to display the information of the Memory.
     *
     * @return a string representation of all the information of the Memory
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s GB] [%s MHz]", size, speed);
    }

    /**
     * Creates a string representation of all the information of the Memory.
     * Uses this method to save the information of the Memory to a csv file.
     *
     * @return a string representation of all the information of the Memory
     */
    public String toCsv() {
        return super.toCsv() + "," + size + "," + speed;
    }

    /**
     * Creates a string representation of all the information of the Memory.
     * Uses this method to save the information of the Memory to a text file.
     *
     * @return a string representation of all the information of the Memory
     */
    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + size + "/" + speed;
    }

    /**
     * Gets all the information of the Memory.
     *
     * @return all the information of the Memory
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nSize: %s GB\nSpeed: %s MHz", size, speed);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "memory";
    }
}
