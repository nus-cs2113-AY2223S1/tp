package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;


public class StorageTest {
    private static final String DIRECTORY = "./test_dir/";
    private static final String INCORRECT_FILE_PATH = "./test_dir2/test_file.txt";
    private static final String LOAD_FILE_CLIENT_FILE = "./load_test_client_file.txt";
    private static final String LOAD_FILE_PROPERTY_FILE = "./load_test_property_file.txt";
    private static final String LOAD_FILE_PAIR_FILE = "./load_test_pair_file.txt";
    private static final String DELETE_CLIENT_FILE = "./data/client.txt";
    private static final String DELETE_PROPERTY_FILE = "./data/property.txt";
    private static final String DELETE_PAIR_FILE = "./data/pair.txt";
    private static final String FILE_PATH = "./test_file.txt";
    private static final String SEPARATOR = " | ";
    private static final String LINE_BREAK = System.lineSeparator();
    private static final String OPEN_BRACKET = "[";
    private static final String COLON = " : ";
    private static final String CLOSE_BRACKET = "]";
    private static final String EMPTY_STRING = "";
    private static final int CLIENT_NAME_ARRAY_INDEX = 0;
    private static final int CLIENT_CONTACT_ARRAY_INDEX = 1;
    private static final int CLIENT_EMAIL_ARRAY_INDEX = 2;
    private static final int CLIENT_BUDGET_ARRAY_INDEX = 3;
    private static final int LANDLORD_NAME_INDEX = 0;
    private static final int PROPERTY_ADDRESS_INDEX = 1;
    private static final int PROPERTY_RENTAL_RATE_INDEX = 2;
    private static final int PROPERTY_UNIT_TYPE_INDEX = 3;

    @Test
    public void checkDirectory_noDirectory_expectCreation() {

        File dir = new File(DIRECTORY);
        boolean hasMadeFile =  dir.mkdir();
        assertTrue(dir.exists());
        // Delete the test directory after test is completed.
        boolean hasDelete = dir.delete();
    }

    @Test
    public void storeFile_noDirectory_expectThrow() {
        assertThrows(FileNotFoundException.class, () -> new FileWriter(INCORRECT_FILE_PATH));
    }

    @Test
    public void storeFile_fileExist_expectCorrectRead() {
        String name = "Bobby Tan";
        String contact = "91234567";
        String email = "abc123@example.com";
        String budget = "2000";
        String fileText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR + budget;

        // Store text into the text file
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Write operation failed.");
        }

