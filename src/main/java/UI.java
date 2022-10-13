import java.util.Scanner;

public class UI {
    private static Scanner sc = new Scanner(System.in);

    public static String readLine() {
        return sc.nextLine();
    }


    public static void printSubMenu(MainMenuState mainMenuState) {
        switch (mainMenuState) {
        case PATIENT:
            Messages.printPatientMenuMessage();
            break;
        case VISIT:
            Messages.printVisitsMenuMessage();
            break;
        case PRESCRIPTION:
            Messages.printPrescriptionMenuMessage();
            break;
        default:
            break;
        }
    }
}

