package seedu.duke.ui;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected Scanner in = new Scanner(System.in);
    protected static final int EMPTY_FLIGHT_LIST = 0;
    protected static final int FORMAT_NAME_SPACE = 24;
    protected static final int FORMAT_DOD_SPACE = 14;
    protected static final int FORMAT_DT_SPACE = 14;
    protected static final int FORMAT_FN_SPACE = 10;
    protected static final int FORMAT_GN_SPACE = 8;
    protected static final int FORMAT_BG_SPACE = 12;
    protected static final int FORMAT_SN_SPACE = 8;
    protected static final int FORMAT_BT_SPACE = 13;
    protected static final int BOARDING_GROUP_LIMIT = 5;
    protected static String formattedName;
    protected static String formattedDepartureDate;
    protected static String formattedDepartureTime;
    protected static String formattedFlightNumber;
    protected static String formattedGateNumber;
    protected static String formattedBoardingGroup;
    protected static String formattedSeatNumber;
    protected static String formattedBoardingTime;

    protected String lineSeparator;
    protected static final String LOGO = "   _____ _             _____            _             _\n"
            + "  / ____| |           / ____|          | |           | |\n"
            + " | (___ | | ___   _  | |     ___  _ __ | |_ _ __ ___ | |\n"
            + "  \\___ \\| |/ / | | | | |    / _ \\| '_ \\| __| '__/ _ \\| |\n"
            + "  ____) |   <| |_| | | |___| (_) | | | | |_| | | (_) | |\n"
            + " |_____/|_|\\_\\\\__, |  \\_____\\___/|_| |_|\\__|_|  \\___/|_|\n"
            + "               __/ |\n"
            + "              |___/\n";

    public Ui() {
        this.lineSeparator = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    }

    public void showLineSeparator() {
        System.out.println(lineSeparator);
    }

    //@@author shengiv
    public void showWelcomeMessage() {
        System.out.println("Welcome to\n" + LOGO);
    }

    //@@author ivanthengwr
    public String readCommand() {
        String lineInput;
        lineInput = in.nextLine();
        return lineInput;
    }

    //@@author shengiv
    public static void showAddedPassenger(PassengerInfo passenger) {
        System.out.println("Passenger " + passenger.getName() + " of "
                + passenger.getFlightNumber() + " " + passenger.getSeatNumber() + " has been added."
        );
    }

    //@@author ivanthengwr
    public static void showDeleteMessage(String name, String flightNumber, String seatNumber, int numOfPassenger) {
        System.out.println("Passenger " + name + " from "
                + flightNumber + " of seat number " + seatNumber
                + " have been\ndeleted from the passenger list.");
        System.out.println(numOfPassenger + " passenger(s) left on the passenger list.\n");
    }

    public String getDeleteError() {
        return "The system is unable to delete the specified passenger "
                + "\nas he/she is not found in the passenger list "
                + "or his/her \ndetail have been input incorrectly.";
    }

    //@@author shengiv
    public String getDuplicatePassengerError() {
        return "Unable to add Passenger. Seat number is already occupied on the flight!";
    }

    //@@author Franky4566
    public String getDuplicateFlightError() {
        return "Unable to add Flight! Flight already exist.";
    }

    public String getGateOccupiedError() {
        return "Unable to add Flight!\n"
                + "Designated gate is already occupied at that time, please select a different gate number.";
    }

    //@@author ivanthengwr
    public String getOperationError() {
        return "Input a valid operation, please try again.";
    }

    public String getBlankOpsError() {
        return "Detail of the operation is blank,\n"
                + "please try again and input all necessary details.";
    }

    //@@author shengiv
    public String getErrorMessage() {
        return "The system is unable to read your command, please try again.";
    }

    //@@author ivanthengwr
    public String getBlankOperationError() {
        return "You did not input an operation.\n"
                + "The system is unable to read your command, please try again.";
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showByeMessage() {
        System.out.println("Thank you, come again! :)");
    }

    public void showPassengerListHeader() {
        System.out.print("\n+---------------------------------------------------------"
                + "---------------------------------------------------------------------+\n"
                + "|                                                  PASSENGER DETAILS LOGBOOK"
                + "                                                   |\n"
                + "+----------------------------------------------------------------"
                + "--------------------------------------------------------------+\n"
                + "|           NAME           | DEPARTURE DATE | DEPARTURE TIME | FLIGHT NUM |"
                + " GATE NUM | BOARDING GRP | SEAT NUM | BOARDING TIME |\n"
                + "+----------------------------------------------------------------"
                + "--------------------------------------------------------------+\n");
    }

    //@@author Franky4566
    public void showFlightListHeader() {
        System.out.print("\n+--------------------------------------------------------------"
                + "--------------------------------------------------------------------+\n"
                + "|                                                  FLIGHT DETAILS LOGBOOK FOR TERMINAL 1"
                + "                                         |\n"
                + "+-----------------------------------------------------------"
                + "-----------------------------------------------------------------------+\n"
                + "| FLIGHT NUM | DEPARTURE DATE |        AIRLINE         |      DESTINATION      | DEPARTURE TIME |"
                + " GATE NUM |  CHECK-IN ROW/DOOR  |\n"
                + "+---------------------------------------------------------"
                + "-------------------------------------------------------------------------+\n");
    }

    //@@author ivanthengwr
    public void showPassengerListBody(String name, String departureDate, String departureTime,
                                      String flightNumber, String gateNumber, int boardingGroup,
                                      String seatNumber, String boardingTime) {
        formatPassengerDetail(name, departureDate, departureTime,
                flightNumber, gateNumber, boardingGroup, seatNumber, boardingTime);
        showFormattedDetail();
    }

    private static void showFormattedDetail() {
        System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s |\n"
                        + "+---------------------------------------------------------------"
                        + "---------------------------------------------------------------+\n",
                formattedName, formattedDepartureDate, formattedDepartureTime, formattedFlightNumber,
                formattedGateNumber, formattedBoardingGroup, formattedSeatNumber, formattedBoardingTime);
    }

    private static void formatPassengerDetail(String name, String departureDate, String departureTime,
                                              String flightNumber, String gateNumber, int boardingGroup,
                                              String seatNumber, String boardingTime) {
        checkAsserts(name, departureDate, departureTime, flightNumber,
                gateNumber, boardingGroup, seatNumber, boardingTime);
        formattedName = String.format("%-" + FORMAT_NAME_SPACE + "s", name);
        formattedDepartureDate = String.format("%-" + FORMAT_DOD_SPACE + "s", departureDate);
        formattedDepartureTime = String.format("%-" + FORMAT_DT_SPACE + "s", departureTime);
        formattedFlightNumber = String.format("%-" + FORMAT_FN_SPACE + "s", flightNumber);
        formattedGateNumber = String.format("%-" + FORMAT_GN_SPACE + "s", gateNumber);
        formattedBoardingGroup = String.format("%-" + FORMAT_BG_SPACE + "s", boardingGroup);
        formattedSeatNumber = String.format("%-" + FORMAT_SN_SPACE + "s", seatNumber);
        formattedBoardingTime = String.format("%-" + FORMAT_BT_SPACE + "s", boardingTime);
    }

    private static void checkAsserts(String name, String departureDate, String departureTime,
                                     String flightNumber, String gateNumber,
                                     int boardingGroup, String seatNumber, String boardingTime) {
        assert name.length() <= FORMAT_NAME_SPACE;
        assert departureDate.length() <= FORMAT_DOD_SPACE;
        assert departureTime.length() <= FORMAT_DT_SPACE;
        assert flightNumber.length() <= FORMAT_FN_SPACE;
        assert gateNumber.length() <= FORMAT_GN_SPACE;
        assert boardingGroup <= BOARDING_GROUP_LIMIT;
        assert seatNumber.length() <= FORMAT_SN_SPACE;
        assert boardingTime.length() <= FORMAT_BT_SPACE;
    }

    public void showEmptyPassengerList() {
        System.out.printf("| %80s %-43s |\n"
                        + "+----------------------------------------------------------------"
                        + "--------------------------------------------------------------+\n",
                "The passenger details logbook is empty.", " ");
    }

    //@@author Franky4566
    public void showEmptyFlightList() {
        System.out.printf("| %80s %-45s |\n"
                        + "+--------------------------------------------------------------------"
                        + "--------------------------------------------------------------+\n",
                "The Flight schedule is empty.", " ");
    }

    public String getFlightNotFoundMessage(String flightNum) {
        return "FLIGHT " + flightNum + " NOT FOUND.";
    }

    public String showEmptyDescriptionMessage() {
        return "oops! The description is empty :(";
    }

    public void showFlightAddedMessage() {
        System.out.println("Flight added!");
    }

    //@@author JordanKwua
    //@@author JordanKwua
    public void showFlightRemovedMessage(String flightNum) {
        System.out.println("FLIGHT " + flightNum + " HAS BEEN DELETED.");
    }

    public void showListOfFlights(ArrayList<FlightInfo> flightList) {
        showFlightListHeader();
        checkEmptyFlightList(flightList);
        for (FlightInfo flight : flightList) {
            System.out.println("\n+-----------------------------------------------------------"
                    + "-----------------------------------------------------------------------+"
                    + flight);
        }
    }

    public void checkEmptyFlightList(ArrayList<FlightInfo> flightList) {
        int numOfFlights = flightList.size();
        if (numOfFlights == EMPTY_FLIGHT_LIST) {
            showEmptyFlightList();
        }
    }

    //@@author ivanthengwr
    public String getLoggerError() {
        return "File logger failed to work.";
    }

    public String getLoggerStartUpMessage() {
        return "Going to start processing";
    }

    //@@author JordanKwua
    public String getWrongFlightFormatErrorMessage() {
        return "Flight number should start with 2 letters and trail with 1-4 numbers.";
    }

    //@@author shengiv
    public String getMissingDetailsError() {
        return "Please fill up details for all fields.\n Invalid input. Try again!";
    }

    public void showUpdatedFlightNumber(String flightNum, String newFlightNum) {
        System.out.println("Flight number of flight " + flightNum + " is updated to " + newFlightNum + ".");
    }

    public void showUpdatedGateNumber(String flightNum, String newGateNum) {
        System.out.println("Gate number of flight " + flightNum + " is updated to " + newGateNum + ".");
    }

    //@@author Franky4566
    public void showUpdatedDepartureTime(String flightNum, String oldDepartureTime, String newDepartureTime) {
        System.out.println("Departure time of flight " + flightNum + " is delayed from "
                + oldDepartureTime + " to " + newDepartureTime + ".");
    }

    public String getWrongDelayTimeError(String flightNum, String oldDepartureTime) {
        return "Stop! Please enter a valid departure time for flight " + flightNum
                + "\nTime must be later than " + oldDepartureTime + ".";
    }

    //@@author ivanthengwr
    public String getExceedNameLengthError(String name) {
        return "Stop! The input name with " + name.length()
                + " characters is too long!\n"
                + "Please input a name that is within 24 characters.";
    }

    //@@author Franky4566
    public String getExceedAirlineLengthError(String airline) {
        return "Stop! The airline input with " + airline.length()
                + " characters is too long!\n"
                + "Please keep your input to 22 characters.";
    }

    public String getExceedDestinationLengthError(String destination) {
        return "Stop! The destination input with " + destination.length()
                + " characters is too long!\n"
                + "Please keep your input to 22 characters.";
    }

    //@@author ivanthengwr
    public String getNameError() {
        return "Stop! The input name cannot have any special characters or numbers.\n"
                + "Please try again.";
    }

    public String getDepartureTimeError() {
        return "Stop! The departure time input format is wrong.\n"
                + "Please try again in 24Hr time format.";
    }

    //@@author Franky4566
    public String getCheckInFormatError() {
        return "Stop! The check in row/door format is wrong.\n"
                + "Please try again using rr-dd format";
    }

    //@@author ivanthengwr
    public String getFlightNumberError() {
        return "Stop! The flight number input format is wrong.\n"
                + "Please try again with the following format:\n"
                + "'SQ12' - For international flights\n"
                + "'SQ123' - For regional flights\n"
                + "'SQ1234' - For domestic flights";
    }

    public String getBoardingTimeError() {
        return "Stop! The boarding time input format is wrong.\n"
                + "Please try again in 24Hr time format.";
    }

    public String getGateNumberError() {
        return "Stop! The gate number input format is wrong.\n"
                + "Gate number should be a digit between 0 and 99, please try again.";
    }

    public String getBoardingGroupError() {
        return "Stop! The boarding group input format is wrong.\n"
                + "Boarding group should not be more than 10 and should be in digit form,\n"
                + "please try again.";
    }

    public String getSeatNumberError() {
        return "Stop! The Seat Number input format is wrong.\n"
                + "Seat number should range between 00A to 99Z,\n"
                + "please try again.";
    }

    public String getFlightNumberSyncError() {
        return "Stop! The input passenger detail does not have a flight number that exist yet.\n"
                + "Flight detail of the specific flight number should input first.";
    }

    public void showFileNotFoundMessage() {
        System.out.println("Cannot read data from file, file or directory does not exist.\n");
    }
}


