package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

/**
 * Represents the motherboard of the computer build.
 */
public class Motherboard extends Component {

    /**
     * The socket of the motherboard.
     */
    protected String socket;

    /**
     * The size of the motherboard.
     */
    protected String formFactor;

    /**
     * The number of RAM slots on the motherboard.
     */
    protected String memorySlots;

    /**
     * The number of PCI-E slots on the motherboard.
     */
    protected String gpuSlots;

    /**
     * Initializes a new motherboard.
     *
     * @param name the name of the motherboard
     * @param price the price of the motherboard
     * @param power the power consumption of the motherboard
     * @param socket the socket of the motherboard
     * @param formFactor the size of the motherboard
     * @param memorySlots the number of RAM slots on the motherboard
     * @param gpuSlots the number of PCI-E slots on the motherboard
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public Motherboard(String name, String price, String power, String socket, String formFactor, String memorySlots,
                       String gpuSlots) throws NegativeNumberException, NumberFormatException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        int memorySlotsInt = Integer.parseInt(memorySlots);
        int gpuSlotsInt = Integer.parseInt(gpuSlots);
        if (priceDouble < 0 || powerInt < 0 || memorySlotsInt < 0 || gpuSlotsInt < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.formFactor = formFactor;
        this.memorySlots = memorySlots;
        this.power = power;
        this.gpuSlots = gpuSlots;
    }

    /**
     * Gets the socket of the motherboard.
     *
     * @return the socket of the motherboard
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Sets the socket of the motherboard.
     *
     * @param socket the socket of the motherboard
     */
    public void setSocket(String socket) {
        this.socket = socket;
    }

    /**
     * Gets the size of the motherboard.
     *
     * @return the size of the motherboard
     */
    public String getFormFactor() {
        return formFactor;
    }

    /**
     * Sets the size of the motherboard.
     *
     * @param formFactor the size of the motherboard
     */
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * Gets the number of RAM slots on the motherboard.
     *
     * @return the number of RAM slots on the motherboard
     */
    public String getMemorySlots() {
        return memorySlots;
    }

    /**
     * Sets the number of RAM slots on the motherboard.
     *
     * @param memorySlots the number of RAM slots on the motherboard
     */
    public void setMemorySlots(String memorySlots) {
        this.memorySlots = memorySlots;
    }

    /**
     * Gets the number of PCI-E slots on the motherboard.
     *
     * @return the number of PCI-E slots on the motherboard
     */
    public String getGpuSlots() {
        return gpuSlots;
    }

    /**
     * Sets the number of PCI-E slots on the motherboard.
     *
     * @param gpuSlots the number of PCI-E slots on the motherboard
     */
    public void setGpuSlots(String gpuSlots) {
        this.gpuSlots = gpuSlots;
    }

    /**
     * Creates a string representation of all the information of the Motherboard.
     * Uses this method to display the information of the Motherboard.
     *
     * @return a string representation of all the information of the Motherboard
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s] [%s] [%s RAM slots] [%s GPU slots]", socket, formFactor,
                memorySlots, gpuSlots);
    }

    /**
     * Creates a string representation of all the information of the Motherboard.
     * Uses this method to save the information of the Motherboard to a csv file.
     *
     * @return a string representation of all the information of the Motherboard
     */
    public String toCsv() {
        return super.toCsv() + "," + socket + "," + formFactor + "," + memorySlots + "," + gpuSlots;
    }

    /**
     * Creates a string representation of all the information of the Motherboard.
     * Uses this method to save the information of the Motherboard to a text file.
     *
     * @return a string representation of all the information of the Motherboard
     */
    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + socket + "/" + formFactor + "/" + memorySlots + "/" + gpuSlots;
    }

    /**
     * Gets all the information of the Motherboard.
     *
     * @return all the information of the Motherboard
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nformFactor: %s\nRAM Slots: %s\nGPU Slots: %s", socket,
                formFactor, memorySlots, gpuSlots);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "motherboard";
    }
}
