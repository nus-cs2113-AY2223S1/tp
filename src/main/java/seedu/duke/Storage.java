package seedu.duke;

//@@author wilsonngja
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeParseException;
import seedu.duke.exception.storagexception.ClientFileNotFoundException;
import seedu.duke.exception.storagexception.InvalidPairingClientException;
import seedu.duke.exception.storagexception.InvalidPairingPropertyException;
import seedu.duke.exception.storagexception.PairFileNotFoundException;
import seedu.duke.exception.storagexception.PropertyFileNotFoundException;
import seedu.duke.exception.storagexception.StorageException;
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

import static seedu.duke.Messages.INVALID_CLIENT_FORMATTING;
import static seedu.duke.Messages.INVALID_CLIENT_ENTRIES;
import static seedu.duke.Messages.INVALID_PROPERTY_ENTRIES;
import static seedu.duke.Messages.INVALID_PROPERTY_FORMATTING;
import static seedu.duke.Messages.INVALID_PAIRING_FORMATTING;


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
    private static final String NEXT_LINE = System.lineSeparator();

    private static final Logger LOGGER = Logger.getLogger("Storage");

    private ClientList clientList;
    private PropertyList propertyList;
    private PairingList pairingList;
    private HashMap<String, String> unitTypeHashMap = new HashMap<>();
    private static final Ui UI = new Ui();

    private int errorClientEntriesCount = 0;
    private int errorClientFormatCount = 0;
    private int errorPropertyEntriesCount = 0;
    private int errorPropertyFormatCount = 0;
    private int errorPairingEntriesCount = 0;
    private int errorPairingFormatCount = 0;

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

        try {
            loadFiles(hasDirectory, hasPropertyFile, hasClientFile, hasPairingFile);
        } catch (StorageException e) {
            UI.showExceptionMessage(e);
        }

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
                          boolean hasPairingFile) throws ClientFileNotFoundException, PropertyFileNotFoundException,
            PairFileNotFoundException {
        if (!hasDirectory) {
            makeDirectory();
        }

        if (hasClientFile) {
            File clientFile = new File(CLIENT_PATH);
            assert clientFile.exists() : "Client text file does not exist";
            try {
                loadClient(clientFile);
            } catch (FileNotFoundException e) {
                throw new ClientFileNotFoundException();
            }
        }

        if (hasPropertyFile) {
            File propertyFile = new File(PROPERTY_PATH);
            assert propertyFile.exists() : "Property text file does not exist";
            try {
                loadProperty(propertyFile);
            } catch (FileNotFoundException e) {
                throw new PropertyFileNotFoundException();
            }
        }

        if (hasPairingFile) {
            File pairingFile = new File(PAIR_PATH);
            assert pairingFile.exists() : "Pairing text file does not exist";
            try {
                loadPair(pairingFile);
            } catch (FileNotFoundException e) {
                throw new PairFileNotFoundException();
            }
        }
    }

    /**
     * Adds the client list in the text file to the array list.
     *
     * @param clientFile The file that stores the list of client.
     */
    public void loadClient(File clientFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(clientFile);
        while (scanner.hasNext()) {
            String clientInput = scanner.nextLine();

            // Split into 4 separate entities to see if storage is in the right format
            String[] clientParameters = clientInput.split("\\s\\|\\s");
            if (clientParameters.length == 4) {
                String clientName = clientParameters[0];
                String clientContact = clientParameters[1];
                String clientEmail = clientParameters[2];
                String clientBudget = clientParameters[3].trim();

                String description = CLIENT_NAME_FLAG + clientName + CLIENT_CONTACT_FLAG + clientContact
                        + CLIENT_EMAIL_FLAG + clientEmail + CLIENT_BUDGET_FLAG + clientBudget;

                addClientEntries(description, clientName, clientContact, clientEmail, clientBudget);
            } else {
                // Storage is not in the right format, so it won't be added into the client list
                skipClientEntries();
            }

        }
        LOGGER.log(Level.INFO, LOG_CLIENT_LOAD_LABEL);

        updateClient();
    }

    /**
     * Loads the stored property file into the property array list.
     *
     * @param propertyFile The file that stores the list of property.
     */
    public void loadProperty(File propertyFile) throws FileNotFoundException {

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

                addPropertyEntries(description, landlordName, address, price, unitTypeLabel);

            } else {
                skipPropertyEntries();
            }
        }
        LOGGER.log(Level.INFO, LOG_PROPERTY_LOAD_LABEL);

        updateProperty();
    }

    /**
     * Loads the stored pair file into the pair hash map.
     *
     * @param pairFile The file that contains the pairing file.
     */
    public void loadPair(File pairFile) throws FileNotFoundException {

        // Update pairing hash map
        scanPairingFile(pairFile);

        LOGGER.log(Level.INFO, LOG_PAIRING_LOAD_LABEL);
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

            String pairText = getPairingFileText(clientPropertyPair);

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
    public void scanPairingFile(File pairFile) throws FileNotFoundException {
        Scanner scanner;


        scanner = new Scanner(pairFile);
        while (scanner.hasNext()) {
            String[] pairingParameters = scanner.nextLine().split("\\s\\:\\s");
            boolean hasSplitCorrectly = pairingParameters.length == 2;
            if (hasSplitCorrectly) {
                String[] clientParameters = pairingParameters[0].split("\\s\\|\\s");
                String[] propertyParameters = pairingParameters[1].split("\\s\\|\\s");
                boolean hasCorrectClientLength = clientParameters.length == 4;
                boolean hasCorrectPropertyLength = clientParameters.length == 4;
                boolean hasCorrectFormat = hasCorrectClientLength && hasCorrectPropertyLength;

                if (hasCorrectFormat) {
                    try {
                        Client pairingClient = getPairingClient(clientParameters);
                        Property pairingProperty = getPairingProperty(propertyParameters);

                        boolean isCorrectClient = pairingClient != null;
                        boolean isCorrectProperty = pairingProperty != null;

                        if (isCorrectProperty && isCorrectClient) {
                            pairingList.addPairing(pairingClient, pairingProperty);
                        }
                    } catch (DukeException e) {
                        UI.showExceptionMessage(e);
                    }
                } else {
                    skipPairingEntries();
                }
            } else {
                skipPairingEntries();
            }
        }
        updatePair();
    }

    /**
     * Exchanges the input unit type string for the unit type such that is in the right format to add into the
     * property.
     *
     * @param unitTypeString The text file format of the unit type of the property.
     * @return The unit type in the format that will be passed into the addition of property.
     */
    public String getUnitTypeLabel(String unitTypeString) {
        return unitTypeHashMap.get(unitTypeString);
    }

    /**
     * Outputs error message if it's the first error and ignore if it's already printed.
     */
    public void skipClientEntries() {
        errorClientEntriesCount += 1;
        if (errorClientEntriesCount == 1) {
            UI.showToUser(INVALID_CLIENT_FORMATTING);
        }
    }

    /**
     * Adds client into the client list and show error message if it's caught for the first time. Subsequent
     * errors will not generate an error message.
     *
     * @param description The full string text to be checked for validity
     * @param clientName The name of the client to be added into the client list.
     * @param clientContact The contact number of the client that will be added to the client list.
     * @param clientEmail The email of the client that will be added to the client list.
     * @param clientBudget The budget of the client.
     */
    public void addClientEntries(String description, String clientName, String clientContact, String clientEmail,
                                 String clientBudget) {

        try {
            checkClientValidity(description);
            clientList.addClient(clientName, clientContact, clientEmail, clientBudget);
        } catch (DukeParseException e) {
            errorClientFormatCount += 1;
            if (errorClientFormatCount == 1) {
                UI.showToUser(INVALID_CLIENT_ENTRIES);
            }
        }
    }

    /**
     * Checks the validity of the client.
     *
     * @param description The full string input that is used to check for validity.
     * @throws DukeParseException The exception in parsing that shows an error in the client.
     */
    public void checkClientValidity(String description) throws DukeParseException {
        ParseAddClient parser = new ParseAddClient(description, clientList);
        ArrayList<String> clientDetails = parser.processCommandAddClientDetails(description);
        parser.validateClientDetails(clientDetails);
    }

    /**
     * Outputs an error message when there is an error in adding property to property list and skip if it has
     * already been previously printed.
     */
    public void skipPropertyEntries() {
        errorPropertyEntriesCount += 1;
        if (errorPropertyEntriesCount == 1) {
            UI.showToUser(INVALID_PROPERTY_FORMATTING);
        }
    }

    /**
     * Outputs an error message when there is an error in adding pairing to the pairing list and skip if there has
     * already been previously printed.
     */
    public void skipPairingEntries() {
        errorPairingEntriesCount += 1;
        if (errorPairingEntriesCount == 1) {
            UI.showToUser(INVALID_PAIRING_FORMATTING);
        }
    }

    /**
     * Adds property to the property list and show error message if it's being caught for the first time. Subsequent
     * errors will not generate error message.
     *
     * @param description The string that will be used for checking of validity
     * @param landlordName The name of the landlord renting out the property.
     * @param address The address of the property being rented out.
     * @param price The rental rate of the property.
     * @param unitType The type of the property being rented.
     */
    public void addPropertyEntries(String description, String landlordName, String address,
                              String price, String unitType) {
        try {
            checkPropertyValidity(description);
            propertyList.addProperty(landlordName, address, price, unitType);
        } catch (DukeException e) {
            errorPropertyFormatCount += 1;
            if (errorPropertyFormatCount == 1) {
                UI.showToUser(INVALID_PROPERTY_ENTRIES);
            }
        }
    }

    /**
     * Checks the Validity of the property before adding property to the property list.
     *
     * @param description The string that will be used for checking of validity.
     * @throws DukeParseException The exception in parsing that shows an error in the property.
     */
    public void checkPropertyValidity(String description) throws DukeParseException {
        ParseAddProperty parser = new ParseAddProperty(description, propertyList);
        ArrayList<String> propertyDetails = parser.processCommandAddPropertyDetails(description);
        parser.validatePropertyDetails(propertyDetails);
    }

    /**
     * Generates the text of each pair.
     *
     * @param clientName The name of the client renting the property.
     * @param clientContact The contact number of the client.
     * @param clientEmail The email of the client.
     * @param clientBudget The budget of the client.
     * @param landlordName The name of the landlord renting out the property.
     * @param address The address of the property being rented out.
     * @param rentalRate The rental rate of the property.
     * @param unitType The unit type of the property being rented.
     * @return
     */
    public String getPairingText(String clientName, String clientContact, String clientEmail, String clientBudget,
                                 String landlordName, String address, String rentalRate, String unitType) {
        String clientFinalText = OPEN_BRACKET + clientName + SEPARATOR + clientContact + SEPARATOR
                + clientEmail + SEPARATOR + clientBudget + CLOSE_BRACKET;

        String propertyFinalText = OPEN_BRACKET + landlordName + SEPARATOR + address
                + SEPARATOR + rentalRate + SEPARATOR + unitType + CLOSE_BRACKET;

        String finalText = clientFinalText + COLON + propertyFinalText;

        return finalText;
    }

    /**
     * Generates the final text that will be written into pair.txt.
     *
     * @param clientPropertyPair The hashmap containing the pairings between the client and property.
     * @return The text that will be used to write to the text file.
     */
    public String getPairingFileText(HashMap<Client, Property> clientPropertyPair) {
        String pairText = "";
        for (Client clientText : clientPropertyPair.keySet()) {

            String clientName = clientText.getClientName();
            String clientContact = clientText.getClientContactNumber();
            String clientEmail = clientText.getClientEmail();
            String clientBudget = clientText.getClientBudgetPerMonth();

            String landlordName = clientPropertyPair.get(clientText).getLandlordName();
            String propertyAddress = clientPropertyPair.get(clientText).getPropertyAddress();
            String propertyPrice = clientPropertyPair.get(clientText).getRentingPrice();
            String propertyType = clientPropertyPair.get(clientText).getUnitType();

            String finalText = getPairingText(clientName, clientContact, clientEmail, clientBudget, landlordName,
                    propertyAddress, propertyPrice, propertyType) + NEXT_LINE;

            pairText = pairText.concat(finalText);
        }
        return pairText;
    }

    /**
     * Searches for Client in the client list that fits the parameters.
     *
     * @param clientParameters The parameters of one client in array format.
     * @return The Client that fits the inputted parameters.
     * @throws InvalidPairingClientException The exception that displays that the Client is not in the client list
     *              and will not be added to the pairing list.
     */
    public Client getPairingClient(String[] clientParameters) throws InvalidPairingClientException {
        String clientName = clientParameters[0].replace(OPEN_BRACKET, EMPTY_STRING);
        String clientContactNumber = clientParameters[1];
        String clientEmail = clientParameters[2];
        String clientBudget = clientParameters[3].replace(CLOSE_BRACKET, EMPTY_STRING);

        Client pairingClient = null;

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
                break;
            }
        }

        if (pairingClient == null) {
            throw new InvalidPairingClientException(clientName, clientContactNumber, clientEmail, clientBudget);
        }

        return pairingClient;
    }

    /**
     * Searches for Property in the property list that fits the parameters.
     *
     * @param propertyParameters The parameters of the property in array format.
     * @return The Property that fits the inputted parameters.
     * @throws InvalidPairingPropertyException The exception that displays that the Property is not in the property
     *              list and will not be added to the pairing list.
     */
    public Property getPairingProperty(String[] propertyParameters) throws InvalidPairingPropertyException {
        String landlordName = propertyParameters[0].replace(OPEN_BRACKET,EMPTY_STRING);
        String propertyAddress = propertyParameters[1];
        String rentalPrice = propertyParameters[2];
        String unitType = propertyParameters[3].replace(CLOSE_BRACKET,EMPTY_STRING);

        Property pairingProperty = null;

        for (int i = 0; i < propertyList.getCurrentListSize(); i += 1) {
            String propertyLandLordName = propertyList.getPropertyList().get(i).getLandlordName();
            String propertyListAddress = propertyList.getPropertyList().get(i).getPropertyAddress();
            String propertyListRentalPrice = propertyList.getPropertyList().get(i).getRentingPrice();
            String propertyListUnitType = propertyList.getPropertyList().get(i).getUnitType();
            boolean isCorrectLandlordName = propertyLandLordName.equals(landlordName);
            boolean isCorrectAddress = propertyListAddress.equals(propertyAddress);
            boolean isCorrectRental = propertyListRentalPrice.equals(rentalPrice);
            boolean isCorrectUnitType = propertyListUnitType.equals(unitType);

            boolean isCorrectProperty = isCorrectLandlordName && isCorrectAddress
                    && isCorrectRental && isCorrectUnitType;
            if (isCorrectProperty) {
                pairingProperty = propertyList.getPropertyList().get(i);
                break;
            }
        }

        if (pairingProperty == null) {
            throw new InvalidPairingPropertyException(landlordName, propertyAddress, rentalPrice, unitType);
        }
        return pairingProperty;
    }
}
    //@@author