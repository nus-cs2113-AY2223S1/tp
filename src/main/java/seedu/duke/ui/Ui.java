package seedu.duke.ui;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected Scanner in = new Scanner(System.in);
    protected static final int FORMAT_NAME_SPACE = 24;
    protected static final int FORMAT_DOD_SPACE = 9;
    protected static final int FORMAT_DT_SPACE = 14;
    protected static final int FORMAT_FN_SPACE = 10;
    protected static final int FORMAT_GN_SPACE = 8;
    protected static final int FORMAT_BG_SPACE = 12;
    protected static final int FORMAT_SN_SPACE = 8;
    protected static final int FORMAT_BT_SPACE = 13;
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

    public void showWelcomeMessage() {
        System.out.println("Welcome to\n" + LOGO);
    }

    public String readCommand() {
        String lineInput;
        lineInput = in.nextLine();
        return lineInput;
    }

    public static void showAddedPassenger(PassengerInfo passenger) {
        System.out.println("Passenger " + passenger.getName() + " of "
                + passenger.getFlightNumber() + " " + passenger.getSeatNumber() + " has been added."
        );
    }

    public static void showDeleteMessage(String name, String flightNumber, String seatNumber, int numOfPassenger) {
        System.out.println("Passenger " + name + " from "
                + flightNumber + " of seat number " + seatNumber
                + " have been\ndeleted from the passenger list.");
        System.out.println(numOfPassenger + " passenger(s) left on the passenger list.\n");
    }

    public String showDeleteError() {
        return "The system is unable to delete the specified passenger "
                + "\nas he/she is not found in the passenger list "
                + "or his/her \ndetail have been input incorrectly.";
    }

    public String showOperationError() {
        return "Input a valid operation, please try again.";
    }

    public String showBlankOpsError() {
        return "Detail of the operation is blank,\n"
                + "please try again and input all necessary details.";
    }

    public String showErrorMessage() {
        return "The system is unable to read your command, please try again.";
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showByeMessage() {
        System.out.println("Thank you, come again! :)");
    }

    public void showPassengerListHeader() {
        System.out.print("\n+-----------------------------------------------------------"
                + "--------------------------------------------------------------+\n"
                + "|                                                  PASSENGER DETAILS LOGBOOK"
                + "                                              |\n"
                + "+-----------------------------------------------------------"
                + "--------------------------------------------------------------+\n"
                + "|           NAME           | DEPARTURE | DEPARTURE TIME | FLIGHT NUM |"
                + " GATE NUM | BOARDING GRP | SEAT NUM | BOARDING TIME |\n"
                + "+-----------------------------------------------------------"
                + "--------------------------------------------------------------+\n");
    }

    public void showPassengerListBody(String name, String departureDate, String departureTime,
                                      String flightNumber, String gateNumber, int boardingGroup,
                                      String seatNumber, String boardingTime) {
        formatPassengerDetail(name, departureDate, departureTime,
                flightNumber, gateNumber, boardingGroup, seatNumber, boardingTime);
        showFormattedDetail();
    }

    private static void showFormattedDetail() {
        System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s |\n"
                + "+-----------------------------------------------------------"
                + "--------------------------------------------------------------+\n",
                formattedName, formattedDepartureDate, formattedDepartureTime, formattedFlightNumber,
                formattedGateNumber, formattedBoardingGroup, formattedSeatNumber, formattedBoardingTime);
    }

    private static void formatPassengerDetail(String name, String departureDate, String departureTime,
                                              String flightNumber, String gateNumber, int boardingGroup,
                                              String seatNumber, String boardingTime) {
        formattedName = String.format("%-" + FORMAT_NAME_SPACE + "s", name);
        formattedDepartureDate = String.format("%-" + FORMAT_DOD_SPACE + "s", departureDate);
        formattedDepartureTime = String.format("%-" + FORMAT_DT_SPACE + "s", departureTime);
        formattedFlightNumber = String.format("%-" + FORMAT_FN_SPACE + "s", flightNumber);
        formattedGateNumber = String.format("%-" + FORMAT_GN_SPACE + "s", gateNumber);
        formattedBoardingGroup = String.format("%-" + FORMAT_BG_SPACE + "s", boardingGroup);
        formattedSeatNumber = String.format("%-" + FORMAT_SN_SPACE + "s", seatNumber);
        formattedBoardingTime = String.format("%-" + FORMAT_BT_SPACE + "s", boardingTime);
    }

    public void showEmptyPassengerList() {
        System.out.printf("| %80s %-38s |\n"
                + "+-----------------------------------------------------------"
                + "--------------------------------------------------------------+\n",
                "The passenger details logbook is empty.", " ");
    }

    public void showFlightNotFoundMessage(String flightNum) {
        System.out.println("FLIGHT " + flightNum + " NOT FOUND.");
    }

    public void showEmptyDescriptionMessage() {
        System.out.println("oops! The description is empty :(");
    }

    public void showFlightAddedMessage() {
        System.out.println("Flight added!");
    }

    public void showFlightRemovedMessage(String flightNum) {
        System.out.println("FLIGHT " + flightNum + " HAS BEEN DELETED.");
    }

    public void showListOfFlights(ArrayList<FlightInfo> flightList) {
        System.out.println("---------------------------------------------------------"
                + "-----------------------------------------------------------------------");
        System.out.printf("%5s %10s %27s %20s %20s %12s %22s", "FLIGHT NUM", "AIRLINE",
                "DESTINATION", "DEPARTURE TIME", "BOARDING GATE", "TERMINAL", "CHECK-IN ROW/DOOR");
        System.out.println("-------------------------------------------------------"
                + "-------------------------------------------------------------------------");

        for (FlightInfo flight : flightList) {
            System.out.println(flight);
        }
    }
}


