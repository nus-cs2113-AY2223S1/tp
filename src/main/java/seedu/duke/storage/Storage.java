package seedu.duke.storage;

import seedu.duke.operationlist.OperationList;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the methods used to manage the flight list and passenger list data in the file
 */
public class Storage {

    protected static final String PATHNAME = "./data/SkyControl.txt";
    private static final int FLIGHT_INPUT_LENGTH = 6;
    private static final int PASSENGER_INPUT_LENGTH = 7;

    /**
     * Loads the relevant flight information from the data file into the application
     *
     * @param flightList the OperationList which contains an ArrayList to store the list of flights
     *                   for the read data to be inserted into
     * @throws FileNotFoundException if the file to read from is not found
     */
    public void loadFlights(OperationList flightList) throws FileNotFoundException {
        ArrayList<FlightInfo> flights = new ArrayList<>();
        File f = new File(PATHNAME);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputFromFile = s.nextLine();
            assert !inputFromFile.isEmpty();
            boolean isFlight = filterData(inputFromFile);
            if (isFlight) {
                String[] details = inputFromFile.split("\\|");
                FlightInfo flight;
                String flightNumber = details[0];
                String airline = details[1];
                String destination = details[2];
                String departureTime = details[3];
                String gateNumber = details[4];
                String checkInRowDoor = details[5];
                flight = new FlightInfo(flightNumber, airline, destination, departureTime, gateNumber, checkInRowDoor);
                flights.add(flight);
            }
        }
        flightList.insertFlights(flights);
    }

    /**
     * Loads the relevant passenger information read from the data file into the application
     *
     * @param passengerList the OperationList containing an ArrayList which stores the list of passengers
     *                      for the read data to be inserted into
     * @throws FileNotFoundException if the file to read from is not found
     */
    public void loadPassengers(OperationList passengerList) throws FileNotFoundException {
        ArrayList<PassengerInfo> passengers = new ArrayList<>();
        File f = new File(PATHNAME);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputFromFile = s.nextLine();
            assert !inputFromFile.isEmpty();
            boolean isFlight = filterData(inputFromFile);
            if (!isFlight) {
                String[] details = inputFromFile.split("\\|");
                PassengerInfo passenger;
                String passengerName = details[0];
                String departureTime = details[1];
                String flightNumber = details[2];
                String gateNumber = details[3];
                String boardingGroup = details[4];
                String seatNumber = details[5];
                String boardingTime = details[6];
                passenger = new PassengerInfo(passengerName, departureTime, flightNumber, gateNumber,
                        Integer.parseInt(boardingGroup), seatNumber, boardingTime);
                passengers.add(passenger);
            }
            passengerList.insertPassengers(passengers);
        }
    }

    /**
     * Checks the status of the data file and directory, and tries to create the file and/or directory
     * if it does not exist
     */
    public static void checkFileStatus() {
        File tempFile = new File(PATHNAME);
        boolean isFileExists = tempFile.exists();
        Ui ui = new Ui();
        if (!isFileExists) {
            ui.showFileDoesNotExistMessage();
        }
        try {
            ui.showFileCreationMessage();
            File file = new File(PATHNAME);
            if (!file.getParentFile().mkdirs()) {
                ui.showDirectoryCreationErrorMessage();
            }
            if (file.createNewFile()) {
                ui.showFileCreatedMessage(PATHNAME);
            } else {
                ui.showFileExistsMessage(PATHNAME);
            }
        } catch (IOException ioException) {
            ui.showFileCannotCreateMessage();
            ioException.printStackTrace();
        }
    }

    /**
     * Inserts the flight information into the data file
     *
     * @param flights the ArrayList containing the list of flights to be inserted into the data file
     * @throws IOException if the data is not inserted properly into the file
     */
    private void insertFlightsIntoFile(ArrayList<FlightInfo> flights) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME);
        for (FlightInfo flight : flights) {
            fw.write(flight.convertToFileFormat());
        }
        fw.close();
    }

    /**
     * Inserts the passenger information into the data file
     *
     * @param passengers the ArrayList containing the list of passengers to be inserted into the data file
     * @throws IOException
     */
    private void insertPassengersIntoFile(ArrayList<PassengerInfo> passengers) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true);
        for (PassengerInfo passenger : passengers) {
            fw.write(passenger.convertToFileFormat());
        }
        fw.close();
    }

    public void insertIntoFile(ArrayList<FlightInfo> flights, ArrayList<PassengerInfo> passengers) throws IOException {
        insertFlightsIntoFile(flights);
        insertPassengersIntoFile(passengers);
    }

    /**
     * Used to separate the flight data from the passenger data which are both inserted into the same data file
     *
     * @param inputFromFile the line of data read from the data file
     * @return true if the line of data from the data file is a flight, returns false otherwise
     */
    private boolean filterData(String inputFromFile) {
        boolean isFlight = true;
        String[] details = inputFromFile.split("\\|");
        assert details.length > 0;
        if (details.length == FLIGHT_INPUT_LENGTH) {
            isFlight = true;
        }
        if (details.length == PASSENGER_INPUT_LENGTH) {
            isFlight = false;
        }
        return isFlight;
    }
}

