package computercomponentchooser.components;

/**
 * Represents the cooler in the computer build.
 */
public class Cooler extends Component {

    /**
     * The socket type of the cooler.
     */
    protected String socket;

    /**
     * The fan speed of the cooler.
     */
    protected String fanSpeed;

    /**
     * The fan noise of the cooler.
     */
    protected String noiseLevel;

    /**
     * Initializes a new cooler.
     *
     * @param name the name of the cooler
     * @param price the price of the cooler
     * @param power the power consumption of the cooler
     * @param socket the socket type of the cooler
     * @param fanSpeed the fan speed of the cooler
     * @param noiseLevel the fan noise of the cooler
     */
    public Cooler(String name, String price, String power, String socket, String fanSpeed, String noiseLevel) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.socket = socket;
        this.fanSpeed = fanSpeed;
        this.noiseLevel = noiseLevel;
    }

    /**
     * Gets the fan speed of the cooler.
     *
     * @return the fan speed of the cooler
     */
    public String getFanSpeed() {
        return fanSpeed;
    }

    /**
     * Sets the fan speed of the cooler.
     *
     * @param fanSpeed the fan speed of the cooler
     */
    public void setFanSpeed(String fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    /**
     * Gets the fan noise of the cooler.
     *
     * @return the fan noise of the cooler
     */
    public String getNoiseLevel() {
        return noiseLevel;
    }

    /**
     * Sets the fan noise of the cooler.
     *
     * @param noiseLevel the fan noise of the cooler
     */
    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    /**
     * Gets the socket type of the cooler.
     *
     * @return the socket type of the cooler
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Sets the socket type of the cooler.
     *
     * @param socket the socket type of the cooler
     */
    public void setSocket(String socket) {
        this.socket = socket;
    }

    /**
     * Creates a string representation of all the information of the cooler.
     * Uses this method to display the information of the cooler.
     *
     * @return a string representation of all the information of the cooler
     */
    public String toString() {
        return super.toString() + String.format(" [%s] [%s RPM] [%s dB]", socket, fanSpeed, noiseLevel);
    }

    /**
     * Creates a string representation of all the information of the cooler.
     * Uses this method to save the information of the cooler to a csv file.
     *
     * @return a string representation of all the information of the cooler
     */
    public String toCsv() {
        return super.toCsv() + "," + socket + "," + fanSpeed + "," + noiseLevel;
    }

    /**
     * Creates a string representation of all the information of the cooler.
     * Uses this method to save the information of the cooler to a text file.
     *
     * @return a string representation of all the information of the cooler
     */
    public String saveAsString() {
        return super.saveAsString() + "," + socket + "," + fanSpeed + "," + noiseLevel;
    }

    /**
     * Gets all the information of the cooler.
     *
     * @return all the information of the cooler
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nFan Speed: %s RPM\nNoise Level: %s dB", socket,
                fanSpeed, noiseLevel);
    }

    /**
     * Gets the type of the component.
     *
     * @return the type of the component
     */
    @Override
    public String getType() {
        return "cooler";
    }
}
