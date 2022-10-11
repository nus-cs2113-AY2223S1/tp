package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String PROPERTY_PATH = "./data/property.txt";
    private static final String CLIENT_PATH = "./data/client.txt";
    private static final String PAIR_PATH = "./data/pair.txt";
    private static final String SEPARATOR = " | ";
    private static final String CURRENCY = "SGD";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String COLON = " : ";
    private static final String EMPTY_STRING = "";

    
    public Storage(ClientList clientList, PropertyList propertyList, PairingList pairingList) {
        boolean hasDirectory = checkDirectory();
        boolean hasPropertyFile = checkPropertyFile();
        boolean hasClientFile = checkClientFile();
        boolean hasPairingFile = checkPair();

        loadFiles(hasDirectory, hasPropertyFile, hasClientFile, hasPairingFile, clientList, propertyList, pairingList);

    }

    /**
     * Checks if the data directory exist.
     *
     * @return true if data directory exist and false if it does not
     */
    public boolean checkDirectory() {
        File dir = new File(DIRECTORY);
        boolean hasDirectory = dir.exists();

        return hasDirectory;
    }

    /**
     * Creates a data directory.
     */
    public void makeDirectory() {
        File dir = new File(DIRECTORY);

        // Creates a directory
        dir.mkdir();
    }

    /**
     * Checks if the property file exist.
     *
     * @return true if the property text file exist and false if it does not
     */
    public boolean checkPropertyFile() {
        File propertyFile = new File(PROPERTY_PATH);
        boolean hasPropertyFile = propertyFile.exists();


        return hasPropertyFile;
    }



    /**
     * Checks if the client file exist.
     *
     * @return true if the client text file exist and false if it does not
     */
    public boolean checkClientFile() {
        File clientFile = new File(CLIENT_PATH);
        boolean hasClientFile = clientFile.exists();


        return hasClientFile;
    }

    /**
     * Checks if the pair file exit.
     *
     * @return true if pairing file exist and false if it does not
     */
    public boolean checkPair() {
        File pairFile = new File(PAIR_PATH);
        boolean hasPairFile = pairFile.exists();


        return hasPairFile;
    }

    /**
     * Creates a directory if it does not already exist then load the file into the relevant array list and
     * hash table if the text file exist.
     *
     * @param hasDirectory boolean value on whether directory exist
     * @param hasPropertyFile boolean value on whether property text file exist
     * @param hasClientFile boolean value on whether client text file exist
     * @param hasPairingFile boolean value on whether pairing text file exist
     * @param clientList the array list containing the list of client
     * @param propertyList the array list containing the list of property
     * @param pairingList the hash map containing the pairing between client and property
     */
    public void loadFiles(boolean hasDirectory, boolean hasPropertyFile, boolean hasClientFile,
                          boolean hasPairingFile, ClientList clientList, PropertyList propertyList,
                          PairingList pairingList) {
        if (!hasDirectory) {
            makeDirectory();
        }

        if (hasClientFile) {
            File clientFile = new File(CLIENT_PATH);
            loadClient(clientList, clientFile);
        }

        if (hasPropertyFile) {
            File propertyFile = new File(PROPERTY_PATH);
            loadProperty(propertyList, propertyFile);
        }

        if (hasPairingFile) {
            File pairingFile = new File(PAIR_PATH);
            loadPair(pairingList, pairingFile);
        }
    }

    /**
     * Adds the client list in the text file to the array list.
     *
     * @param clientList Client List object that contains client's array list
     * @param clientFile The file that stores the list of client.
     */
    public void loadClient(ClientList clientList, File clientFile) {

        try {
            Scanner scanner = new Scanner(clientFile);
            while (scanner.hasNext()) {
                String[] clientParameters = scanner.nextLine().split("\\s\\|\\s");
                String clientName = clientParameters[0];
                String clientContact = clientParameters[1];
                String clientEmail = clientParameters[2];
                String clientBudget = clientParameters[3].replace(CURRENCY, EMPTY_STRING).trim();
                clientList.addClient(clientName, clientContact, clientEmail, clientBudget);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found...");
        }


    }

    /**
     * Loads the stored property file into the property array list.
     *
     * @param propertyList Property List object that contains property's array list.
     * @param propertyFile The file that stores the list of property.
     */
    public void loadProperty(PropertyList propertyList, File propertyFile) {

    }

    /**
     * Loads the stored pair file into the pair hash map.
     *
     * @param pairingList Paring List object that contains the hash map for pairings.
     * @param pairFile The file that contains the pairing file.
     */
    public void loadPair(PairingList pairingList, File pairFile) {

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
            String rentalPrice = CURRENCY + price;
            String newText = landlord + SEPARATOR + address + SEPARATOR + rentalPrice
                    + SEPARATOR + unitType + System.lineSeparator();

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
            String budgetPrice = CURRENCY + budget;
            String newText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR
                    + budgetPrice + System.lineSeparator();

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
            String monthlyPrice = CURRENCY + price;
            String clientPortion = OPEN_BRACKET + client + SEPARATOR + contact + CLOSE_BRACKET;
            String propertyPortion = OPEN_BRACKET + landlord + SEPARATOR + address
                    + SEPARATOR + monthlyPrice + SEPARATOR + type + CLOSE_BRACKET;
            String finalString = clientPortion + COLON + propertyPortion;
            fw.write(finalString);
            fw.close();

        } catch (IOException e) {
            System.out.println("Pairing file does not exist.");
        }
    }

    /**
     * Updates the client list when entry in client list is deleted.
     * @param clientList The array list containing all the clients.
     */
    public void updateClient(ClientList clientList) {
        try {
            FileWriter clientFile = new FileWriter(CLIENT_PATH);
            ArrayList<Client> clientLists = clientList.getClientList();

            // clientText will initially be empty and will be appended in subsequent iterations of the client list.
            String clientText = "";
            for (int i = 0; i < clientLists.size(); i += 1) {
                // Concatenate the string variables into clientText
                String budgetPrice = CURRENCY + clientLists.get(i).getClientBudgetPerMonth();
                String name = clientLists.get(i).getClientName();
                String contact = clientLists.get(i).getClientContactNumber();
                String email = clientLists.get(i).getClientEmail();
                String finalText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR
                        + budgetPrice + System.lineSeparator();
                clientText = clientText.concat(finalText);
            }

            // Write the client list into a file.
            clientFile.write(clientText);
            clientFile.close();

        } catch (IOException e) {
            System.out.println("Client file does not exist.");
        }




    }
}
