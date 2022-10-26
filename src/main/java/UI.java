import java.util.Scanner;

public class UI {
    private static Scanner scanner;

    protected static final String LOGO = "ıllıllı OneDoc ıllıllı";
    private static final String WELCOME_MESSAGE = "Hello welcome to" + System.lineSeparator() + LOGO;
    private static final String MAIN_MENU = "Please choose one of the following options:" + System.lineSeparator()
            + "1 - Patients" + System.lineSeparator()
            + "2 - Visits" + System.lineSeparator()
            + "3 - Prescription" + System.lineSeparator()
            + "bye - Quit OneDoc";

    // General Invalid Error Message
    private static final String INVALID_MAIN_MENU_COMMAND_MESSAGE = "Incorrect input. Please type 1, 2, 3 or bye";
    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index number is out of range. Try again.";

    // Patient
    private static final String PATIENT_MAIN_MENU =
            "This is the Patient Main Menu!" + System.lineSeparator()
            + "List of commands: "
            + UI.PATIENT_ADD
            + UI.PATIENT_VIEW_ALL
            + UI.PATIENT_RETRIEVE
            + UI.PATIENT_EDIT
            + UI.RETURN_TO_MAIN
            + UI.EXIT_PROGRAM;

    // Prescription
    private static final String NO_PRESCRIPTION_MESSAGE = "There are currently no prescriptions in the record.";
    private static final String PRESCRIPTION_MAIN_MENU =
            "This is the Prescription Main Menu!" + System.lineSeparator()
            + "List of commands:"
            + UI.PRESCRIPTION_ADD
            + UI.PRESCRIPTION_EDIT
            + UI.PRESCRIPTION_VIEW_ALL
            + UI.PRESCRIPTION_VIEW_PATIENT
            + UI.PRESCRIPTION_VIEW_ACTIVE
            + UI.PRESCRIPTION_CHANGE_ACTIVE
            + UI.PRESCRIPTION_CHANGE_INACTIVE
            + UI.RETURN_TO_MAIN
            + UI.EXIT_PROGRAM;
    private static final String NO_MATCHING_PRESCRIPTION_MESSAGE = "There are currently no prescriptions from this "
            + "patient.";
    private static final String NO_MATCHING_ACTIVE_PRESCRIPTION_MESSAGE = "There are currently no active prescriptions "
            + "from this patient.";

    // Visit
    private static final String VISIT_MAIN_MENU =
            "This is the Visits Main Menu!" + System.lineSeparator()
            + "List of commands:"
            + UI.VISIT_ADD
            + UI.VISIT_EDIT
            + UI.VISIT_VIEW_ALL
            + UI.VISIT_VIEW_PATIENT
            + UI.VISIT_VIEW
            + UI.RETURN_TO_MAIN
            + UI.EXIT_PROGRAM;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printMainMenu() {
        System.out.println(MAIN_MENU);
    }

    public void printInvalidMainMenuErrorMessage() {
        System.out.println(INVALID_MAIN_MENU_COMMAND_MESSAGE);
    }

    public void printIndexOutOfRangeErrorMessage() {
        System.out.println(INDEX_OUT_OF_RANGE_MESSAGE);
    }

    public void printSubMenu(MainMenuState mainMenuState) {
        switch (mainMenuState) {
        case PATIENT:
            printPatientMenuMessage();
            break;
        case VISIT:
            printVisitsMenuMessage();
            break;
        case PRESCRIPTION:
            printPrescriptionMenuMessage();
            break;
        default:
            break;
        }
    }

    public void printPatientMenuMessage() {
        System.out.println(PATIENT_MAIN_MENU);
    }

    public void printPatientAddedMessage(Patient patient) {
        System.out.println("Ok! I've added a patient! The patient's details are as follows:");
        printLine();
        System.out.println(patient);
        printLine();
    }

    public static void printErrorMessage(String errorMessage) {
        printLine();
        System.out.println("\t" + errorMessage);
        printLine();
    }

    public void printPatientEditedMessage(Patient patient) {
        System.out.print("Alright, I've modified the details of the patient! ");
        System.out.println("Here are the new details of the patient: ");
        printLine();
        System.out.println(patient);
        printLine();
    }

    public void printVisitsMenuMessage() {
        System.out.println(VISIT_MAIN_MENU);
    }

    public void printAddVisitMessage(String visitString) {
        System.out.println("You have added a visit!");
        printLine();
        System.out.println(visitString);
        printLine();
    }

