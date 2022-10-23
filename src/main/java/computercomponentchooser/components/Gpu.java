package computercomponentchooser.components;

/**
 * Represents the GPU in the computer build.
 */
public class Gpu extends Component {

    /**
     * An electronic chipset manages the flow of data between components on a motherboard.
     */
    protected String chipset;

    /**
     * VRAM of the GPU in GB.
     */
    protected String memory;

    /**
     * Clock speed of the GPU in MHz.
     */
    protected String clock;

    /**
     * Initializes a new GPU.
     *
     * @param name the name of the GPU
     * @param price the price of the GPU
     * @param power the power consumption of the GPU
     * @param memory the VRAM of the GPU
     * @param clock the clock speed of the GPU
     */
    public Gpu(String name, String price, String power, String memory, String clock) {
        this.name = name;
        this.price = price;
        this.memory = memory;
        this.clock = clock;
        this.power = power;
    }

    /**
     * Gets the chipset of the GPU.
     *
     * @return the chipset of the GPU
     */
    public String getChipset() {
        return chipset;
    }

    /**
     * Sets the chipset of the GPU.
     *
     * @param chipset the chipset of the GPU
     */
    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    /**
     * Gets the VRAM of the GPU.
     *
     * @return the VRAM of the GPU
     */
    public String getMemory() {
        return memory;
    }

    /**
     * Sets the VRAM of the GPU.
     *
     * @param memory the VRAM of the GPU
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * Gets the clock speed of the GPU.
     *
     * @return the clock speed of the GPU
     */
    public String getClock() {
        return clock;
    }

    /**
     * Sets the clock speed of the GPU.
     *
     * @param clock the clock speed of the GPU
     */
    public void setClock(String clock) {
        this.clock = clock;
    }

    /**
     * Creates a string representation of all the information of the GPU.
     * Uses this method to display the information of the GPU.
     *
     * @return a string representation of all the information of the GPU
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s GB] [%s MHz]", memory, clock);
    }

    /**
     * Creates a string representation of all the information of the GPU.
     * Uses this method to save the information of the GPU to a csv file.
     *
     * @return a string representation of all the information of the GPU
     */
    public String toCsv() {
        return super.toCsv() + "," + memory + "," + clock;
    }

    /**
     * Creates a string representation of all the information of the GPU.
     * Uses this method to save the information of the GPU to a text file.
     *
     * @return a string representation of all the information of the GPU
     */
    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + memory + "/" + clock;
    }

    /**
     * Gets all the information of the GPU.
     *
     * @return all the information of the GPU
     */
    @Override
    public String getDetails() {
        return super.getDetails() + String.format("\nMemory: %s GB\nClock: %s MHz", memory, clock);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "gpu";
    }
}
