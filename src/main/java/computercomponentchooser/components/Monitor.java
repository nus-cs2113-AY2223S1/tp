package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

/**
 * Represents the monitor of the computer build.
 */
public class Monitor extends Component {

    /**
     * The refresh rate of the monitor in Hz.
     */
    protected String refreshRate;

    /**
     * The response time of the monitor in ms.
     */
    protected String responseTime;

    /**
     * The resolution of the monitor in pixel.
     */
    protected String resolution;

    /**
     * Initializes a new monitor.
     *
     * @param name the name of the monitor
     * @param price the price of the monitor
     * @param power the power consumption of the monitor
     * @param refreshRate the refresh rate of the monitor
     * @param responseTime the response time of the monitor
     * @param resolution the resolution of the monitor
     */
    public Monitor(String name, String price, String power, String refreshRate, String responseTime,
                   String resolution) throws NegativeNumberException, NumberFormatException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        int refreshRateInt = Integer.parseInt(refreshRate);
        int responseTimeInt = Integer.parseInt(responseTime);
        if (priceDouble < 0 || powerInt < 0 || refreshRateInt < 0 || responseTimeInt < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.price = price;
        this.power = power;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
        this.resolution = resolution;
    }

    /**
     * Gets the refresh rate of the monitor.
     *
     * @return the refresh rate of the monitor
     */
    public String getRefreshRate() {
        return refreshRate;
    }

    /**
     * Sets the refresh rate of the monitor.
     *
     * @param refreshRate the refresh rate of the monitor
     */
    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    /**
     * Gets the response time of the monitor.
     *
     * @return the response time of the monitor
     */
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * Sets the response time of the monitor.
     *
     * @param responseTime the response time of the monitor
     */
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * Gets the resolution of the monitor.
     *
     * @return the resolution of the monitor
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the resolution of the monitor.
     *
     * @param resolution the resolution of the monitor
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Creates a string representation of all the information of the Monitor.
     * Uses this method to display the information of the Monitor.
     *
     * @return a string representation of all the information of the Monitor
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s Hz] [%s ms] [%s p]", refreshRate, responseTime, resolution);
    }

    /**
     * Creates a string representation of all the information of the Monitor.
     * Uses this method to save the information of the Monitor to a csv file.
     *
     * @return a string representation of all the information of the Monitor
     */
    public String toCsv() {
        return super.toCsv() + "," + refreshRate + "," + responseTime + "," + resolution;
    }

    /**
     * Creates a string representation of all the information of the Monitor.
     * Uses this method to save the information of the Monitor to a text file.
     *
     * @return a string representation of all the information of the Monitor
     */
    public String saveAsString() {
        return super.saveAsString() + "/" + refreshRate + "/" + responseTime + "/" + resolution;
    }

    /**
     * Gets all the information of the Monitor.
     *
     * @return all the information of the Monitor
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nRefresh Rate: %s Hz\nResponse Time: %s ms\nResolution: %s p",
                                                 refreshRate, responseTime, resolution);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    @Override
    public String getType() {
        return "monitor";
    }


}