    public void printEditVisitReasonMessage(String visitString) {
        System.out.println("You have edited reason for the visit. Here's the updated visit!");
        printLine();
        System.out.println(visitString);
        printLine();
    }

    public void printDeleteVisitReasonMessage(String visitString) {
        System.out.println("You have deleted the reason for the visit. Here's the updated visit!");
        printLine();
        System.out.println(visitString);
        printLine();
    }

    public void printViewAllVisitsMessage() {
        System.out.println("Here are all the visits:");
    }

    public void printPrescriptionMenuMessage() {
        System.out.println(PRESCRIPTION_MAIN_MENU);
    }

    public void printAddPrescriptionMessage(String prescriptionString) {
        System.out.println("You have added a prescription!");
        printLine();
        System.out.println(prescriptionString);
        printLine();
    }

    public void printEditPrescriptionMessage(String prescriptionString) {
        System.out.println("You have edited the prescription!");
        printLine();
        System.out.println(prescriptionString);
        printLine();
    }

    public void printNoPrescriptionMessage() {
        System.out.println(NO_PRESCRIPTION_MESSAGE);
    }

    public void printViewAllPrescriptionsMessage() {
        System.out.println("Here are all the prescriptions:");
    }

    public void printExitMessage() {
        System.out.println("Goodbye!");
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static final String PATIENT_ADD = "\n* To add a patient: add n/[name] g/[M/F] d/[DOB] i/[ID]";
    public static final String PATIENT_EDIT = "\n* To edit a patient's information: "
            + "edit i/[ID] (n/[name] or g/[M/F] or d/[DOB])";
    public static final String PATIENT_RETRIEVE = "\n* To retrieve information about a specific patient:"
            + " retrieve i/[ID]";

    public static final String PATIENT_VIEW_ALL = "\n* To list all patients: viewall";
    public static final String VISIT_ADD = "\n* To add a visit: add i/[ID] d/[date] t/[time] r/[reason]";
    public static final String VISIT_EDIT = "\n* To edit a visit's reason: edit i/[ID] r/[reason]";
    public static final String VISIT_VIEW_ALL = "\n* To list all visits: viewall";
    public static final String VISIT_VIEW_PATIENT = "\n* To list all visits of one patient: viewPatient i/[ID]";
    public static final String VISIT_VIEW = "\n* To view a patient's specific visit: viewVisit i/[ID] [INDEX]";

    public static final String PRESCRIPTION_ADD = "\n* To add a prescription: add i/ID "
            + "n/[name] d/[dosage] t/[time interval]";
    public static final String PRESCRIPTION_EDIT = "\n* To edit a prescription: edit i/[index] "
            + "(n/[name] or d/[dosage] or t/[time interval])";

    public static final String PRESCRIPTION_VIEW_ALL = "\n* To list all prescriptions: viewall";

    public static final String PRESCRIPTION_VIEW_PATIENT = "\n* To list all prescriptions of one patient: "
            + "viewPatientPres i/[ID]";

    public static final String PRESCRIPTION_VIEW_ACTIVE = "\n* To list all active prescriptions of one "
        + "patient: viewActPatientPres i/[ID]";

    public static final String PRESCRIPTION_CHANGE_ACTIVE = "\n* To change a prescription record to be "
            + "active: activate i/INDEX";

    public static final String PRESCRIPTION_CHANGE_INACTIVE = "\n* To change a prescription record to be "
            + "inactive: deactivate i/[ID]";

    public static final String RETURN_TO_MAIN =  "\n* To return to main menu: main";

    public static final String EXIT_PROGRAM =  "\n* To quit OneDoc: bye";

    public void printActivatePrescriptionMessage(String prescription) {
        System.out.println("Ok, I've activated the prescription below:");
        printLine();
        System.out.println(prescription);
        printLine();
    }

    public void printDeactivatePrescriptionMessage(String prescription) {
        System.out.println("Ok, I've deactivated the prescription below:");
        printLine();
        System.out.println(prescription);
        printLine();
    }

    public void printNoMatchingPrescriptionMessage() {
        System.out.println(NO_MATCHING_PRESCRIPTION_MESSAGE);
    }

    public void printNoMatchingActivePrescriptionMessage() {
        System.out.println(NO_MATCHING_ACTIVE_PRESCRIPTION_MESSAGE);
    }
}

