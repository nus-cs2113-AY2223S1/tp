package computercomponentchooser.components;

/**
 * Represents a computer part in the computer build.
 */
public abstract class Component {
    /**
     * The name of the computer part.
     */
    protected String name;

    /**
     * The price of the computer part.
     */
    protected String price;

    /**
     * The power consumption of the computer part.
     */
    protected String power;

    /**
     * Gets the name of the computer part.
     *
     * @return the name of the computer part
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the computer part.
     *
     * @param name the name of the computer part
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the computer part.
     *
     * @return the price of the computer part
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the price of the computer part.
     *
     * @param price the price of the computer part
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Gets the power of the computer part.
     *
     * @return the power of the computer part
     */
    public String getPower() {
        return power;
    }

    /**
     * Sets the power of the computer part.
     *
     * @param power the power of the computer part
     */
    public void setPower(String power) {
        this.power = power;
    }

    /**
     * Creates a string representation of all the information about the computer part.
     * Uses this method to display the information about the computer part.
     *
     * @return the string representation of all the information about the computer part
     */
    @Override
    public String toString() {
        return String.format("%s [$%s] [%s W]", name, price, power);
    }

    /**
     * Creates a string representation of all the information about the computer part.
     * Uses this method to save the information about the computer part to a csv file.
     *
     * @return the string representation of all the information about the computer part
     */
    public String toCsv() {
        return String.format("%s,%s,%s", name, price, power);
    }

    /**
     * Creates a string representation of all the information about the computer part.
     * Uses this method to save the information about the computer part to a text file.
     *
     * @return the string representation of all the information about the computer part
     */
    public String saveAsString() {
        return getType() + "/" + name + "/" + price + "/" + power;
    }

    /**
     * Gets the all the information about the computer part.
     *
     * @return the all the information about the computer part
     */
    public String getDetails() {
        return String.format("Name: %s\nPrice: $%s\nPower: %s W", name, price, power);
    }

    /**
     * Gets the type of the computer part.
     */
    public abstract Object getType();
}
