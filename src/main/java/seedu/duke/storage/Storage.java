package seedu.duke.storage;

import seedu.duke.operationlist.OperationList;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected static final String PATHNAME = "./data/SkyControl.txt";
    private static final int FLIGHT_INPUT_LENGTH = 6;
    private static final int PASSENGER_INPUT_LENGTH = 7;

    public void loadFlights(OperationList flightList) throws FileNotFoundException {
        ArrayList<FlightInfo> flights = new ArrayList<>();
        File f = new File(PATHNAME);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputFromFile = s.nextLine();
            boolean isFlight = filterData(inputFromFile);
            if (isFlight) {
                String[] details = inputFromFile.split("\\|");
                FlightInfo flight;
                String flightNumber =details[0];
                String airline = details[1];
                String destination = details[2];
                String departureTime = details[3];
                String gateNumber = details[4];
                String checkInRowDoor =  details[5];
                flight = new FlightInfo(flightNumber,airline,destination,departureTime,gateNumber,checkInRowDoor);
                flights.add(flight);
            }
        }
        flightList.insertFlights(flights);
    }

    public void loadPassengers(OperationList passengerList) throws FileNotFoundException {
        ArrayList<PassengerInfo> passengers = new ArrayList<>();
        File f = new File(PATHNAME);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputFromFile = s.nextLine();
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
                passenger = new PassengerInfo(passengerName, departureTime,flightNumber,gateNumber,
                        Integer.parseInt(boardingGroup), seatNumber,boardingTime);
                passengers.add(passenger);
            }
            passengerList.insertPassengers(passengers);
        }
    }

    public static void checkFileStatus() {
        File tempFile = new File(PATHNAME);
        boolean isFileExists = tempFile.exists();
        if (!isFileExists) {
            System.out.println("File or directory does not exist.\n");
        }
        try {
            System.out.println("Trying to create file now.");
            File file = new File(PATHNAME);
            if (!file.getParentFile().mkdirs()) {
                System.out.println("Directory could not be created");
            }
            if (file.createNewFile()) {
                System.out.println("File is created at: " + PATHNAME);
            } else {
                System.out.println("File already exists at: " + PATHNAME);
            }
        } catch (IOException ioException) {
            System.out.println("File could not be created");
            ioException.printStackTrace();
        }
    }

    private void insertFlightsIntoFile(ArrayList<FlightInfo> flights) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME);
        for (FlightInfo flight : flights) {
            fw.write(flight.convertToFileFormat());
        }
        fw.close();
    }

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

    private boolean filterData(String inputFromFile) {
        boolean isFlight = true;
        String[] details = inputFromFile.split("\\|");
        if (details.length == FLIGHT_INPUT_LENGTH) {
            isFlight = true;
        }
        if (details.length == PASSENGER_INPUT_LENGTH) {
            isFlight = false;
        }
        return isFlight;
    }
}

