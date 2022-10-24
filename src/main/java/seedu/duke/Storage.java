package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.parsermanager.ParseAddClient;
import seedu.duke.parsermanager.ParseAddProperty;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.CommandStructure.UNIT_TYPE_ONE;
import static seedu.duke.CommandStructure.UNIT_TYPE_TWO;
import static seedu.duke.CommandStructure.UNIT_TYPE_THREE;
import static seedu.duke.CommandStructure.UNIT_TYPE_FOUR;
import static seedu.duke.CommandStructure.UNIT_TYPE_FIVE;
import static seedu.duke.CommandStructure.UNIT_TYPE_SIX;
import static seedu.duke.CommandStructure.UNIT_TYPE_SEVEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_EIGHT;
import static seedu.duke.CommandStructure.UNIT_TYPE_NINE;
import static seedu.duke.CommandStructure.UNIT_TYPE_TEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_ELEVEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_TWELVE;
import static seedu.duke.CommandStructure.UNIT_TYPE_THIRTEEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_FOURTEEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_FIFTEEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_ONE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_TWO;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_THREE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_FOUR;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_FIVE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_SIX;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_SEVEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_EIGHT;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_NINE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_TEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_ELEVEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_TWELVE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_THIRTEEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_FOURTEEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_FIFTEEN;