        // Read the text file.
        File file = new File(FILE_PATH);
        try {
            Scanner input = new Scanner(file);
            if (input.hasNext()) {
                String inputText = input.nextLine();
                file.delete();
                assertEquals(fileText, inputText);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
    }

    @Test
    public void loadClient_wrongFormat_expectNoStorage() throws FileNotFoundException {

        String name = "Bobby Tan";
        String contact = "9123a5678";
        String email = "abc123@ex1ample.com";
        String budget = "1500a";

        String finalText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR + budget + LINE_BREAK;

        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();

        // To load incorrect contact data into text file to load.
        try {
            FileWriter fw = new FileWriter(LOAD_FILE_CLIENT_FILE);
            fw.write(finalText);
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }

        Storage storage = new Storage(clientList, propertyList, pairingList);

        // This file will be used to read
        File file = new File(LOAD_FILE_CLIENT_FILE);

        // After this point, after storing, everything that should be inside client list should be inside
        storage.loadClient(file);

        boolean hasClient = false;

        int clientListLength = clientList.getCurrentListSize();

        for (int i = 0; i < clientListLength; i += 1) {
            String clientListName = clientList.getClientList().get(i).getClientName();
            String clientListContact = clientList.getClientList().get(i).getClientContactNumber();
            String clientListEmail = clientList.getClientList().get(i).getClientEmail();
            String clientListBudget = clientList.getClientList().get(i).getClientBudgetPerMonth();

            boolean hasClientName = clientListName.equals(name);
            boolean hasClientContact = clientListContact.equals(contact);
            boolean hasClientEmail = clientListEmail.equals(email);
            boolean hasClientBudget = clientListBudget.equals(budget);

            hasClient = hasClientName && hasClientContact && hasClientEmail && hasClientBudget;
            if (hasClient) {
                break;
            }
        }

        assertFalse(hasClient);
    }

    @Test
    public void loadProperty_wrongFormat_expectNoStorage() throws FileNotFoundException {
        String landlordName = "Pikachu Pi Kar Kiu";
        String address = "25 Lower Kent Ridge Rd, Singapore 119081";
        String rentalPrice = "12a";
        String unitType = "HDB 3";

        String finalText = landlordName + SEPARATOR + address + SEPARATOR + rentalPrice + SEPARATOR + unitType
                + SEPARATOR;

        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();

        // To load incorrect contact data into text file to load.
        try {
            FileWriter fw = new FileWriter(LOAD_FILE_PROPERTY_FILE);
            fw.write(finalText);
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }

        Storage storage = new Storage(clientList, propertyList, pairingList);

        // This file will be used to read
        File file = new File(LOAD_FILE_PROPERTY_FILE);

        storage.loadProperty(file);

        boolean hasProperty = false;

        int propertyListLength = propertyList.getCurrentListSize();

        for (int i = 0; i < propertyListLength; i += 1) {
            String propertyListLandlordName = propertyList.getPropertyList().get(i).getLandlordName();
            String propertyListAddress = propertyList.getPropertyList().get(i).getPropertyAddress();
            String propertyListRentalRate = propertyList.getPropertyList().get(i).getRentingPrice();
            String propertyListUnitType = propertyList.getPropertyList().get(i).getUnitType();

            boolean hasLandlordName = propertyListLandlordName.equals(landlordName);
            boolean hasPropertyAddress = propertyListAddress.equals(address);
            boolean hasPropertyRentalRate = propertyListRentalRate.equals(rentalPrice);
            boolean hasPropertyUnitType = propertyListUnitType.equals(unitType);

            hasProperty = hasLandlordName && hasPropertyAddress && hasPropertyRentalRate && hasPropertyUnitType;
            if (hasProperty) {
                break;
            }
        }

        assertFalse(hasProperty);
    }

    @Test
    public void loadPair_wrongClientFormat_expectNoStorage() throws FileNotFoundException {
        String name = "Bobby Tan";
        String contact = "9123a5678";
        String email = "abc123@ex1ample.com";
        String budget = "1500a";
        String landlordName = "Pikachu Pi Kar Kiu";
        String address = "25 Lower Kent Ridge Rd, Singapore 119081";
        String rentalPrice = "1200";
        String unitType = "HDB 3-Room";

        String clientPairText = OPEN_BRACKET + name + SEPARATOR + contact + SEPARATOR
                + email + SEPARATOR + budget + CLOSE_BRACKET;

        String propertyPairText = OPEN_BRACKET + landlordName + SEPARATOR +
                address + SEPARATOR + rentalPrice + SEPARATOR + unitType + CLOSE_BRACKET;

        String pairingText = clientPairText + COLON + propertyPairText;


        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();
        Storage storage = new Storage(clientList, propertyList, pairingList);

        try {
            FileWriter fw = new FileWriter(LOAD_FILE_PAIR_FILE);
            fw.write(pairingText);
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }

        File file = new File(LOAD_FILE_PAIR_FILE);

        storage.loadPair(file);

        boolean hasClient = false;
        boolean hasProperty = false;

        for (Client c : pairingList.getClientPropertyPairs().keySet()) {
            String clientName = c.getClientName();
            String clientContact = c.getClientContactNumber();
            String clientEmail = c.getClientEmail();
            String clientBudget = c.getClientBudgetPerMonth();
            boolean hasClientName = clientName.equals(name);
            boolean hasClientContact = clientContact.equals(contact);
            boolean hasClientEmail = clientEmail.equals(email);
            boolean hasClientBudget = clientBudget.equals(budget);

            hasClient = hasClientName && hasClientContact && hasClientEmail && hasClientBudget;

            String propertyLandlordName = pairingList.getClientPropertyPairs().get(c).getLandlordName();
            String propertyAddress = pairingList.getClientPropertyPairs().get(c).getPropertyAddress();
            String propertyRentalPrice = pairingList.getClientPropertyPairs().get(c).getRentingPrice();
            String propertyUnitType = pairingList.getClientPropertyPairs().get(c).getUnitType();
            boolean hasLandlordName = propertyLandlordName.equals(landlordName);
            boolean hasPropertyAddress = propertyAddress.equals(address);
            boolean hasPropertyRentalPrice = propertyRentalPrice.equals(rentalPrice);
            boolean hasPropertyUnitType = propertyUnitType.equals(unitType);

            hasProperty = hasLandlordName && hasPropertyAddress && hasPropertyRentalPrice && hasPropertyUnitType;

            if (hasClient && hasProperty) {
                break;
            }
        }

        boolean isStored = hasClient && hasProperty;
        assertFalse(isStored);
    }

    @Test
    public void deleteClient_correctClientIndex_notInStorage() {
        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();

        Storage storage = new Storage(clientList, propertyList, pairingList);

        File loadFile = new File(DELETE_CLIENT_FILE);
        try {
            storage.loadClient(loadFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String name = "Pikachu Pi ";
        String contact = "91235679 ";
        String email = "abcd1234@example.com ";
        String budget = "1500";

        clientList.addClient(name, contact, email, budget);

        // Since the client is added in last, it should be in the last of the list
        int lastClientIndex = clientList.getCurrentListSize() - 1;
        clientList.deleteClient(lastClientIndex);
        storage.updateClient();

        File checkFile = new File(DELETE_CLIENT_FILE);

        boolean hasClient = true;
        try {
            Scanner scan = new Scanner(checkFile);
            while (scan.hasNext()) {
                String readText = scan.nextLine();
                String[] textEntities = readText.split("\\s\\|\\s");

                if (textEntities.length == 4) {
                    boolean hasClientName = textEntities[CLIENT_NAME_ARRAY_INDEX].equals(name);
                    boolean hasClientContact = textEntities[CLIENT_CONTACT_ARRAY_INDEX].equals(contact);
                    boolean hasClientEmail = textEntities[CLIENT_EMAIL_ARRAY_INDEX].equals(email);
                    boolean hasClientBudget = textEntities[CLIENT_BUDGET_ARRAY_INDEX].equals(budget);

                    hasClient = hasClientName && hasClientContact && hasClientEmail && hasClientBudget;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        assertFalse(hasClient);
    }

    @Test
    public void deleteProperty_correctPropertyIndex_notInStorage() {
        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();

        Storage storage = new Storage(clientList, propertyList, pairingList);
        File loadFile = new File(DELETE_PROPERTY_FILE);
        try {
            storage.loadClient(loadFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String landlordName = "Bobby Tay";
        String address = "25 Lower Kent Ridge Rd, Singapore 119081";
        String rentalRate = "850";
        String unitType = "Penthouse";


        propertyList.addProperty(landlordName, address, rentalRate, unitType);

        int lastPropertyIndex = propertyList.getPropertyList().size() - 1;
        propertyList.deleteProperty(lastPropertyIndex);
        storage.updateProperty();

        File checkFile = new File(DELETE_PROPERTY_FILE);
        boolean hasProperty = true;

        try {
            Scanner scan = new Scanner(checkFile);
            while (scan.hasNext()) {
                String readText = scan.nextLine();
                String[] textEntities = readText.split("\\s\\|\\s");

                if (textEntities.length == 4) {
                    boolean hasLandlordName = textEntities[LANDLORD_NAME_INDEX].equals(landlordName);
                    boolean hasAddress = textEntities[PROPERTY_ADDRESS_INDEX].equals(address);
                    boolean hasRentalRate = textEntities[PROPERTY_RENTAL_RATE_INDEX].equals(rentalRate);
                    boolean hasUnitType = textEntities[PROPERTY_UNIT_TYPE_INDEX].equals(unitType);

                    hasProperty = hasLandlordName && hasAddress && hasRentalRate && hasUnitType;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        assertFalse(hasProperty);
    }

    @Test
    public void unpair_correctClientAndPropertyIndex_removeFromStorage() throws FileNotFoundException {
        ClientList clientList = new ClientList();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();
        Storage storage = new Storage(clientList, propertyList, pairingList);


        loadClientAndPropertyForPairing(clientList, propertyList, storage);


        //pair both the last property
        int lastClientIndex = clientList.getCurrentListSize() - 1;
        int lastPropertyIndex = propertyList.getCurrentListSize() - 1;

        Client client = clientList.getClientList().get(lastClientIndex);
        Property property = propertyList.getPropertyList().get(lastPropertyIndex);

        pairingList.addPairing(client, property);
        storage.updatePair();

        File pairFile = new File(DELETE_PAIR_FILE);


        // At this point, it stored inside the file already
        pairingList.deletePairing(client, property);
        storage.updatePair();

        boolean hasPair = hasRemovedFromPairingFile(pairFile);
        assertFalse(hasPair);
    }

    public void loadClientAndPropertyForPairing(ClientList clientList, PropertyList propertyList, Storage storage) {
        // Loading Client File
        File loadFile = new File(DELETE_CLIENT_FILE);
        try {
            storage.loadClient(loadFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String name = "Pikachu Pi Kah Chiu";
        String contact = "81234567";
        String email = "abc123@example.com";
        String budget = "2500";

        clientList.addClient(name, contact, email, budget);

        // Loading Property File
        loadFile = new File(DELETE_PROPERTY_FILE);
        try {
            storage.loadProperty(loadFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String landlordName = "Bobby Tay";
        String address = "25 Lower Kent Ridge Rd, Singapore 119081";
        String rentalRate = "850";
        String unitType = "Penthouse";

        propertyList.addProperty(landlordName, address, rentalRate, unitType);
    }

    public boolean hasRemovedFromPairingFile(File pairFile) throws FileNotFoundException {
        Scanner scan = new Scanner(pairFile);
        boolean hasClient = false;
        boolean hasProperty = false;

        while (scan.hasNext()) {
            String readText = scan.nextLine();
            String[] eachEntities = readText.split("\\s\\:\\s");

            if (eachEntities.length == 2) {
                String clientEntity = eachEntities[0];
                String propertyEntity = eachEntities[1];

                String[] clientEntities = clientEntity.split("\\s\\|\\s");
                String[] propertyEntities = propertyEntity.split("\\s\\|\\s");
                if (clientEntities.length == 4 && propertyEntities.length == 4) {
                    hasClient = hasClientInPairingFile(clientEntities);
                    hasProperty = hasPropertyInPairingFile(propertyEntities);
                }
            }
        }

        return hasClient && hasProperty;
    }

    public boolean hasClientInPairingFile(String[] clientEntities) {
        String clientName = clientEntities[CLIENT_NAME_ARRAY_INDEX].replace(OPEN_BRACKET, EMPTY_STRING);
        String clientContact = clientEntities[CLIENT_CONTACT_ARRAY_INDEX];
        String clientEmail = clientEntities[CLIENT_EMAIL_ARRAY_INDEX];
        String clientBudget = clientEntities[CLIENT_BUDGET_ARRAY_INDEX].replace(CLOSE_BRACKET, EMPTY_STRING);

        String name = "Pikachu Pi Kah Chiu";
        String contact = "81234567";
        String email = "abc123@example.com";
        String budget = "2500";

        boolean hasClientName = clientName.equals(name);
        boolean hasClientContact = clientContact.equals(contact);
        boolean hasClientEmail = clientEmail.equals(email);
        boolean hasClientBudget=  clientBudget.equals(budget);
        boolean hasClient = hasClientName && hasClientContact && hasClientEmail && hasClientBudget;

        return hasClient;
    }

    public boolean hasPropertyInPairingFile(String[] propertyEntities) {
        String propertyLandlordName = propertyEntities[LANDLORD_NAME_INDEX].replace(OPEN_BRACKET, EMPTY_STRING);
        String propertyAddress = propertyEntities[PROPERTY_ADDRESS_INDEX];
        String propertyRentalPrice = propertyEntities[PROPERTY_RENTAL_RATE_INDEX];
        String propertyUnitType = propertyEntities[PROPERTY_UNIT_TYPE_INDEX];

        String landlordName = "Bobby Tay";
        String address = "25 Lower Kent Ridge Rd, Singapore 119081";
        String rentalRate = "850";
        String unitType = "Penthouse";


        boolean hasLandlordName = propertyLandlordName.equals(landlordName);
        boolean hasPropertyAddress = propertyAddress.equals(address);
        boolean hasPropertyRentalRate = propertyRentalPrice.equals(rentalRate);
        boolean hasPropertyUnitType = propertyUnitType.equals(unitType);
        boolean hasProperty = hasLandlordName && hasPropertyAddress && hasPropertyRentalRate
                && hasPropertyUnitType;

        return hasProperty;
    }
}
