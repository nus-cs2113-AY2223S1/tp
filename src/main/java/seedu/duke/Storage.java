package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String PROPERTY_PATH = "./data/property.txt";
    private static final String CLIENT_PATH = "./data/client.txt";
    private static final String PAIR_PATH = "./data/pair.txt";
    private static final String SEPARATOR = " | ";
    private static final String DOLLAR_SIGN = "$";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String COLON = " : ";

    /**
     * Constructor
     */
    public Storage() {
        checkFile();
    }

    /**
     * Checks if all the file exist.
     */
    public void checkFile() {
        checkDirectory();
        checkPropertyFile();
        checkClientFile();
        checkPair();
    }

    /**
     * Checks if the data directory exist.
     */
    public void checkDirectory() {
        File dir = new File(DIRECTORY);
        boolean hasDirectory = dir.exists();

        // Create a directory if the directory does not exist.
        if (!hasDirectory) {
            dir.mkdir();
        }
    }

    /**
     * Checks if the property file exist.
     */
    public void checkPropertyFile() {
        File propertyFile = new File(PROPERTY_PATH);
        boolean hasPropertyFile = propertyFile.exists();

        if (hasPropertyFile) {
            // Load property into the array list
            loadProperty(propertyFile);
        }
    }

    /**
     * Checks if the client file exist.
     */
    public void checkClientFile() {
        File clientFile = new File(CLIENT_PATH);
        boolean hasClientFile = clientFile.exists();

        if (!hasClientFile) {
            // Load client into the array list
            loadClient(clientFile);
        }
    }

    /**
     * Checks if the pair file exit.
     */
    public void checkPair() {
        File pairFile = new File(PAIR_PATH);
        boolean hasPairFile = pairFile.exists();

        if (!hasPairFile) {
            // Load pair into the hash map
            loadPair(pairFile);
        }
    }

    /**
     * Adds the client list in the text file to the array list.
     *
     * @param clientFile The file that stores the list of client.
     */
    public void loadClient(File clientFile) {

    }

    /**
     * Loads the stored property file into the property array list.
     *
     * @param propertyFile The file that stores the list of property.
     */
    public void loadProperty(File propertyFile) {

    }

    /**
     * Loads the stored pair file into the pair hash map.
     *
     * @param pairFile The file that stores all the pairs.
     */
    public void loadPair(File pairFile) {

    }


    /**
     * Appends the latest property into property text file.
     *
     * @param landlord The name of the landlord.
     * @param address The address of the property being leased.
     * @param price The monthly rental price.
     * @param unitType The type of the unit being leased.
     */
    public void addToPropertyFile(String landlord, String address, int price, String unitType) {
        try {
            FileWriter fw = new FileWriter(PROPERTY_PATH, true);
            String rentalPrice = DOLLAR_SIGN + price;
            String newText = landlord + SEPARATOR + address + SEPARATOR + rentalPrice +
                    SEPARATOR + unitType + System.lineSeparator();

            fw.write(newText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Property file does not exist.");
        }

    }

    /**
     * Appends the latest client into the client text file.
     *
     * @param name The name of the client.
     * @param contact The contact number of the client.
     * @param email The email of the client.
     * @param budget The budget the client is looking at.
     */
    public void addToClientFile(String name, String contact, String email, int budget) {
        try {
            FileWriter fw = new FileWriter(CLIENT_PATH, true);
            String budgetPrice = DOLLAR_SIGN + budget;
            String newText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR + budgetPrice;

            fw.write(newText);
            fw.close();

        } catch (IOException e) {
            System.out.println("Client file does not exist.");
        }
    }

    /**
     * Appends the latest pair into the pair text file.
     *
     * @param client The name of the client.
     * @param contact The contact number of the client.
     * @param landlord The name of the landlord.
     * @param address The address of the rental unit.
     * @param price The monthly rental price per month.
     * @param type The type of the rental unit.
     */
    public void addToPairFile(String client, String contact, String landlord,
                              String address, int price, String type) {
        try {
            FileWriter fw = new FileWriter(PAIR_PATH, true);
            String monthlyPrice = DOLLAR_SIGN + price;
            String clientPortion = OPEN_BRACKET + client + SEPARATOR + contact + CLOSE_BRACKET;
            String propertyPortion = OPEN_BRACKET + landlord + SEPARATOR + address +
                    SEPARATOR + monthlyPrice + SEPARATOR + type + CLOSE_BRACKET;
            String finalString = clientPortion + COLON + propertyPortion;
            fw.write(finalString);
            fw.close();

        } catch (IOException e) {
            System.out.println("Pairing file does not exist.");
        }
    }
}