public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String PROPERTY_PATH = "./data/property.txt";
    private static final String CLIENT_PATH = "./data/client.txt";
    private static final String PAIR_PATH = "./data/pair.txt";
    private static final String SEPARATOR = " | ";
    private static final String COLON = " : ";
    private static final String EMPTY_STRING = "";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String LOG_ADD_CLIENT_LABEL = "Client has been added to text file as: ";
    private static final String LOG_ADD_PROPERTY_LABEL = "Property has been added to text file as: ";
    private static final String LOG_ADD_PAIRING_LABEL = "Pairings has been added to text file as: ";
    private static final String LOG_CLIENT_UPDATE_LABEL = "Client file has been successfully updated";
    private static final String LOG_PROPERTY_UPDATE_LABEL = "Property file has been successfully updated";
    private static final String LOG_PAIRING_UPDATE_LABEL = "Pairing file has been successfully updated";
    private static final String LOG_CLIENT_LOAD_LABEL = "Client has been successfully loaded into the array list.";
    private static final String LOG_PROPERTY_LOAD_LABEL = "Property has been successfully loaded into the array list.";
    private static final String LOG_PAIRING_LOAD_LABEL = "Pairing has been successfully loaded into the hashmap.";
    private static final String CLIENT_NAME_FLAG = "n/";
    private static final String CLIENT_CONTACT_FLAG = " c/";
    private static final String CLIENT_EMAIL_FLAG = " e/";
    private static final String CLIENT_BUDGET_FLAG = " b/";
    private static final String LANDLORD_NAME_FLAG = "n/";
    private static final String PROPERTY_ADDRESS_FLAG = " a/";
    private static final String PROPERTY_RENTAL_FLAG = " p/";
    private static final String PROPERTY_TYPE_FLAG = " t/";

    private static final Logger LOGGER = Logger.getLogger("Storage");

    private ClientList clientList;
    private PropertyList propertyList;
    private PairingList pairingList;
    private HashMap<String, String> unitTypeHashMap = new HashMap<>();


    public Storage(ClientList clientList, PropertyList propertyList, PairingList pairingList) {
        this.clientList = clientList;
        this.propertyList = propertyList;
        this.pairingList = pairingList;


        // Loading all the actual and unit type label
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_ONE, UNIT_TYPE_ONE);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_TWO, UNIT_TYPE_TWO);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_THREE, UNIT_TYPE_THREE);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_FOUR, UNIT_TYPE_FOUR);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_FIVE, UNIT_TYPE_FIVE);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_SIX, UNIT_TYPE_SIX);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_SEVEN, UNIT_TYPE_SEVEN);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_EIGHT, UNIT_TYPE_EIGHT);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_NINE, UNIT_TYPE_NINE);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_TEN, UNIT_TYPE_TEN);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_ELEVEN, UNIT_TYPE_ELEVEN);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_TWELVE, UNIT_TYPE_TWELVE);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_THIRTEEN, UNIT_TYPE_THIRTEEN);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_FOURTEEN, UNIT_TYPE_FOURTEEN);
        unitTypeHashMap.put(ACTUAL_UNIT_TYPE_FIFTEEN, UNIT_TYPE_FIFTEEN);

        boolean hasDirectory = checkDirectory();
        boolean hasPropertyFile = checkPropertyFile();
        boolean hasClientFile = checkClientFile();
        boolean hasPairingFile = checkPair();

        loadFiles(hasDirectory, hasPropertyFile, hasClientFile, hasPairingFile);

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

        return propertyFile.exists();
    }



    /**
     * Checks if the client file exist.
     *
     * @return true if the client text file exist and false if it does not
     */
    public boolean checkClientFile() {
        File clientFile = new File(CLIENT_PATH);

        return clientFile.exists();
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
     */
    public void loadFiles(boolean hasDirectory, boolean hasPropertyFile, boolean hasClientFile,
                          boolean hasPairingFile) {
        if (!hasDirectory) {
            makeDirectory();
        }

        if (hasClientFile) {
            File clientFile = new File(CLIENT_PATH);
            assert clientFile.exists() : "Client text file does not exist";
            loadClient(clientFile);
        }

        if (hasPropertyFile) {
            File propertyFile = new File(PROPERTY_PATH);
            assert propertyFile.exists() : "Property text file does not exist";
            loadProperty(propertyFile);
        }

        if (hasPairingFile) {
            File pairingFile = new File(PAIR_PATH);
            assert pairingFile.exists() : "Pairing text file does not exist";
            loadPair(pairingFile);
        }
    }

    /**
     * Adds the client list in the text file to the array list.
     *
     * @param clientFile The file that stores the list of client.
     */
    public void loadClient(File clientFile) {
        try {
            Scanner scanner = new Scanner(clientFile);
            while (scanner.hasNext()) {
                String[] clientParameters = scanner.nextLine().split("\\s\\|\\s");
                if (clientParameters.length == 4) {
                    String clientName = clientParameters[0];
                    String clientContact = clientParameters[1];
                    String clientEmail = clientParameters[2];
                    String clientBudget = clientParameters[3].trim();

                    String description = CLIENT_NAME_FLAG + clientName + CLIENT_CONTACT_FLAG + clientContact
                            + CLIENT_EMAIL_FLAG + clientEmail + CLIENT_BUDGET_FLAG + clientBudget;

                    ParseAddClient parser = new ParseAddClient(description, clientList);
                    try {
                        parser.parseCommand();
                        clientList.addClient(clientName, clientContact, clientEmail, clientBudget);
                    } catch (DukeException e) {
                        System.out.println(Messages.INVALID_CLIENT_FILE);
                    }


                }
            }
            LOGGER.log(Level.INFO, LOG_CLIENT_LOAD_LABEL);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.MESSAGE_NO_FILE);
        }

        updateClient();
    }

    /**
     * Loads the stored property file into the property array list.
     *
     * @param propertyFile The file that stores the list of property.
     */
    public void loadProperty(File propertyFile) {
        try {
            Scanner scanner = new Scanner(propertyFile);
            while (scanner.hasNext()) {
                String[] propertyParameters = scanner.nextLine().split("\\s\\|\\s");
                if (propertyParameters.length == 4) {
                    String landlordName = propertyParameters[0];
                    String address = propertyParameters[1];
                    String price = propertyParameters[2];
                    String unitTypeString = propertyParameters[3].trim();

                    String unitTypeLabel = getUnitTypeLabel(unitTypeString);

                    String description = LANDLORD_NAME_FLAG + landlordName + PROPERTY_ADDRESS_FLAG + address
                            + PROPERTY_RENTAL_FLAG + price + PROPERTY_TYPE_FLAG + unitTypeLabel;

                    ParseAddProperty parser = new ParseAddProperty(description, propertyList);
                    try {
                        parser.parseCommand();
                        propertyList.addProperty(landlordName, address, price, unitTypeString);
                    } catch (DukeException e) {
                        System.out.println(Messages.INVALID_PROPERTY_FILE);
                    }
                }
            }
            LOGGER.log(Level.INFO, LOG_PROPERTY_LOAD_LABEL);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.MESSAGE_NO_FILE);
        }

        updateProperty();
    }

    /**
     * Loads the stored pair file into the pair hash map.
     *
     * @param pairFile The file that contains the pairing file.
     */
    public void loadPair(File pairFile) {

        // Update pairing hash map
        scanPairingFile(pairFile);

        // Re-populate pairing.txt
        updatePair();

    }


    /**
     * Appends the latest property into property text file.
     *
     * @param landlord The name of the landlord.
     * @param address The address of the property being leased.
     * @param price The monthly rental price.
     * @param unitType The type of the unit being leased.
     */
    public void addToPropertyFile(String landlord, String address, String price, String unitType) {
        try {
            FileWriter fw = new FileWriter(PROPERTY_PATH, true);
            String rentalPrice = price;
            String newText = landlord + SEPARATOR + address + SEPARATOR + rentalPrice
                    + SEPARATOR + unitType + System.lineSeparator();

            fw.write(newText);
            fw.close();

            String logMessage = LOG_ADD_PROPERTY_LABEL + newText;
            LOGGER.log(Level.INFO, logMessage);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_PROPERTY_FILE);
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
    public void addToClientFile(String name, String contact, String email, String budget) {
        try {
            FileWriter fw = new FileWriter(CLIENT_PATH, true);
            String budgetPrice = budget;
            String newText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR
                    + budgetPrice + System.lineSeparator();

            fw.write(newText);
            fw.close();

            String logMessage = LOG_ADD_CLIENT_LABEL + newText;
            LOGGER.log(Level.INFO, logMessage);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_CLIENT_FILE);
        }
    }


    /**
     * Appends the latest pair to the pairing hash map.
     *
     * @param clientFormat The key of the hash map, in the client format that will be inputted into pairing.txt
     * @param propertyFormat The value of the hash map, in the property format that will be inputted into pairing.txt
     */
    public void addToPairFile(String clientFormat, String propertyFormat) {
        try {
            FileWriter fw = new FileWriter(PAIR_PATH, true);

            String finalFormat = clientFormat + COLON + propertyFormat + System.lineSeparator();
            fw.write(finalFormat);
            fw.close();

            String logMessage = LOG_ADD_PAIRING_LABEL + finalFormat;
            LOGGER.log(Level.INFO, logMessage);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_PAIRING_FILE);
        }
    }

    /**
     * Updates the client list when entry in client list is deleted.
     *
     */
    public void updateClient() {
        try {
            FileWriter clientFile = new FileWriter(CLIENT_PATH);
            ArrayList<Client> clientLists = clientList.getClientList();

            // clientText will initially be empty and will be appended in subsequent iterations of the client list.
            String clientText = EMPTY_STRING;
            for (int i = 0; i < clientLists.size(); i += 1) {
                // Concatenate the string variables into clientText
                String budgetPrice = clientLists.get(i).getClientBudgetPerMonth();
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
            LOGGER.log(Level.INFO, LOG_CLIENT_UPDATE_LABEL);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_CLIENT_FILE);
        }
    }

    /**
     * Updates the property list in the text file when entry is deleted.
     *
     */
    public void updateProperty() {
        try {
            FileWriter propertyFile = new FileWriter(PROPERTY_PATH);
            ArrayList<Property> propertyLists = propertyList.getPropertyList();
            String propertyText = EMPTY_STRING;

            for (int i = 0; i < propertyLists.size(); i += 1) {
                String landlordName = propertyLists.get(i).getLandlordName();
                String propertyAddress = propertyLists.get(i).getPropertyAddress();
                String rentingPrice = propertyLists.get(i).getRentingPrice();
                String unitTypeString = propertyLists.get(i).getUnitType();

                String finalText = landlordName + SEPARATOR + propertyAddress + SEPARATOR
                        + rentingPrice + SEPARATOR + unitTypeString + System.lineSeparator();

                propertyText = propertyText.concat(finalText);
            }

            propertyFile.write(propertyText);
            propertyFile.close();
            LOGGER.log(Level.INFO, LOG_PROPERTY_UPDATE_LABEL);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_PROPERTY_FILE);
        }
    }

    /**
     * Updates the pairing text file when entries are unpaired.
     *
     */
    public void updatePair() {
        try {
            HashMap<Client, Property> clientPropertyPair = pairingList.getClientPropertyPairs();
            FileWriter pairFile = new FileWriter(PAIR_PATH);

            String clientName;
            String clientContact;
            String clientEmail;
            String clientBudget;
            String landlordName;
            String propertyAddress;
            String propertyPrice;
            String propertyType;

            String pairText = EMPTY_STRING;
            for (Client clientText : clientPropertyPair.keySet()) {

                clientName = clientText.getClientName();
                clientContact = clientText.getClientContactNumber();
                clientEmail = clientText.getClientEmail();
                clientBudget = clientText.getClientBudgetPerMonth();

                landlordName = clientPropertyPair.get(clientText).getLandlordName();
                propertyAddress = clientPropertyPair.get(clientText).getPropertyAddress();
                propertyPrice = clientPropertyPair.get(clientText).getRentingPrice();
                propertyType = clientPropertyPair.get(clientText).getUnitType();

                String clientFinalText = OPEN_BRACKET + clientName + SEPARATOR + clientContact + SEPARATOR
                        + clientEmail + SEPARATOR + clientBudget + CLOSE_BRACKET;

                String propertyFinalText = OPEN_BRACKET + landlordName + SEPARATOR + propertyAddress
                        + SEPARATOR + propertyPrice + SEPARATOR + propertyType + CLOSE_BRACKET;

                String finalText = clientFinalText + COLON + propertyFinalText;

                pairText = pairText.concat(finalText);
            }

            // Write the pairing list into a file.
            pairFile.write(pairText);
            pairFile.close();
            LOGGER.log(Level.INFO, LOG_PAIRING_UPDATE_LABEL);

        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_NO_PAIRING_FILE);
        }
    }



    /**
     * Populates the pairing file after comparing whether Client is in clientList and Property is in propertyList.
     *
     * @param pairFile The text file that will be read and check if it exists in clientList and propertyList.
     */
    public void scanPairingFile(File pairFile) {
        Scanner scanner;
        try {
            scanner = new Scanner(pairFile);
            while (scanner.hasNext()) {
                String[] pairingParameters = scanner.nextLine().split("\\s\\:\\s");
                if (pairingParameters.length == 2) {
                    String[] clientParameters = pairingParameters[0].split("\\s\\|\\s");
                    String[] propertyParameters = pairingParameters[1].split("\\s\\|\\s");

                    String clientName = "";
                    String clientContactNumber = "";
                    String clientEmail = "";
                    String clientBudget = "";
                    String landLordName = "";
                    String propertyAddress = "";
                    String rentalPrice = "";
                    String unitType = "";

                    //Client Information
                    if (clientParameters.length == 4) {
                        clientName = clientParameters[0].replace(OPEN_BRACKET, EMPTY_STRING);
                        clientContactNumber = clientParameters[1];
                        clientEmail = clientParameters[2];
                        clientBudget = clientParameters[3].replace(CLOSE_BRACKET, EMPTY_STRING);
                    }

                    //Property Information
                    if (propertyParameters.length == 4) {
                        landLordName = propertyParameters[0].replace(OPEN_BRACKET,EMPTY_STRING);
                        propertyAddress = propertyParameters[1];
                        rentalPrice = propertyParameters[2];
                        unitType = propertyParameters[3].replace(CLOSE_BRACKET,EMPTY_STRING);
                    }

                    // If both property and client have the right format
                    Client pairingClient = new Client(clientName, clientContactNumber, clientEmail, clientBudget);
                    Property pairingProperty = new Property(landLordName, propertyAddress, rentalPrice, unitType);

                    if ((clientParameters.length == 4) && (propertyParameters.length == 4)) {
                        for (int i = 0; i < clientList.getCurrentListSize(); i += 1) {
                            String clientListName = clientList.getClientList().get(i).getClientName();
                            String clientListContactNumber = clientList.getClientList().get(i).getClientContactNumber();
                            String clientListEmail = clientList.getClientList().get(i).getClientEmail();
                            String clientListBudget = clientList.getClientList().get(i).getClientBudgetPerMonth();
                            boolean isCorrectClientName = clientListName.equals(clientName);
                            boolean isCorrectClientContact = clientListContactNumber.equals(clientContactNumber);
                            boolean isCorrectClientEmail = clientListEmail.equals(clientEmail);
                            boolean isCorrectClientBudget = clientListBudget.equals(clientBudget);

                            boolean isCorrectClient = isCorrectClientName && isCorrectClientContact
                                    && isCorrectClientEmail && isCorrectClientBudget;

                            if (isCorrectClient) {
                                pairingClient = clientList.getClientList().get(i);
                            }
                        }

                        for (int i = 0; i < propertyList.getCurrentListSize(); i += 1) {
                            String propertyLandLordName = propertyList.getPropertyList().get(i).getLandlordName();
                            String propertyListAddress = propertyList.getPropertyList().get(i).getPropertyAddress();
                            String propertyListRentalPrice = propertyList.getPropertyList().get(i).getRentingPrice();
                            String propertyListUnitType = propertyList.getPropertyList().get(i).getUnitType();
                            boolean isCorrectLandlordName = propertyLandLordName.equals(landLordName);
                            boolean isCorrectAddress = propertyListAddress.equals(propertyAddress);
                            boolean isCorrectRental = propertyListRentalPrice.equals(rentalPrice);
                            boolean isCorrectUnitType = propertyListUnitType.equals(unitType);

                            boolean isCorrectProperty = isCorrectLandlordName && isCorrectAddress
                                    && isCorrectRental && isCorrectUnitType;
                            if (isCorrectProperty) {
                                pairingProperty = propertyList.getPropertyList().get(i);
                            }
                        }
                        pairingList.addPairing(pairingClient, pairingProperty);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUnitTypeLabel(String unitTypeString) {
        return unitTypeHashMap.get(unitTypeString);
    }

    public String getUnitTypeString(String unitTypeLabel) {
        for (String key : unitTypeHashMap.keySet()) {
            if (unitTypeHashMap.get(key).equals(unitTypeLabel)) {
                return key;
            }
        }
        return null;
    }
}
