package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class CheckFileCreationTest {
    private static final String DIRECTORY = "./test_dir/";
    private static final String INCORRECT_FILE_PATH = "./test_dir2/test_file.txt";
    private static final String LOAD_FILE_CLIENT_FILE = "./load_test_client_file.txt";
    private static final String LOAD_FILE_PROPERTY_FILE = "./load_test_property_file.txt";
    private static final String FILE_PATH = "./test_file.txt";
    private static final String SEPARATOR = " | ";
    private static final String LINE_BREAK = System.lineSeparator();


    @Test
    void checkDirectory_noDirectory_expectCreation() {

        File dir = new File(DIRECTORY);
        dir.mkdir();
        assertTrue(dir.exists());
        // Delete the test directory after test is completed.
        dir.delete();
    }

    @Test
    void storeFile_noDirectory_expectThrow() {
        assertThrows(FileNotFoundException.class, () -> new FileWriter(INCORRECT_FILE_PATH));
    }

    @Test
    void storeFile_fileExist_expectCorrectRead() {
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
    void loadClient_wrongFormat_expectNoStorage() throws FileNotFoundException {

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
        }

        assertFalse(hasClient);
    }

    @Test
    void loadProperty_wrongFormat_expectNoStorage() throws FileNotFoundException {
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
        }

        assertFalse(hasProperty);



    }

}
