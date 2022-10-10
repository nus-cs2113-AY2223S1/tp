package seedu.duke;

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
}
