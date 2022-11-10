package computercomponentchooser.components;

import computercomponentchooser.exceptions.NegativeNumberException;

/**
 * Represents the drive in the computer build.
 */
public class Drive extends Component {

    /**
     * The size of the drive in GB.
     */
    protected String size;

    /**
     * The type of the drive.
     */
    protected String driveType;

    /**
     * Initializes a new drive.
     *
     * @param name the name of the drive
     * @param price the price of the drive
     * @param power the power consumption of the drive
     * @param size the size of the drive
     * @param driveType the type of the drive
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public Drive(String name, String price, String power, String size, String driveType) throws NegativeNumberException,
            NumberFormatException {
        double priceDouble = Double.parseDouble(price);
        int powerInt = Integer.parseInt(power);
        double sizeDouble = Double.parseDouble(size);
        if (priceDouble < 0 || powerInt < 0 || sizeDouble < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.price = price;
        this.size = size;
        this.driveType = driveType;
        this.power = power;
    }

    /**
     * Gets the size of the drive.
     *
     * @return the size of the drive
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the drive.
     *
     * @param size the size of the drive
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the type of the drive.
     *
     * @return the type of the drive
     */
    public String getDriveType() {
        return driveType;
    }

    /**
     * Sets the type of the drive.
     *
     * @param driveType the type of the drive
     */
    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    /**
     * Creates a string representation of all the information of the drive.
     * Uses this method to display the information of the drive.
     *
     * @return a string representation of all the information of the drive
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s GB] [%s]", size, driveType);
    }

    /**
     * Creates a string representation of all the information of the drive.
     * Uses this method to save the information of the drive to a csv file.
     *
     * @return a string representation of all the information of the drive
     */
    public String toCsv() {
        return super.toCsv() + "," + size + "," + driveType;
    }

    /**
     * Creates a string representation of all the information of the drive.
     * Uses this method to save the information of the drive to a text file.
     *
     * @return a string representation of all the information of the drive
     */
    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + size + "/" + driveType;
    }

    /**
     * Gets all the information of the drive.
     *
     * @return all the information of the drive
     */
    public String getDetails() {
        return super.getDetails() + String.format("\nSize: %s GB\nType: %s", size, driveType);
    }

    /**
     * Gets the type of the computer part.
     *
     * @return the type of the computer part
     */
    public String getType() {
        return "drive";
    }
}
