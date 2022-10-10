package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    private static final String LOGO = "   _____ _             _____            _             _\n"
            + "  / ____| |           / ____|          | |           | |\n"
            + " | (___ | | ___   _  | |     ___  _ __ | |_ _ __ ___ | |\n"
            + "  \\___ \\| |/ / | | | | |    / _ \\| '_ \\| __| '__/ _ \\| |\n"
            + "  ____) |   <| |_| | | |___| (_) | | | | |_| | | (_) | |\n"
            + " |_____/|_|\\_\\\\__, |  \\_____\\___/|_| |_|\\__|_|  \\___/|_|\n"
            + "               __/ |\n"
            + "              |___/\n";

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
    }

    public String nextLineInput() {
        return in.nextLine();
    }

    public static void showAddedPassenger(Passenger passenger) {
        System.out.println("Passenger " + passenger.getName() + " of "
                + passenger.getFlightNumber() + " " + passenger.getSeatNumber() + " has been added.\n"
        );
    }

    public void showGoodByeMessage() {
        System.out.println("Thank you, come again! :)\n");
    }

    public void showFlightAddedMessage() {
        System.out.println("Flight added!");
    }

    public void showListOfFlights(ArrayList<Flight> flightList) {
        System.out.println("---------------------------------------------------------"
                + "-----------------------------------------------------------------------");
        System.out.printf("%5s %10s %27s %20s %20s %12s %22s", "FLIGHT NUM", "AIRLINE",
                "DESTINATION", "DEPARTURE TIME", "BOARDING GATE", "TERMINAL", "CHECK-IN ROW/DOOR");
        System.out.println("\n-------------------------------------------------------"
                + "-------------------------------------------------------------------------");

        for (Flight flight : flightList) {
            System.out.println(flight);
        }
    }

    public void showEmptyDescriptionMessage() {
        System.out.println("oops! The description is empty :(");
    }

    public void showFlightRemovedMessage(String flightNum) {
        System.out.println("FLIGHT " + flightNum + " HAS BEEN DELETED.");
    }

    public void showFlightNotFoundMessage(String flightNum) {
        System.out.println("FLIGHT " + flightNum + " NOT FOUND.");
    }
}
